/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.dao.ProjectDAO;
import model.dao.ProjectTypeDAO;
import model.dao.ProjectUserDAO;
import model.dao.UserDAO;
import model.dao.service.ProjectDAOService;
import model.dao.service.ProjectTypeDAOService;
import model.dao.service.ProjectUserDAOService;
import model.dao.service.UserDAOService;
import model.entities.Project;
import model.entities.ProjectType;
import model.entities.ProjectUserDetails;
import model.entities.User;
import org.primefaces.model.DualListModel;

/**
 *
 * @author windows 8.1
 */
@ManagedBean
@RequestScoped
public class ProjectBean {

    private final ProjectDAOService PROJECT_SERVICE = ProjectDAO.getInstance();
    private final ProjectTypeDAOService TYPE_SERVICE = ProjectTypeDAO.getInstance();
    private final ProjectUserDAOService PU_SERVICE = ProjectUserDAO.getInstance();
    private final UserDAOService USER_SERVICE = UserDAO.getInstance();

    private Project project;
    private ProjectType projectType;
    private List<ProjectType> types;
    private List<Project> projects;
    private List<Project> filteredProjects;

    private List<User> users;
    private DualListModel<User> usersDualList = new DualListModel<User>(); //DualList of Pick list add user to project

    private List<User> usersJoining;

    List<User> usersSource = new ArrayList<User>();
    List<User> usersTarget = new ArrayList<User>();
    private User user = new User();

    public ProjectBean() {
        if (project == null) {
            project = new Project();
        }
    }

    public List<Project> getProjects() {
        projects = PROJECT_SERVICE.getProjects();
        return projects;
    }

    /**
     *
     * @return project list of user
     */
    public List<Project> getProjectsByUser() {
        List<Project> projectsByUser = PU_SERVICE.getProjectByUser(util.Support.getCurrentUser().getUserID());
        return projectsByUser;
    }

    /**
     *
     * @param p the project
     * @return users not joining to project p yet.
     */
    public List<User> getUserNameNotJoined(Project p) {
        List<User> userLists = USER_SERVICE.getAllUser();
        List<String> usersName = new ArrayList<>();
        for (User user : userLists) {
            usersName.add(user.getUserName());
        }
        List<User> usersJoined = PU_SERVICE.getUserByProject(p.getProjectID());
        List<String> usersNameJoined = new ArrayList<>();
        for (User user : usersJoined) {
            usersNameJoined.add(user.getUserName());
        }
        usersName.removeAll(usersNameJoined);
        List<User> userList = new ArrayList<>();
        for (String userName : usersName) {
            User u = USER_SERVICE.getUserByUserName(userName);
            userList.add(u);
        }
        return userList;
    }

    /**
     * method get user name creating project
     *
     * @param p the project
     * @return
     */
    public String getCreater(Project p) {
        List<ProjectUserDetails> puList = PU_SERVICE.getPUByProjectID(p.getProjectID());
        String creater = "";
        for (ProjectUserDetails projectUserDetails : puList) {
            if (projectUserDetails.getCreater() == 1) {
                creater = projectUserDetails.getUser().getUserName();
            }
        }
        return creater;
    }

    /**
     * Create new project
     *
     * @param event
     */
    public void createProject(ActionEvent event) {
        String msg = "";
        if (PROJECT_SERVICE.createProject(this.project)) {
            User user = util.Support.getCurrentUser();
            if (user == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Invalid Login!", "Please try again!"));
            } else {
                List<Project> projectList = PROJECT_SERVICE.getProjectByName(this.project.getProjectName());
                Project pro = projectList.get(projectList.size() - 1);
                ProjectUserDetails pud = new ProjectUserDetails(1, user, pro, 1);
                if (PU_SERVICE.createPUD(pud)) {
                    msg += "Project created successfully by: " + user.getFullName() + "\n";
                } else {
                    msg += "Create creater for project failed. \n";
                }
            }
        } else {
            msg = "Create project failed !";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");

        FacesContext.getCurrentInstance()
                .addMessage(null, message);
    }

    public void saveUpdate(Project project) {
        this.project = project;
    }

    /**
     * Update the project
     *
     * @param event
     */
    public void updateProject(ActionEvent event) {
        String msg;
//        if (PROJECT_SERVICE.updateProject(this.project)) {
//            msg = "Project updated successfully!";
//        } else {
//            msg = "Update project failed !";
//        }
        int projectID = getProject().getProjectID();
        String projectName = getProject().getProjectName();
        String description = getProject().getDescription();
        Date startDate = getProject().getStartDate();
        java.sql.Date date = new java.sql.Date(startDate.getTime());
        int duration = getProject().getDuration();
        ProjectType type = this.projectType;
        int active = getProject().getActive();
        Project pro = new Project(projectID, projectName, description, date, duration, type, active);
        if (PROJECT_SERVICE.updateProject(pro)) {
            msg = "Project updated successfully!";
        }
        msg = "Update project failed !";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Delete project
     *
     * @param event
     */
    public void deleteProject(ActionEvent event) {
        String msg;
        int projectID = this.project.getProjectID();
        if (PU_SERVICE.deletePUByProjectID(projectID)) {
            if (PROJECT_SERVICE.deleteProject(projectID)) {
                msg = "Project deleted successfully!";
            } else {
                msg = "Delete project failed !";
            }
        } else {
            msg = "Delete project failed !";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Set status active for project
     *
     * @param event
     */
    public void activeProject(ActionEvent event) {
        String msg;
        if (PROJECT_SERVICE.activeProject(this.project.getProjectID())) {
            msg = "Project actived succesfully !";
        } else {
            msg = "Project actived failed !";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Set status inactive for project
     *
     * @param event
     */
    public void inactiveProject(ActionEvent event) {
        String msg;
        if (PROJECT_SERVICE.inactiveProject(this.project.getProjectID())) {
            msg = "Project inactived succesfully !";
        } else {
            msg = "Project inactived failed !";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the projectType
     */
    public ProjectType getProjectType() {
        return projectType;
    }

    /**
     * @param projectType the projectType to set
     */
    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    /**
     * @return the types
     */
    public List<ProjectType> getTypes() {
        types = TYPE_SERVICE.getTypes();
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(List<ProjectType> types) {
        this.types = types;
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

        //
        usersSource = getUserNameNotJoined(project);
        usersTarget = PU_SERVICE.getUserByProject(project.getProjectID());

        usersDualList = new DualListModel<User>(usersSource, usersTarget);
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * @return the filteredProjects
     */
    public List<Project> getFilteredProjects() {
        return filteredProjects;
    }

    /**
     * @param filteredProjects the filteredProjects to set
     */
    public void setFilteredProjects(List<Project> filteredProjects) {
        this.filteredProjects = filteredProjects;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return the usersDualList
     */
    public DualListModel<User> getUsersDualList() {
        return usersDualList;
    }

    /**
     * @param usersDualList the usersDualList to set
     */
    public void setUsersDualList(DualListModel<User> usersDualList) {
        this.usersDualList = usersDualList;
    }

    /**
     * @return the usersJoining
     */
    public List<User> getUsersJoining() {
        return usersJoining;
    }

    /**
     * @param usersJoining the usersJoining to set
     */
    public void setUsersJoining(List<User> usersJoining) {
        this.usersJoining = usersJoining;
    }

    public List<Project> getAllProject() {
        List<Project> projectList = PROJECT_SERVICE.getProjects();
        return projectList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

}
