/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao.service;

import java.util.List;
import model.entities.Role;

/**
 *
 * @author Admin
 */
public interface RoleDAOService {
    
    /**
     * Get All Roles
     * @param isActive The role's status.
     * @return role list by status
     */
    public List<Role> getRoles();
    public Role getRoleByID(int roleID);
    public Role getRolesByName(String roleName);
    
    public boolean createRole(Role role);
    public boolean updateRole(Role role);
    public boolean removeRole(Role role);
    public boolean restoreRole(Role role);
    public boolean deleteRole(int roleID);
}
