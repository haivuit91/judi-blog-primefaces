/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import hibernate.HibernateUtil;
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
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author HAI VU
 */
@ManagedBean(name = "userManager", eager = true)
@RequestScoped
public class UserManagerBean implements Serializable {

    private User user;
    private final FacesContext facesContext;
    private HibernateUtil hu;
    private Session session;
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
        return getListUser();
    }

    public List<User> getUserActive() {
        List<User> users1 = USER_SERVICE.getUsers();
        getListUser().clear();
        for (User user1 : users1) {
            if (user1.isActive()) {
                getListUser().add(user1);
            }
        }
        return getListUser();
    }

    public void addUser(ActionEvent event) {
        String msg = "";
        String pwd = util.Support.encryptMD5("123456");
        String imagePath;
        if (user.isGender()) {
            imagePath = "images/avartar/avatar_male.jpg";
        } else {
            imagePath = "images/avartar/avatar_female.jpg";
        }
        User addUser = new User(1, this.user.getRole(), this.user.getUserName(), pwd, this.user.getFullName(), this.user.getBirthOfDay(),
                this.user.isGender(), this.user.getIdCard(), this.user.getAddress(), this.user.getEmail(), this.user.getPhoneNumber(),
                imagePath, null, true);
        if (!USER_SERVICE.checkUser(this.user.getUserName())) {
            if (!USER_SERVICE.checkEmail(this.user.getEmail())) {
                if (USER_SERVICE.createUser(addUser)) {
                    msg = "Add user successfully!";
                } else {
                    msg = "Add user failed!";
                }
            } else {
                msg = "Email is existed!";
            }
        } else {
            msg = "Username is existed!";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void editUser(ActionEvent event) {
        String msg = "";
        User editUser = new User(this.user.getUserId(), this.user.getRole(), this.user.getUserName(), this.user.getPwd(), this.user.getFullName(),
                this.user.getBirthOfDay(), this.user.isGender(), this.user.getIdCard(), this.user.getAddress(), this.user.getEmail(),
                this.user.getPhoneNumber(), this.user.getImagePath(), null, this.user.isActive());
        if (USER_SERVICE.updateUserByAdmin(editUser)) {
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
        if (USER_SERVICE.deleteUser(this.user)) {
            msg = "Deleted use succesfully!";
        } else {
            msg = "Deleted use failed!";
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
        if (USER_SERVICE.makeAdmin(this.user)) {
            msg = "Make administrator succesfully!";
        } else {
            msg = "Make administrator failed!";
        }
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

    /**
     * @return the listUser
     */
    public List<User> getListUser() {
        listUser = USER_SERVICE.getUsers();
        return listUser;
    }
}
