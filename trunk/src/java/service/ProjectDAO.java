/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Project;
import java.util.List;

/**
 *
 * @author Tuanka
 */
public interface ProjectDAO {
    
    /**
     * get all list project
     *
     * @return project list
     */
    public List<Project> getProjects();

    /**
     * get Project by project ID
     *
     * @param projectID Project's ID
     * @return Project object
     */
    public Project getProjectByID(int projectID);

    /**
     * get Project by project ID
     *
     * @param projectName Project's Name
     * @return Project object
     */
    public List<Project> getProjectByName(String projectName);
    
    /**
     * get Project by type ID
     *
     * @param typeID id of project type
     * @return list project
     */
    public List<Project> getProjectByType(int typeID);

    /**
     * create new a project
     *
     * @param project is Object's Project
     * @return true if successfully
     */
    public boolean createProject(Project project);

    /**
     * update a project
     *
     * @param project is Object's Project
     * @return true if successfully
     */
    public boolean updateProject(Project project);

    /**
     * delete a project
     *
     * @param project is a Project
     * @return true if successfully
     */
    public boolean deleteProject(Project project);
    
    /**
     * Active project
     * @param project The type of project
     * @return true if active successfully
     */
    public boolean activeProject(Project project);
    
    /**
     * Inactive project
     * @param project The type of project
     * @return true if Inactive successfully
     */
    public boolean inactiveProject(Project project);
}
