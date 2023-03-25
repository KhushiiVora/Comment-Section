package NewsFeed.Services;

import NewsFeed.Models.User;
import NewsFeed.Dao.UserDao;
import NewsFeed.Exceptions.*;

public class UserService {
    private static UserService userService = null;
    private NewsService newsService = NewsService.getNewsService();
    private UserDao userDao = UserDao.getUserDao();

    private UserService() {
    }

    public static UserService getUserService() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null) {
                    userService = new UserService();
                }
            }
        }
        return userService;
    }

    public void signUp(String username) {
        try {
            if (username == null) {
                throw new InvalidDataException("Username cannot be null!!");
            }

            User user = userDao.signUp(username);

            if (user == null) {
                throw new Exception("Something went wrong while signup!!");
            }

        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void login(String username) {
        try {
            if (username == null) {
                throw new InvalidDataException("Username cannot be null!!");
            }

            userDao.login(username);
            newsService.showNewsFeed();
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void followUser(String username) {
        try {
            if (username == null) {
                throw new InvalidDataException("Username cannot be null!!");
            }

            userDao.followUser(username);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

}
