package NewsFeed.Models;

import NewsFeed.Utils.IdGenerator;

// import java.time.LocalDateTime;

public class Comments extends Template {

    public Comments() {
        this.newsId = IdGenerator.generateId();
    }

    public int getNewsId() {
        return newsId;
    }

    // public void setPost(String post) {
    // this.post = post;
    // }

    // public String getPost() {
    // return post;
    // }

    // public LocalDateTime getTimeStamp() {
    // return timeStamp;
    // }

    // public void setTimeStamp() {
    // this.timeStamp = LocalDateTime.now();
    // }
}
