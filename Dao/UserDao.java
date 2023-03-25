package NewsFeed.Dao;

import NewsFeed.Exceptions.NoUserFoundException;
import NewsFeed.Models.*;

import java.util.*;

public class UserDao {
    private static UserDao userDao = null;
    private User loggedInUser = null;
    // private Map<> news = new HashMap<>();
    private HashMap<Double, User> userIdReference = new HashMap<>();
    private HashMap<String, User> userReference = new HashMap<>();

    private UserDao() {
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            synchronized (UserDao.class) {
                if (userDao == null) {
                    userDao = new UserDao();
                }
            }
        }
        return userDao;
    }

    public User signUp(String username) {
        if (userReference.containsKey(username)) {
            User user = userReference.get(username);
            System.out.println("User already exists with id: " + user.getUserId());
            return user;
        }
        User user = new User();
        user.setName(username);

        userReference.put(username, user);
        userIdReference.put(user.getUserId(), user);

        return user;
    }

    public void login(String username) {
        try {
            if (userReference.containsKey(username)) {
                loggedInUser = userReference.get(username);
            } else {
                throw new NoUserFoundException("User did not found!!");
            }
        } catch (NoUserFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void followUser(String username) {
        try {
            if (userReference.containsKey(username)) {
                User user = userReference.get(username);
                loggedInUser.getFollowUsers().add(user);
            } else {
                throw new NoUserFoundException("No user found with username: " + username);
            }
        } catch (NoUserFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
