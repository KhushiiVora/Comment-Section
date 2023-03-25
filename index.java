package NewsFeed;

import NewsFeed.Services.UserService;
import NewsFeed.Services.NewsService;

// import java.util.*;

public class index {
    public static void main(String[] args) {
        System.out.println("Welcome...");
        NewsService newsService = NewsService.getNewsService();
        UserService userService = UserService.getUserService();

        userService.signUp("lucious");
        userService.signUp("albert");
        userService.signUp("tom");
        System.out.println("Tom logged in...");
        userService.login("tom");
        newsService.post("There is no substitute for hard work.");
        newsService.post("Live as if you were to die tomorrow. Learn as if you were to live forever.");
        System.out.println("=========================");
        System.out.println("Lucious logged in...");
        userService.login("lucious");
        newsService.upvote(1);
        userService.followUser("tom");
        newsService.replyToNews(1, "True tom hard work pays...");
        System.out.println("=========================");
        System.out.println("Albert logged in...");
        userService.login("albert");
        newsService.post("Happiness can be found, even in the darkest of times.");
        userService.followUser("lucious");
        newsService.downvote(1);
        newsService.upvote(2);
        newsService.replyToNews(2, "Never stop Learning");
        newsService.replyToNews(3, "True");
        System.out.println("=========================");
        System.out.println("Tom logged in...");
        userService.login("tom");
    }

}
