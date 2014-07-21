/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

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
import model.entities.Project;
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
    private String searchbyUser;
    private final HttpServletRequest request;
    private final FacesContext fc;
    private final HttpSession session;
    private FacesMessage facesMessage;
    private final Map<String, String> params;
     private List<User> filteredUser;
      private List<Role> role;
        
    
    UserDAOService USER_SERVICE = UserDAO.getInstance();
    RoleDAOService ROLE_SERVICE = RoleDAO.getInstance();

    public UserManagerBean() {
        fc = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) fc.getExternalContext().getRequest();
        params = fc.getExternalContext().getRequestParameterMap();
        session = request.getSession(true);
        this.user = new User();
    }

    public String update1(User user1) {
        this.user = user1;
        return "edit_user";
    }

    public List<User> getAllUser() {
        List<User> userList = USER_SERVICE.getAllUser();

        return userList;
    }

    public List<User> search() {
        List<User> userList = USER_SERVICE.findUserByUserName(searchbyUser);;
        return userList;
    }

    /**
     * @return the searchbyUsername
     */
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
     * @return the role
     */
    public List<Role> getListRole() {
        return ROLE_SERVICE.getRoles();

    }

    public String edituser() {

        int userID = getUser().getUserID();
        String username = getUser().getUserName();
        String fullname = getUser().getFullName();
        String address = getUser().getAddress();
        String email = getUser().getEmail();
        String phone = getUser().getPhone();
        int role = getUser().getRole().getRoleID();
        Role roleID = ROLE_SERVICE.getRoleByID(role);
        Date birthday = getUser().getBirthday();
        java.sql.Date date = new java.sql.Date(birthday.getTime());
        int gender = getUser().getGender();
        String idcard = getUser().getIdCard();
        User user = new User(userID, username,null, fullname, date, gender, idcard, address, email, phone, null, roleID, null, 1);
        if (USER_SERVICE.updateProfile(user)) {

         

            return "users_manager";

        } else {
            return "edit_user";
        }

    }

    public String adduser() {
        String msg = "";
        String username = getUser().getUserName();
        String fullname = getUser().getFullName();
        String newpass = getUser().getPwd();
        String address = getUser().getAddress();
        String email = getUser().getEmail();
        String phone = getUser().getPhone();
        int role = getUser().getRole().getRoleID();
        Role roleID = ROLE_SERVICE.getRoleByID(role);
        Date birthday = getUser().getBirthday();
        java.sql.Date date = new java.sql.Date(birthday.getTime());
        int gender = getUser().getGender();
        String idcard = getUser().getIdCard();

        User user11 = new User(1, username, newpass, fullname, date, gender, idcard, address, email, phone, null, roleID, null, 1);
        if (USER_SERVICE.createUser(user11)) {
            return "users_manager";

        } else {
//            msg += " Failed";
            return "add_edit_user";
        }

    }

    public String createUser() {
        String msg = "";
        String username = getUser().getUserName();
        String fullname = getUser().getFullName();
        String newpass = getUser().getPwd();
        String address = getUser().getAddress();
        String email = getUser().getEmail();
        String phone = getUser().getPhone();
        Date birthday = getUser().getBirthday();
        java.sql.Date date = new java.sql.Date(birthday.getTime());
        int gender = getUser().getGender();
        String idcard = getUser().getIdCard();

        User user11 = new User(1, username, newpass, fullname, date, gender, idcard, address, email, phone, null, ROLE_SERVICE.getRoleByID(4), null, 1);
        if (USER_SERVICE.createUser(user11)) {
            return "/Judi-PrimeFaces/index.jsf";
        } else {
//            msg += " Failed";
            return "/Judi-PrimeFaces/module/registration.jsf";
        }

    }

    /**
     * @param event
     */
     public void deleteUser(ActionEvent event) {
        String msg;
        int UserID = this.user.getUserID();
        if (USER_SERVICE.deleteUser(UserID)) {
            msg = "Delete  succesfully !";
        }
        msg = "Delete  failed !";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    /**
     * @return the searchbyUser
     */
    public String getSearchbyUser() {
        return searchbyUser;
    }

    /**
     * @param searchbyUser the searchbyUser to set
     */
    public void setSearchbyUser(String searchbyUser) {
        this.searchbyUser = searchbyUser;
    }

    /**
     * @return the filteredUser
     */
    public List<User> getFilteredUser() {
        return filteredUser;
    }

    /**
     * @param filteredUser the filteredUser to set
     */
    public void setFilteredUser(List<User> filteredUser) {
        this.filteredUser = filteredUser;
    }

    /**
     * @return the role
     */
    public List<Role> getRole() {
         role = ROLE_SERVICE.getRoles();
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(List<Role> role) {
        this.role = role;
    }


}
