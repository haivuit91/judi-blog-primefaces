/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.dao.RoleDAO;
import model.dao.UserDAO;
import model.dao.service.RoleDAOService;
import model.dao.service.UserDAOService;
import model.entities.Role;
import model.entities.User;

/**
 *
 * @author cong0_000
 */
@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {

    private final UserDAOService USER_SERVICE = UserDAO.getInstance();
    private final RoleDAOService Role_SERVICE = RoleDAO.getInstance();
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
        Date birthOfDay = user.getBirthday();
        int gender = user.getGender();
        String idCard = user.getIdCard();
        String address = user.getAddress();
        String email = user.getEmail();
        String phone = user.getPhone();

        String imagePath;
        if (user.getGender() == 1) {
            imagePath = "images/avartar/avatar_male.jpg";
        } else {
            imagePath = "images/avartar/avatar_female.jpg";
        }
        Role r = Role_SERVICE.getRoleByID(4);

        String idActive = util.Support.encryptMD5(userName + new Date().toString());

        User user11 = new User(11, userName, pwd, fullName, birthOfDay, gender, idCard, address, email, phone, imagePath, r, idActive, 0);
        if (!USER_SERVICE.checkUser(userName)) {
            if (!USER_SERVICE.checkEmail(email)) {
                if (USER_SERVICE.createUser(user11)) {
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

            msg = "profile updated successfully!";

        } else {
            msg = "Update profile failed !";
        }
         this.addMessages(msg);
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
