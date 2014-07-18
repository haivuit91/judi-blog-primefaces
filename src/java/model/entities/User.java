/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class User {

    private int userID;
    private String userName;
    private String pwd;
    private String fullName;
    private Date birthday;
    private int gender;
    private String idCard;
    private String address;
    private String email;
    private String phone;
    private String pathImage;
    private Role role;
    private String idActive;
    private int active;
    
    private List<Post> postList = null;
    private List<Project> projectList = null;

    public User() {
    }

    public User(int userID, String userName, String pwd, String fullName, Date birthday, int gender, String idCard, String address, String email, String phone, String pathImage, Role role, String idActive, int active) {
        this.userID = userID;
        this.userName = userName;
        this.pwd = pwd;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.idCard = idCard;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.pathImage = pathImage;
        this.role = role;
        this.idActive = idActive;
        this.active = active;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getIdActive() {
        return idActive;
    }

    public void setIdActive(String idActive) {
        this.idActive = idActive;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public boolean equals(Object obj) {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
