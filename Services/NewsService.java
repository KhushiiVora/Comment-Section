package NewsFeed.Services;

import NewsFeed.Dao.*;
import NewsFeed.Exceptions.InvalidDataException;

public class NewsService {
    private static NewsService newsService = null;
    private NewsDao newsDao = NewsDao.getNewsDao();

    private NewsService() {
    }

    public static NewsService getNewsService() {
        if (newsService == null) {
            synchronized (UserService.class) {
                if (newsService == null) {
                    newsService = new NewsService();
                }
            }
        }
        return newsService;
    }

    public void post(String post) {
        try {
            if (post == null) {
                throw new InvalidDataException("Please write a post!!");
            }
            newsDao.post(post);

        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void replyToNews(int newsId, String comment) {
        try {
            if (comment == null || newsId <= 0) {
                throw new InvalidDataException("Please enter valid arguments!!");
            }
            newsDao.replyToNews(newsId, comment);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void upvote(int newsId) {
        try {
            if (newsId <= 0)
                throw new InvalidDataException("Invalid news id!!");

            newsDao.upvote(newsId);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void downvote(int newsId) {
        try {
            if (newsId <= 0)
                throw new InvalidDataException("Invalid news id!!");

            newsDao.downvote(newsId);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showNewsFeed() {
        newsDao.showNewsFeed();
    }

}
