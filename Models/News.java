package NewsFeed.Models;

import NewsFeed.Utils.*;

// import java.util.*;
// import java.time.LocalDateTime;

public class News extends Template {
    private int upvote = 0;
    private int downvote = 0;
    // private List<Comments> comments = new ArrayList<>();

    public News() {
        this.newsId = IdGenerator.generateId();
    }

    public int getNewsId() {
        return newsId;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote() {
        this.upvote++;
    }

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote() {
        this.downvote++;
    }

    // public void setPost(String post) {
    // this.post = post;
    // }

    // public String getPost() {
    // return post;
    // }

    // public List<Comments> getComments() {
    // return comments;
    // }

    // public void setComments(List<News> comments) {
    // this.comments = comments;
    // }

    // public LocalDateTime getTimeStamp() {
    // return timeStamp;
    // }

    // public void setTimeStamp() {
    // this.timeStamp = LocalDateTime.now();
    // }

}
