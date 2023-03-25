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
        newsService.post("I am going to be the darkest dark wizard of all time");
        newsService.post("I am lord Voldemort btw 3:)");
        System.out.println("=========================");
        System.out.println("Lucious logged in...");
        userService.login("lucious");
        newsService.upvote(1);
        userService.followUser("tom");
        newsService.replyToNews(1, "I am with you dark lord!");
        System.out.println("=========================");
        System.out.println("Albert logged in...");
        userService.login("albert");
        newsService.post("Happiness can be found, even in the darkest of times.");
        userService.followUser("lucious");
        newsService.downvote(1);
        newsService.downvote(2);
        newsService.replyToNews(2, "LOL!");
        newsService.replyToNews(3, "You shouldn't be");
        System.out.println("=========================");
        System.out.println("Tom logged in...");
        userService.login("tom");
    }

}
