/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entities;

/**
 *
 * @author Admin
 */
public class ProjectUserDetails {
    private int project_userID;
    private User user;
    private Project project;
    private int creater;

    public ProjectUserDetails() {
    }

    public ProjectUserDetails(int project_userID, User user, Project project, int creater) {
        this.project_userID = project_userID;
        this.user = user;
        this.project = project;
        this.creater = creater;
    }

    public int getProject_userID() {
        return project_userID;
    }

    public void setProject_userID(int project_userID) {
        this.project_userID = project_userID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getCreater() {
        return creater;
    }

    public void setCreater(int creater) {
        this.creater = creater;
    }
    
}
