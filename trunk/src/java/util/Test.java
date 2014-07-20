/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.dao.ProjectDAO;
import model.dao.ProjectUserDAO;
import model.dao.RoleDAO;
import model.dao.UserDAO;
import model.entities.Project;
import model.entities.ProjectUserDetails;
import model.entities.User;

/**
 *
 * @author cong0_000
 */
public class Test {
    public void test(){
        List<Project> list = ProjectDAO.getInstance().getProjects();
        for (Project project : list) {
            System.out.println(project.getUserList().size());
        }
    }
    public List<String> getUserNameNotJoined(Project p){
        List<User> users = UserDAO.getInstance().getAllUser();
        List<String> usersName = new ArrayList<>();
        for (User user : users) {
            usersName.add(user.getUserName());
        }
        List<User> usersJoined = p.getUserList();
        List<String> usersNameJoined = new ArrayList<>();
        for (User user : usersJoined) {
            usersNameJoined.add(user.getUserName());
        }
        usersName.removeAll(usersNameJoined);
        return usersName;
    }
    
    public String getDateFormat(Date d){
        String date = new SimpleDateFormat("dd/MM/yyyy").format(d);
        return date;
    }
    public List<User> getUserNameNotJoined2(Project p) {
        List<User> users = UserDAO.getInstance().getAllUser();
        List<String> usersName = new ArrayList<>();
        for (User user : users) {
            usersName.add(user.getUserName());
        }
        List<User> usersJoined = p.getUserList();
        List<String> usersNameJoined = new ArrayList<>();
        for (User user : usersJoined) {
            usersNameJoined.add(user.getUserName());
        }
        usersName.removeAll(usersNameJoined);
        List<User> userList = new ArrayList<>();
        for (String userName : usersName) {
            User u = UserDAO.getInstance().getUserByUserName(userName);
            userList.add(u);
        }
        return userList;
    }
    
    
    public static void main(String[] args) throws ParseException {
        User user = new User(1, "cong", "cong", "cong", new Date(), 1, "cong", "cong", "cong", "cong", "cong", RoleDAO.getInstance().getRoleByID(4), "cong", 1);
        System.out.println(UserDAO.getInstance().createUser(user));
    }
}
