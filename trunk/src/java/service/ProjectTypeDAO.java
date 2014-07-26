/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Type;
import java.util.List;

/**
 *
 * @author Tuanka
 */
public interface ProjectTypeDAO {
    
    /**
     * get list Type of project
     *
     * @return Project Type list.
     */
    public List<Type> getTypes();

    /**
     * get project type by id
     *
     * @param typeID
     * @return Project Type
     */
    public Type getTypeByID(int typeID);

    /**
     * get project type by name
     *
     * @param typeName  name's project type
     * @return Project Type
     */
    public Type getTypeByName(String typeName);

    /**
     * Create new type of project
     * @param type The type of project
     * @return true if create successfully
     */
    public boolean createProjectType(Type type);
    /**
     * Update type of project
     * @param type The type of project
     * @return true if update successfully
     */
    public boolean updateProjectType(Type type);
    /**
     * Delete type of project
     * @param typeID The typeID of project
     * @return true if delete successfully
     */
    public boolean deleteProjectType(int typeID);
    
    /**
     * Active type of project
     * @param type The type of project
     * @return true if active successfully
     */
    public boolean activeProjectType(Type type);
    
    /**
     * Inactive type of project
     * @param type The type of project
     * @return true if Inactive successfully
     */
    public boolean inactiveProjectType(Type type);
}
