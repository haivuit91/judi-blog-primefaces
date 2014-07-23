/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.dao.UserDAO;
import model.dao.service.UserDAOService;
import model.entities.Project;
import model.entities.User;
import org.primefaces.model.DualListModel;

/**
 *
 * @author cong0_000
 */
@ManagedBean (name = "projectUserBean")
@RequestScoped
public class UserProjectBean {

    private final UserDAOService USER_SERVICE = UserDAO.getInstance();
    private DualListModel<String> users = new DualListModel<String>();
    private Project project;

    @PostConstruct
    public void init() {
        
    }

    private List<String> getUsersSource() {
        List<User> userList = USER_SERVICE.getAllUser();
        List<String> userSource = new ArrayList<String>();
        for (User user : userList) {
            if (user.getActive() == 1) {
                userSource.add(user.getUserName());
            }
        }
        return userSource;
    }

    public DualListModel<String> getUsers() {
//        init();
        List<String> usersSource = getUsersSource();
        List<String> usersTarget = new ArrayList<String>();

        users = new DualListModel<String>(usersSource, usersTarget);
        return users;
    }

    public void setUsers(DualListModel<String> users) {

        this.users = users;

    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }
}
