/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.olddao.ProjectDAO;
import model.olddao.ProjectUserDAO;
import model.olddao.UserDAO;
import model.olddao.service.ProjectDAOService;
import model.olddao.service.ProjectUserDAOService;
import model.olddao.service.UserDAOService;
import model.oldentities.Project;
import model.oldentities.ProjectUserDetails;
import model.oldentities.User;
import org.primefaces.model.DualListModel;

/**
 *
 * @author cong0_000
 */
@ManagedBean(name = "projectUserBean")
@RequestScoped
public class UserProjectBean {

    private final UserDAOService USER_SERVICE = UserDAO.getInstance();
    private final ProjectDAOService PROJECT_SERVICE = ProjectDAO.getInstance();
    private final ProjectUserDAOService PU_SERVICE = ProjectUserDAO.getInstance();
    private Project project;
    private List<User> userJoining;
    private List<User> userNotJoin;

    public UserProjectBean() {
    }

    public Project getProject() {
        if (project == null) {
            project = PROJECT_SERVICE.getProjectByID(1);
        }
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<User> getUserJoining() {
        userJoining = PU_SERVICE.getUserByProject(getProject().getProjectID());
        return userJoining;
    }

    public void setUserJoining(List<User> userJoining) {
        this.userJoining = userJoining;
    }

    public List<User> getUserNotJoin() {
        return userNotJoin;
    }

    public void setUserNotJoin(List<User> userNotJoin) {
        this.userNotJoin = userNotJoin;
    }

}
