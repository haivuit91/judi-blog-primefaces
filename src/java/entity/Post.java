package entity;
// Generated Jul 25, 2014 9:34:05 AM by Hibernate Tools 3.6.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Post generated by hbm2java
 */
@Entity
@Table(name="post"
    ,catalog="db_judiwebsite"
)
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
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="postID", unique=true, nullable=false)
    public Integer getPostId() {
        return this.postId;
    }
    
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoryID", nullable=false)
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userID", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="title", nullable=false, length=145)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="content", nullable=false, length=20000)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    
    @Column(name="imagePath", length=105)
    public String getImagePath() {
        return this.imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="postDate", nullable=false, length=10)
    public Date getPostDate() {
        return this.postDate;
    }
    
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    
    @Column(name="active", nullable=false)
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }




}

