/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.dao.UserDAOImpl;
import model.dao.service.UserDAO;
import model.entities.User;

/**
 *
 * @author Khoa
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    private final UserDAO USER_SERVICE = UserDAOImpl.getInstance();
    private User user = new User();

    public String checkLogin() {
        String page = "/index.jsf?faces-redirect=true";
        String userName = this.user.getUserName();
        String password = util.Support.encryptMD5(this.user.getPwd());
        if (USER_SERVICE.checkLogin(userName, password)) {
            User u = USER_SERVICE.getUserByUserName(userName);
            if (u.isActive()) {
                this.addMessages("Login successfully!");
                HttpSession session = util.Support.getSession();
                session.setAttribute(util.Constants.CURRENT_USER, u);
            } else {
                page = "/module/success.jsf";
            }
            System.out.println("OK");
        } else {
            System.out.println(userName);
            System.out.println(password);
            System.out.println("Failed");
            this.addMessages("Username or password wrong !!!");
        }
        
        return page;
    }

    public String logout() {
        HttpSession session = util.Support.getSession();
        session.invalidate();
        return "/index.jsf?faces-redirect=true";
    }

    public void addMessages(String msg) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance()
                .addMessage(null, fm);
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

}
