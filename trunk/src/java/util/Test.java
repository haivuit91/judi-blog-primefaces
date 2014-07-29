/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import model.entities.Project;
import model.entities.User;
import java.util.List;
import model.dao.ProjectDAOImpl;
import model.dao.ProjectUserDAOImpl;
import model.dao.RoleDAOImpl;
import model.dao.UserDAOImpl;

/**
 *
 * @author cong0_000
 */
public class Test {

    public static void main(String[] args) {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        RoleDAOImpl roleDAO = RoleDAOImpl.getInstance();
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        ProjectUserDAOImpl puDAO = ProjectUserDAOImpl.getInstance();
        
//        User user = userDAO.getUserByID(1);
//        List<Project> projects = puDAO.getProjectByUser(user);
//        System.out.println(projects.get(1).getProjectName());
//        Project p = projectDAO.getProjectByID(1);
//        List<User> users = puDAO.getUserByProject(p);
//        System.out.println(users.get(0).getUserName());
        
        String userName = "admin";
        String pwd = "admin";
        System.out.println(userDAO.checkLogin(userName, pwd));
    }
}
