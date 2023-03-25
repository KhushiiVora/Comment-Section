package NewsFeed.Dao;

import NewsFeed.Exceptions.*;
import NewsFeed.Models.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class NewsDao {
    private static NewsDao newsDao = null;
    private UserDao userDao = UserDao.getUserDao();
    // to associate usesrname with news and comments
    private HashMap<String, List<Template>> userTemplateReference = new HashMap<>();
    private HashMap<Integer, News> newsReference = new HashMap<>();

    // it is list of comments associated to either news or other comment
    private HashMap<Integer, List<Comments>> newsCommentsMap = new HashMap<>();

    private NewsDao() {
    }

    public static NewsDao getNewsDao() {
        if (newsDao == null) {
            synchronized (NewsDao.class) {
                if (newsDao == null) {
                    newsDao = new NewsDao();
                }
            }
        }
        return newsDao;
    }

    public void post(String post) {
        News news = new News();
        news.setPost(post);
        news.setTimeStamp();
        // System.out.println("New news created!!" + news.getPost());

        User loggedInUser = userDao.getLoggedInUser();
        // System.out.println("uname: " + loggedInUser.getUsername());
        try {
            if (loggedInUser == null) {
                throw new Exception("No user is logged in!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        news.setUsername(loggedInUser.getUsername());

        List<Template> templates = userTemplateReference.get(loggedInUser.getUsername());
        if (templates == null) {
            templates = new ArrayList<Template>();
        }
        templates.add(news);
        userTemplateReference.put(loggedInUser.getUsername(), templates);
        newsReference.put(news.getNewsId(), news);
        newsCommentsMap.put(news.getNewsId(), null);
    }

    public void replyToNews(int templateId, String comment) {
        // System.out.println("ReplyToPOst");
        try {
            // templateId can be a newsId or a commmentId
            if (!(newsCommentsMap.containsKey(templateId))) {
                throw new InvalidDataException("Entered id did not exist!!");
            }
            User loggedInUser = userDao.getLoggedInUser();

            Comments replyComment = new Comments();
            replyComment.setPost(comment);
            replyComment.setTimeStamp();
            replyComment.setUsername(loggedInUser.getUsername());

            List<Comments> comments = newsCommentsMap.get(templateId);
            if (comments == null) {
                comments = new ArrayList<Comments>();
            }
            comments.add(replyComment);
            newsCommentsMap.put(templateId, comments);
            newsCommentsMap.put(replyComment.getNewsId(), null);

            List<Template> templates = userTemplateReference.get(loggedInUser.getUsername());
            if (templates == null) {
                templates = new ArrayList<Template>();
            }
            templates.add(replyComment);
            userTemplateReference.put(loggedInUser.getUsername(), templates);

        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void upvote(int newsId) {
        try {
            News news = newsReference.get(newsId);

            if (news == null) {
                throw new InvalidDataException("News id is not found!!");
            }
            news.setUpvote();

        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }

    }

    public void downvote(int newsId) {
        try {
            News news = newsReference.get(newsId);

            if (news == null) {
                throw new InvalidDataException("News id is not found!!");
            }
            news.setDownvote();

        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showNewsFeed() {
        // System.out.println(newsReference);
        if (newsReference == null)
            return;

        User loggedInUser = userDao.getLoggedInUser();
        List<User> followedUsers = loggedInUser.getFollowUsers();
        // System.out.println(followedUsers);
        List<News> feed = new ArrayList<>();
        if (followedUsers == null || followedUsers.isEmpty()) {
            feed = showAllPosts();
            // System.out.println(feed);
        } else {
            feed = getPostsBy(followedUsers);
        }

        Collections.sort(feed, new SortNewsByVotes());
        Collections.sort(feed, new sortNewsByComments());
        Collections.sort(feed, new sortNewsByTime());

        for (News news : feed) {
            System.out.println("\n");
            System.out.println("id: " + news.getNewsId());
            System.out.println("(" + news.getUpvote() + " upvotes " + news.getDownvote()
                    + " downvotes)");
            System.out.println("By: " + news.getUsername());
            System.out.println(news.getPost());

            printTimeDuration(news.getTimeStamp());
            if (newsCommentsMap.containsKey(news.getNewsId())) {
                printComments(news);
            }
        }

    }

    public List<News> showAllPosts() {
        return new ArrayList<>(newsReference.values());
    }

    public void printComments(Template template) {
        List<Comments> comments = newsCommentsMap.get(template.getNewsId());
        if (comments == null || comments.size() == 0) {
            return;
        }

        for (Comments comment : comments) {
            System.out.println("\tid: " + comment.getNewsId());
            System.out.println("\tBy: " + comment.getUsername());
            System.out.println("\t" + comment.getPost());

            System.out.print("\t");
            printTimeDuration(comment.getTimeStamp());

            if (newsCommentsMap.containsKey(comment.getNewsId())) {
                printComments(comment);
            }
        }
    }

    public void printTimeDuration(LocalDateTime timestamp) {
        Duration duration = Duration.between(LocalDateTime.now(), timestamp);
        if (duration.toDays() > 0)
            System.out.println(Math.abs(duration.toDays()) + "d ago");
        else if (duration.toHours() > 0)
            System.out.println(Math.abs(duration.toHours()) + "h ago");
        else if (duration.toMinutes() > 0)
            System.out.println(Math.abs(duration.toMinutes()) + "m ago");
        else
            System.out.println(Math.abs(duration.toSeconds()) + "s ago");
    }

    public List<News> getPostsBy(List<User> followedUsers) {
        List<News> feed = new ArrayList<>();
        for (User followedUser : followedUsers) {
            List<Template> templates = userTemplateReference.get(followedUser.getUsername());
            for (Template template : templates) {
                if (template instanceof News) {
                    feed.add((News) template);
                }
            }
        }
        return feed;
    }

    class SortNewsByVotes implements Comparator<News> {
        @Override
        public int compare(News news, News toNews) {
            if ((news.getUpvote() - news.getDownvote()) == (toNews.getUpvote() - toNews.getDownvote()))
                return 0;
            else if ((news.getUpvote() - news.getDownvote()) < (toNews.getUpvote() - toNews.getDownvote()))
                return 1;
            else
                return -1;
        }
    }

    class sortNewsByComments implements Comparator<News> {
        @Override
        public int compare(News news, News toNews) {

            if (newsCommentsMap.get(news.getNewsId()) == null || newsCommentsMap.get(toNews.getNewsId()) == null)
                return 0;

            if (newsCommentsMap.get(news.getNewsId()).size() == newsCommentsMap.get(toNews.getNewsId()).size())
                return 0;
            else if (newsCommentsMap.get(news.getNewsId()).size() < newsCommentsMap.get(toNews.getNewsId()).size())
                return 1;
            else
                return -1;
        }
    }

    class sortNewsByTime implements Comparator<News> {
        @Override
        public int compare(News news, News toNews) {
            return toNews.getTimeStamp().compareTo(news.getTimeStamp());
        }
    }
}
