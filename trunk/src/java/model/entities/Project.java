/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Project {

    private int projectID;
    private String projectName;
    private String description;
    private Date startDate;
    private int duration;
    private ProjectType type;
    int active;
    private boolean editable;

    private List<User> userList = null;
    private List<User> userListNotJoin = null;

    public Project() {
    }

    public Project(int projectID, String projectName, String description, Date startDate, int duration, ProjectType type, int active) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.duration = duration;
        this.type = type;
        this.active = active;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<User> getUserListNotJoin() {
        return userListNotJoin;
    }

    public void setUserListNotJoin(List<User> userListNotJoin) {
        this.userListNotJoin = userListNotJoin;
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

}
