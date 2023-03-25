package NewsFeed.Models;

import java.util.*;
// import java.lang.*;

public class User {
    private Double userId;
    private String username;
    private List<User> followUsers = new ArrayList<>();
    // private List<String> posts = new ArrayList<>();

    public User() {
        this.userId = Math.random();
    }

    public Double getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public List<User> getFollowUsers() {
        return followUsers;
    }

    public void setFollow(List<User> followUsers) {
        this.followUsers = followUsers;
    }

    // public List<String> getPosts() {
    // return posts;
    // }

    // public void setPosts(List<String> posts) {
    // this.posts = posts;
    // }

}
