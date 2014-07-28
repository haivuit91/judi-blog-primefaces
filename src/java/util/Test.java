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
import java.util.Date;
import java.util.List;

/**
 *
 * @author cong0_000
 */
public class Test {

    public static void main(String[] args) {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        RoleDAOImpl roleDAO = RoleDAOImpl.getInstance();
        Role r = roleDAO.getRolesByName("Member");
        System.out.println(r.getUsers().get(1).getFullName());
//        User user = new User(1, r, "haivv", util.Support.encryptMD5("haivv"), "Vũ Văn Hải", new Date(91, 5, 12), true, "129444223", 
//                "Cẩm Thủy - Thanh Hóa", "haivv.itedu@gmail.com", "0905022342", null, null, true);
        
//        User user = userDAO.getUserByID(3);
//        user.setImagePath("");
//        System.out.println(userDAO.updateAvatar(user));
        List<User> users = userDAO.getAllUsers();
        for (User user1 : users) {
            System.out.println(user1.getUserName());
        }
//        List<Role> roles = roleDAO.getRoles();
//        for (Role role : roles) {
//            System.out.println(role.getUsers().size());
//        }
        
    }
}
