/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.util.Date;
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
import model.entities.Post;
import model.entities.Project;
import model.entities.Role;
import model.entities.User;

/**
 *
 * @author cong0_000
 */
@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {

    private final UserDAO USER_SERVICE = UserDAOImpl.getInstance();
    private final RoleDAO Role_SERVICE = RoleDAOImpl.getInstance();
    private User user;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    public String createUser(ActionEvent event) {
        String page = "/module/registration.jsf";
        String msg;

        String userName = user.getUserName();
        String pwd = util.Support.encryptMD5(user.getPwd());
        String fullName = user.getFullName();
        Date birthOfDay = user.getBirthOfDay();
        boolean gender = user.isGender();
        String idCard = user.getIdCard();
        String address = user.getAddress();
        String email = user.getEmail();
        String phone = user.getPhoneNumber();

        String imagePath;
        if (user.isGender()) {
            imagePath = "images/avatar/avatar_male.jpg";
        } else {
            imagePath = "images/avarar/avatar_female.jpg";
        }
        Role r = Role_SERVICE.getRoleByID(2);
        List<Project> projects = this.user.getProjects();
        List<Post> posts = this.user.getPosts();
        String idActive = util.Support.encryptMD5(userName + new Date().toString());
        User user11 = new User(r, userName, pwd, fullName, birthOfDay, gender, idCard, address, email, phone, imagePath, idActive, false, posts, projects);
        if (!USER_SERVICE.checkUser(userName)) {
            if (!USER_SERVICE.checkEmail(email)) {
                if (USER_SERVICE.createUser(user11)) {
//                    r.getUsers().add(user11);
                    util.Support.sendMail(email, idActive);
                    msg = "Account created successfully!";
                    page = "/module/success.jsf";
                } else {
                    msg = "Account create failed!";
                }
            } else {
                msg = "Email is existed!";
            }
        } else {
            msg = "Username is existed!";
        }
        this.addMessages(msg);
        return page;
    }

    public void addMessages(String msg) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance()
                .addMessage(null, fm);
    }

    public void editUser(ActionEvent event) {
        String msg;
        if (USER_SERVICE.updateProfile(this.user)) {
            msg = "Edit user successfully!";
        } else {
            msg = "Edit user failed!";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the user
     */
    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
