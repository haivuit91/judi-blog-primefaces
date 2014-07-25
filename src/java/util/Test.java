/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.RoleDAOImpl;
import dao.UserDAOImpl;
import entity.Role;
import entity.User;
import java.util.List;

/**
 *
 * @author cong0_000
 */
public class Test {

    public static void main(String[] args) {
        User user = UserDAOImpl.getInstance().getUserByID(1);
        System.out.println(user.getUserName());
        List<Role> roles = RoleDAOImpl.getInstance().getRoles();
        System.out.println(roles.size());
        Role r = RoleDAOImpl.getInstance().getRoleByID(3);
        
        System.out.println(r.isActive());
//        
//        Role role = new Role("Mod", true);
//        System.out.println(RoleDAOImpl.getInstance().createRole(role));
        System.out.println(RoleDAOImpl.getInstance().deleteRole(r));
        System.out.println(RoleDAOImpl.getInstance().getRoles().size());
    }
}
