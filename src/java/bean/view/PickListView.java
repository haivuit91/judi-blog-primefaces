/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.dao.ProjectDAOImpl;
import model.dao.ProjectUserDAOImpl;
import model.dao.UserDAOImpl;
import model.dao.service.ProjectDAO;
import model.dao.service.ProjectUserDAO;
import model.dao.service.UserDAO;
import model.entities.Project;
import model.entities.ProjectUserDetails;
import model.entities.User;
import org.primefaces.model.DualListModel;

/**
 *
 * @author cong0_000
 */
@ManagedBean
@RequestScoped
public class PickListView {
    
    private final ProjectDAO PROJECT_SERVICE = ProjectDAOImpl.getInstance();
    private final ProjectUserDAO PU_SERVICE = ProjectUserDAOImpl.getInstance();
    private final UserDAO USER_SERVICE = UserDAOImpl.getInstance();
    
    private int projectID;
    private Project project;
    private DualListModel<User> users = new DualListModel<>();
    private List<User> usersJoined = null;
    
    @PostConstruct
    public void init() {
        List<User> userSource = new ArrayList<User>();
        List<User> userTarget = new ArrayList<User>();
        
        List<User> usersList = USER_SERVICE.getUsers();
        for (User user : usersList) {
            if (user.isActive()) {
                userSource.add(user);
            }
        }
        
        users = new DualListModel<User>(userSource, userTarget);
    }
    
    public void addUsersJoinProject(ActionEvent event) {
        String msg="";
        if (!usersJoined.isEmpty()) {
            if (PU_SERVICE.deletePUByProject(getProject())) {
                for (User user : usersJoined) {
                    ProjectUserDetails pud = new ProjectUserDetails(getProject(), user, false);
                    PU_SERVICE.createPUD(pud);
                }
            }else{
                msg += "failed";
            }
            msg += "Success";
        } else {
            msg += "Failed";
        }
        this.addMessages(msg);
    }
    
    public void addMessages(String msg) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance()
                .addMessage(null, fm);
    }

    /**
     * @return the project
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     * @param projectID the project to set
     */
    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    /**
     * @return the users
     */
    public DualListModel<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(DualListModel<User> users) {
        this.users = users;
    }

    /**
     * @return the usersJoined
     */
    public List<User> getUsersJoined() {
        return usersJoined;
    }

    /**
     * @param usersJoined the usersJoined to set
     */
    public void setUsersJoined(List<User> usersJoined) {
        this.usersJoined = usersJoined;
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
