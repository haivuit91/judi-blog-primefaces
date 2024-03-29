/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao.service;

import model.entities.Project;
import model.entities.ProjectUserDetails;
import model.entities.User;
import java.util.List;

/**
 *
 * @author cong0_000
 */
public interface ProjectUserDAO {
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
     * @param project
     * @return ProjectUserDetails
     */
    public List<ProjectUserDetails> getPUByProject(Project project);

    /**
     * get list project by user
     *
     * @param user id of User
     * @return Project list
     */
    public List<Project> getMyProject(User user);

    /**
     * get list project by user
     *
     * @param user id of User
     * @return Project list
     */
    public List<Project> getProjectsByUser(User user);
    
    /**
     * get list user by project
     *
     * @param project instance of Project
     * @return Project list
     */
    public List<User> getUsersByProject(Project project);
    
    
    /**
     * check is exist of user in one project
     *
     * @param project Object project
     * @param user Object User
     * @return
     */
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
     * @param project id of Project
     * @return true if delete successfully
     */
    public boolean deletePUByProject(Project project);
    /**
     * Delete PUD
     * @param project is Object Project
     * @param user is Object User
     * @return true if delete successfully
     */
    public boolean removeUserLeaProject(User user, Project project);
}
