/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao.service;

import model.entities.Type;
import java.util.List;

/**
 *
 * @author cong0_000
 */
public interface TypeDAO {
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
    public boolean createType(Type type);
    /**
     * Update type of project
     * @param type The type of project
     * @return true if update successfully
     */
    public boolean updateType(Type type);
    /**
     * Delete type of project
     * @param typeID The typeID of project
     * @return true if delete successfully
     */
    public boolean deleteType(int typeID);
    
    /**
     * Active type of project
     * @param type The type of project
     * @return true if active successfully
     */
    public boolean activeType(Type type);
    
    /**
     * Inactive type of project
     * @param type The type of project
     * @return true if Inactive successfully
     */
    public boolean inactiveType(Type type);
}
