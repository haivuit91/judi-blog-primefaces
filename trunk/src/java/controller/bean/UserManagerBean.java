/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.RoleDAO;
import model.dao.UserDAO;
import model.dao.service.RoleDAOService;
import model.dao.service.UserDAOService;
import model.entities.Role;
import model.entities.User;

/**
 *
 * @author HAI VU
 */
@ManagedBean(name = "userManager", eager = true)
@RequestScoped
public class UserManagerBean implements Serializable {

    private User user;
    private final FacesContext facesContext;
    private List<User> listUser;
    private List<User> searchUser;
    private List<Role> roles;

    UserDAOService USER_SERVICE = UserDAO.getInstance();
    RoleDAOService ROLE_SERVICE = RoleDAO.getInstance();

    public UserManagerBean() {
        this.facesContext = FacesContext.getCurrentInstance();
        this.user = new User();
    }

    public String update1(User user1) {
        this.user = user1;
        return "edit_user";
    }

    public List<User> getAllUser() {
        listUser = USER_SERVICE.getAllUser();
        return listUser;
    }

    public List<User> getUserActive() {
        listUser = USER_SERVICE.getUserActive(this.user.getActive());
        return listUser;
    }

    public void addUser(ActionEvent event) {
        String msg = "";

        System.out.println("Username: " + this.user.getUserName());
        System.out.println("Full name: " + this.user.getFullName());
        System.out.println("Birthday: " + this.user.getBirthday());
        System.out.println("Gender: " + this.user.getGender());
        System.out.println("Email: " + this.user.getEmail());
        System.out.println("Address: " + this.user.getAddress());
        System.out.println("Phone: " + this.user.getPhone());
        System.out.println("ID Card: " + this.user.getIdCard());
        System.out.println("Role: " + this.user.getRole().getRoleID());

//        Role roleID = ROLE_SERVICE.getRoleByID(this.user.getRole().getRoleID());
//        String pwd = util.Support.encryptMD5("123456");
//        User addUser = new User(1, this.user.getUserName(), pwd, this.user.getFullName(), this.user.getBirthday(),
//                this.user.getGender(), this.user.getIdCard(), this.user.getAddress(), this.user.getEmail(), this.user.getPhone(),
//                null, roleID, null, 1);
//        if (USER_SERVICE.createUser(addUser)) {
//            msg = "Add user successfully!";
//        } else {
            msg = "Add user failed!";
//        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void editUser(ActionEvent event) {
        String msg = "";
//        System.out.println("User id: " + this.user.getUserID());
//        System.out.println("Username: " + this.user.getUserName());
//        System.out.println("Full name: " + this.user.getFullName());
//        System.out.println("Birthday: " + this.user.getBirthday());
//        System.out.println("Gender: " + this.user.getGender());
//        System.out.println("Email: " + this.user.getEmail());
//        System.out.println("Address: " + this.user.getAddress());
//        System.out.println("Phone: " + this.user.getPhone());
//        System.out.println("ID Card: " + this.user.getIdCard());
//        System.out.println("Role: " + this.user.getRole().getRoleID());
        Role roleID = ROLE_SERVICE.getRoleByID(this.user.getRole().getRoleID());
        User editUser = new User(this.user.getUserID(), this.user.getUserName(), null, this.user.getFullName(), this.user.getBirthday(),
                this.user.getGender(), this.user.getIdCard(), this.user.getAddress(), this.user.getEmail(), this.user.getPhone(),
                null, roleID, null, 1);
        if (USER_SERVICE.updateProfile(editUser)) {
            msg = "Edit user successfully!";
        } else {
            msg = "Edit user failed!";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Reset password
     *
     * @param event
     */
    public void resetPass(ActionEvent event) {
        String msg;
        System.out.println(this.user.getUserID());
        String pwd = util.Support.encryptMD5("123456");
        if (USER_SERVICE.resetPass(this.user.getUserID(), pwd)) {
            msg = "Reset password successfully!";
        } else {
            msg = "Reset password failed!";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Delete user
     *
     * @param event
     */
    public void deleteUser(ActionEvent event) {
        String msg;
        if (USER_SERVICE.deleteUser(this.user.getUserID())) {
            msg = "Deleted user successfully!";
        } else {
            msg = "Delete user failed!";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Set status ban for user
     *
     * @param event
     */
    public void removeUser(ActionEvent event) {
        String msg;
        if (USER_SERVICE.removeUser(this.user.getUserID())) {
            msg = "User ban succesfully!";
        } else {
            msg = "User ban failed!";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Set status restore for user
     *
     * @param event
     */
    public void restoreUser(ActionEvent event) {
        String msg;
        if (USER_SERVICE.restoreUser(this.user.getUserID())) {
            msg = "User restore succesfully!";
        } else {
            msg = "User restore failed!";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void makeAdmin(ActionEvent event) {
        String msg;
        System.out.println(this.user.getUserID());
        System.out.println(this.user.getUserName());
//        if (USER_SERVICE.makeAdmin(this.user.getUserID())) {
//            msg = "Make administrator succesfully!";
//        } else {
        msg = "Make administrator failed!";
//        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the searchUser
     */
    public List<User> getSearchUser() {
        return searchUser;
    }

    /**
     * @param searchUser the searchUser to set
     */
    public void setSearchUser(List<User> searchUser) {
        this.searchUser = searchUser;
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        roles = ROLE_SERVICE.getRoles();
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
