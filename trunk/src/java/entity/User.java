package entity;
// Generated Jul 25, 2014 9:34:05 AM by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "db_judiwebsite", uniqueConstraints = @UniqueConstraint(columnNames = "userName")
)
public class User implements java.io.Serializable {

    private Integer userId;
    private Role role;
    private String userName;
    private String pwd;
    private String fullName;
    private Date birthOfDay;
    private boolean gender;
    private String idCard;
    private String address;
    private String email;
    private String phoneNumber;
    private String imagePath;
    private String idActive;
    private boolean active;
    private Set posts = new HashSet(0);
    private Set<ProjectUserDetails> projectUserDetailses = new HashSet<ProjectUserDetails>(0);

    public User() {
    }

    public User(Role role, String userName, String pwd, String fullName, boolean gender, String email, boolean active) {
        this.role = role;
        this.userName = userName;
        this.pwd = pwd;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.active = active;
    }

    public User(Role role, String userName, String pwd, String fullName, Date birthOfDay, boolean gender, String idCard, String address, String email, String phoneNumber, String imagePath, String idActive, boolean active, Set posts, Set<ProjectUserDetails> projectUserDetailses) {
        this.role = role;
        this.userName = userName;
        this.pwd = pwd;
        this.fullName = fullName;
        this.birthOfDay = birthOfDay;
        this.gender = gender;
        this.idCard = idCard;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imagePath = imagePath;
        this.idActive = idActive;
        this.active = active;
        this.posts = posts;
        this.projectUserDetailses = projectUserDetailses;
    }

    public User(Integer userId, Role role, String userName, String pwd, String fullName, Date birthOfDay, boolean gender, String idCard, String address, String email, String phoneNumber, String imagePath, String idActive, boolean active) {
        this.userId = userId;
        this.role = role;
        this.userName = userName;
        this.pwd = pwd;
        this.fullName = fullName;
        this.birthOfDay = birthOfDay;
        this.gender = gender;
        this.idCard = idCard;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imagePath = imagePath;
        this.idActive = idActive;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "userID", unique = true, nullable = false)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleID", nullable = false)
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "userName", unique = true, nullable = false, length = 45)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "pwd", nullable = false, length = 45)
    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Column(name = "fullName", nullable = false, length = 55)
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthOfDay", length = 10)
    public Date getBirthOfDay() {
        return this.birthOfDay;
    }

    public void setBirthOfDay(Date birthOfDay) {
        this.birthOfDay = birthOfDay;
    }

    @Column(name = "gender", nullable = false)
    public boolean isGender() {
        return this.gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Column(name = "idCard", length = 15)
    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column(name = "address", length = 105)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "email", nullable = false, length = 55)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phoneNumber", length = 15)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "imagePath", length = 105)
    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Column(name = "idActive", length = 45)
    public String getIdActive() {
        return this.idActive;
    }

    public void setIdActive(String idActive) {
        this.idActive = idActive;
    }

    @Column(name = "active", nullable = false)
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set getPosts() {
        return this.posts;
    }

    public void setPosts(Set posts) {
        this.posts = posts;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<ProjectUserDetails> getProjectUserDetailses() {
        return this.projectUserDetailses;
    }

    public void setProjectUserDetailses(Set<ProjectUserDetails> projectUserDetailses) {
        this.projectUserDetailses = projectUserDetailses;
    }

    
    
}
