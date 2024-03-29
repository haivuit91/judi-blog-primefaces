/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.service;

import model.entities.Role;
import java.util.List;

/**
 *
 * @author cong0_000
 */
public interface RoleDAO {

    public List<Role> getRoles();

    public Role getRoleByID(int roleID);

    public Role getRoleByName(String roleName);

    public boolean createRole(Role role);

    public boolean updateRole(Role role);

    public boolean removeRole(Role role);

    public boolean restoreRole(Role role);

    public boolean deleteRole(Role role);
}
