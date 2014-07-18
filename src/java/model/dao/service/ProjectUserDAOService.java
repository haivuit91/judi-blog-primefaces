/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.service;

import java.util.List;
import model.entities.Project;
import model.entities.ProjectUserDetails;
import model.entities.User;

/**
 *
 * @author Admin
 */
public interface ProjectUserDAOService {

    /**
     * get All list project user
     *
     * @return ProjectUserDetails list
     */
    public List<ProjectUserDetails> getAllPUList();

    /**
     * get ProjectUserDetails by id
     *
     * @param puID
     * @return ProjectUserDetails
     */
    public ProjectUserDetails getPUByID(int puID);
    /**
     * get ProjectUserDetails by id
     *
     * @param projectID
     * @return ProjectUserDetails
     */
    public List<ProjectUserDetails> getPUByProjectID(int projectID);

    /**
     * get list project by user
     *
     * @param userID id of User
     * @return Project list
     */
    public List<Project> getProjectByUser(int userID);

    /**
     * get list project by user
     *
     * @param projectID id of Project
     * @return Project list
     */
    public List<User> getUserByProject(int projectID);

    /**
     * get list project by user
     *
     * @param projectID id of Project
     * @return Project list
     */
    public List<User> getUserNotJoin(int projectID);

    /**
     * check is exist of user in one project
     *
     * @param project Object project
     * @param user Object User
     * @return
     */
    
    public boolean checkJoinUser(User user, Project project);
    public boolean isExistUserInProject(Project project, User user);

    /**
     * Create new PUD
     *
     * @param pud Object ProjectUserDetails
     * @return true if create new successfully
     */
    public boolean createPUD(ProjectUserDetails pud);

    /**
     * update PUD
     *
     * @param pud Object ProjectUserDetails
     * @return true if update successfully
     */
    public boolean updatePUD(ProjectUserDetails pud);

    /**
     * Delete PUD
     *
     * @param projectID id of Project
     * @return true if delete successfully
     */
    public boolean deleteUserJoinedProject(int projectID);
    /**
     * Delete PUD
     *
     * @param projectID id of Project
     * @return true if delete successfully
     */
    public boolean deletePUByProjectID(int projectID);
    /**
     * Delete PUD
     * @param project is Object Project
     * @param user is Object User
     * @return true if delete successfully
     */
    public boolean removeUserLeaProject(User user, Project project);
}
