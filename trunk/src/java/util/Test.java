/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.ProjectDAOImpl;
import dao.ProjectTypeDAOImpl;
import dao.ProjectUserDAOImpl;
import dao.RoleDAOImpl;
import dao.UserDAOImpl;
import entity.Project;
import entity.Role;
import entity.Type;
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
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        ProjectUserDAOImpl puDAO = ProjectUserDAOImpl.getInstance();
        
        User user = userDAO.getUserByID(1);
        List<Project> projects = puDAO.getProjectByUser(user);
        System.out.println(projects.get(1).getProjectName());
        
    }
}
