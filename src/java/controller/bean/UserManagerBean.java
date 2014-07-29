/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.dao.RoleDAOImpl;
import model.dao.UserDAOImpl;
import model.dao.service.RoleDAO;
import model.dao.service.UserDAO;
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

    UserDAO USER_SERVICE = UserDAOImpl.getInstance();
    RoleDAO ROLE_SERVICE = RoleDAOImpl.getInstance();

    public UserManagerBean() {
        this.facesContext = FacesContext.getCurrentInstance();
        this.user = new User();
    }

    public String update1(User user1) {
        this.user = user1;
        return "edit_user";
    }

    public List<User> getAllUser() {
        listUser = USER_SERVICE.getUsers();
        return listUser;
    }

    public List<User> getUserActive() {
        List<User> users1 = USER_SERVICE.getUsers();
        listUser.clear();
        for (User user1 : users1) {
            if(user1.isActive()){
                listUser.add(user1);
            }
        }
        return listUser;
    }

    public void addUser(ActionEvent event) {
        String msg = "";

        System.out.println("Username: " + this.user.getUserName());
        System.out.println("Full name: " + this.user.getFullName());
        System.out.println("Birthday: " + this.user.getBirthOfDay());
        System.out.println("Gender: " + this.user.isGender());
        System.out.println("Email: " + this.user.getEmail());
        System.out.println("Address: " + this.user.getAddress());
        System.out.println("Phone: " + this.user.getPhoneNumber());
        System.out.println("ID Card: " + this.user.getIdCard());
        System.out.println("Role: " + this.user.getRole().getRoleId());

//        Role roleID = ROLE_SERVICE.getRoleByID(this.user.getRole().getRoleId());
//        String pwd = util.Support.encryptMD5("123456");
//        User addUser = new User(1, this.user.getUserName(), pwd, this.user.getFullName(), this.user.getBirthday(),
//                this.user.isGender(), this.user.getIdCard(), this.user.getAddress(), this.user.getEmail(), this.user.getPhone(),
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
//        System.out.println("User id: " + this.user.getUserId());
//        System.out.println("Username: " + this.user.getUserName());
//        System.out.println("Full name: " + this.user.getFullName());
//        System.out.println("Birthday: " + this.user.getBirthday());
//        System.out.println("Gender: " + this.user.isGender());
//        System.out.println("Email: " + this.user.getEmail());
//        System.out.println("Address: " + this.user.getAddress());
//        System.out.println("Phone: " + this.user.getPhone());
//        System.out.println("ID Card: " + this.user.getIdCard());
//        System.out.println("Role: " + this.user.getRole().getRoleId());
        Role roleID = ROLE_SERVICE.getRoleByID(this.user.getRole().getRoleId());
        User editUser = new User(user.getUserId(), user.getRole(), msg, msg, msg, null, true, msg, msg, msg, msg, msg, msg, true);
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
//        String msg;
//        System.out.println(this.user.getUserId());
//        String pwd = util.Support.encryptMD5("123456");
//        if (USER_SERVICE.resetPass(this.user.getUserId(), pwd)) {
//            msg = "Reset password successfully!";
//        } else {
//            msg = "Reset password failed!";
//        }
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Delete user
     *
     * @param event
     */
    public void deleteUser(ActionEvent event) {
        String msg;
        if (USER_SERVICE.deleteUser(this.user.getUserId())) {
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
        if (USER_SERVICE.removeUser(this.user)) {
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
        if (USER_SERVICE.restoreUser(this.user)) {
            msg = "User restore succesfully!";
        } else {
            msg = "User restore failed!";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void makeAdmin(ActionEvent event) {
        String msg;
        System.out.println(this.user.getUserId());
        System.out.println(this.user.getUserName());
//        if (USER_SERVICE.makeAdmin(this.user.getUserId())) {
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
