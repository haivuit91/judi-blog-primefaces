/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import model.dao.ProjectDAOImpl;
import model.dao.ProjectTypeDAOImpl;
import model.dao.ProjectUserDAOImpl;
import model.dao.UserDAOImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.dao.service.ProjectDAO;
import model.dao.service.ProjectUserDAO;
import model.dao.service.TypeDAO;
import model.dao.service.UserDAO;
import model.entities.Project;
import model.entities.ProjectUserDetails;
import model.entities.Type;
import model.entities.User;

/**
 *
 * @author windows 8.1
 */
@ManagedBean
@RequestScoped
public class ProjectBean {

    private final ProjectDAO PROJECT_SERVICE = ProjectDAOImpl.getInstance();
    private final TypeDAO TYPE_SERVICE = ProjectTypeDAOImpl.getInstance();
    private final ProjectUserDAO PU_SERVICE = ProjectUserDAOImpl.getInstance();
    private final UserDAO USER_SERVICE = UserDAOImpl.getInstance();

    private Project project = new Project();
    private Type projectType;
    private List<Type> types;
    private List<Project> projects = null;
    private List<Project> filteredProjects;

    private List<User> users;

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
        
        int userID = util.Support.getCurrentUser().getUserId();
        User user11 = USER_SERVICE.getUserByID(userID);
        List<Project> myProjects = PU_SERVICE.getMyProject(user11);
        
        return myProjects;
    }

    /**
     *
     * @param p the project
     * @return users not joining to project p yet.
     */
    public List<User> getUserNameNotJoined(Project p) {
        List<User> userLists = USER_SERVICE.getUsers();
        List<String> usersName = new ArrayList<>();
        for (User user1 : userLists) {
            usersName.add(user1.getUserName());
        }
        List<User> usersJoined = p.getUsers();

        List<String> usersNameJoined = new ArrayList<>();
        for (User user2 : usersJoined) {
            usersNameJoined.add(user2.getUserName());
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
        List<ProjectUserDetails> puList = PU_SERVICE.getPUByProject(p);
        String creator = "";
        for (ProjectUserDetails projectUserDetails : puList) {
            if (projectUserDetails.isCreator()) {
                creator = projectUserDetails.getUser().getUserName();
            }
        }
        return creator;
    }

    /**
     * Create new project
     *
     * @param event
     */
    public void createProject(ActionEvent event) {
        String msg = "";
        Type t = this.project.getType();
        String projectName = this.project.getProjectName();
        String description = this.project.getDescription();
        Date startDate = this.project.getStartDate();
        int duration = this.project.getDuration();
        
        Project newProject = new Project(t, projectName, description, startDate, duration, true, null);
        if (PROJECT_SERVICE.createProject(newProject)) {
            User currentUser = util.Support.getCurrentUser();
            if (currentUser == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Invalid Login!", "Please try again!"));
            } else {
                List<Project> projectList = PROJECT_SERVICE.getProjectByName(newProject.getProjectName());
                Project pro = projectList.get(projectList.size() - 1);
                ProjectUserDetails pud = new ProjectUserDetails(pro, currentUser, true);
                if (PU_SERVICE.createPUD(pud)) {
//                    pro.getUsers().add(currentUser);
//                    t.getProjects().add(pro);
//                    currentUser.getProjects().add(pro);
                    msg += "Project created successfully by: " + currentUser.getFullName() + "\n";
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

    /**
     * Update the project
     *
     * @param event
     */
    public void updateProject(ActionEvent event) {
        String msg;
        int projectID = this.project.getProjectId();
        String projectName = this.project.getProjectName();
        String description = this.project.getDescription();
        Date startDate = this.project.getStartDate();
        int duration = this.project.getDuration();
        Type type = this.project.getType();
        boolean active = getProject().isActive();
        List<User> puList = this.project.getUsers();
        Project pro = new Project(projectID, type, projectName, description, startDate, duration, active, puList);
        if (PROJECT_SERVICE.updateProject(pro)) {
            msg = "Project updated successfully!";
        } else {
            msg = "Update project failed !";
        }
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
        int projectID = this.project.getProjectId();
        Project p = PROJECT_SERVICE.getProjectByID(projectID);
        if (PU_SERVICE.deletePUByProject(p)) {
            if (PROJECT_SERVICE.deleteProject(p)) {
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
        if (PROJECT_SERVICE.activeProject(this.project)) {
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
        if (PROJECT_SERVICE.inactiveProject(this.project)) {
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
    public Type getType() {
        return projectType;
    }

    /**
     * @param projectType the projectType to set
     */
    public void setType(Type projectType) {
        this.projectType = projectType;
    }

    /**
     * @return the types
     */
    public List<Type> getTypes() {
        types = TYPE_SERVICE.getTypes();
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(List<Type> types) {
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
