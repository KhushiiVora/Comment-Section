package NewsFeed.Models;

import java.time.LocalDateTime;
// import java.util.*;

public abstract class Template {
    int newsId;
    String post;
    String username;
    LocalDateTime timeStamp;

    abstract public int getNewsId();

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp() {
        this.timeStamp = LocalDateTime.now();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
