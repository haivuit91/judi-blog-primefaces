package model.entities;
// Generated Jul 25, 2014 9:34:05 AM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Post generated by hbm2java
 */
public class Post  implements java.io.Serializable {

     private Integer postId;
     private Category category;
     private User user;
     private String title;
     private String content;
     private String imagePath;
     private Date postDate;
     private boolean active;

    public Post() {
    }

	
    public Post(Category category, User user, String title, String content, Date postDate, boolean active) {
        this.category = category;
        this.user = user;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.active = active;
    }
    public Post(Category category, User user, String title, String content, String imagePath, Date postDate, boolean active) {
       this.category = category;
       this.user = user;
       this.title = title;
       this.content = content;
       this.imagePath = imagePath;
       this.postDate = postDate;
       this.active = active;
    }
   
    public Integer getPostId() {
        return this.postId;
    }
    
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return this.imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getPostDate() {
        return this.postDate;
    }
    
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }




}


