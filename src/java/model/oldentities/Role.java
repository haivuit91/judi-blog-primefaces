/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.oldentities;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Role {

    private int roleID;
    private String roleName;
    private String pathImage;
    private int isActive;

    List<User> userList = null;
    
    public Role() {

    }

    public Role(int roleID, String roleName, String pathImage, int isActive) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.pathImage = pathImage;
        this.isActive = isActive;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public int getActive() {
        return isActive;
    }

    public void setActive(int isActive) {
        this.isActive = isActive;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
