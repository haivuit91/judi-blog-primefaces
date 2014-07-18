/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.service.ProjectUserDAOService;
import model.entities.Project;
import model.entities.ProjectUserDetails;
import model.entities.User;

/**
 *
 * @author Admin
 */
public class ProjectUserDAO implements ProjectUserDAOService {

    private static ProjectUserDAO puDAO;

    public static ProjectUserDAO getInstance() {
        if (puDAO == null) {
            puDAO = new ProjectUserDAO();
        }
        return puDAO;
    }

    @Override
    public List<ProjectUserDetails> getAllPUList() {
        List<ProjectUserDetails> pudList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_project_user";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectUserDetails pud = new ProjectUserDetails();
                pud.setProject_userID(rs.getInt("project_userID"));
                pud.setUser(UserDAO.getInstance().getUserByID(rs.getInt("userID")));
                pud.setProject(ProjectDAO.getInstance().getProjectByID(rs.getInt("projectID")));
                pud.setCreater(rs.getInt("isCreater"));
                pudList.add(pud);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return pudList;
    }

    @Override
    public ProjectUserDetails getPUByID(int puID) {
        ProjectUserDetails pud = new ProjectUserDetails();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_project_user where project_userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, puID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pud.setProject_userID(rs.getInt("project_userID"));
                pud.setUser(UserDAO.getInstance().getUserByID(rs.getInt("userID")));
                pud.setProject(ProjectDAO.getInstance().getProjectByID(rs.getInt("projectID")));
                pud.setCreater(rs.getInt("isCreate"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return pud;
    }

    @Override
    public List<Project> getProjectByUser(int userID) {
        List<Project> projectList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select projectID from tbl_project_user where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Project project = ProjectDAO.getInstance().getProjectByID(rs.getInt("projectID"));
                projectList.add(project);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return projectList;
    }

    @Override
    public List<User> getUserByProject(int projectID) {
        List<User> userList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select userID from tbl_project_user where projectID = ? and isCreater = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = UserDAO.getInstance().getUserByID(rs.getInt("userID"));
                userList.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> getUserNotJoin(int projectID) {
        List<User> userList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select userID from tbl_user";
//            String sql = "select userID from tbl_user except "
//                    + "select userID from tbl_project_user where projectID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, projectID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = UserDAO.getInstance().getUserByID(rs.getInt("userID"));
                userList.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean checkJoinUser(User user, Project project) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_project_user where userID = ? and projectID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserID());
            pstmt.setInt(2, project.getProjectID());
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                isCheck = rs.next();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean createPUD(ProjectUserDetails pud) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "insert into tbl_project_user(userID, projectID, isCreater) values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pud.getUser().getUserID());
            pstmt.setInt(2, pud.getProject().getProjectID());
            pstmt.setInt(3, pud.getCreater());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean updatePUD(ProjectUserDetails pud) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_project_user set userID = ?, projectID = ?, isCreater = ? where project_userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pud.getUser().getUserID());
            pstmt.setInt(2, pud.getProject().getProjectID());
            pstmt.setInt(3, pud.getProject_userID());
            pstmt.setInt(4, pud.getCreater());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean deleteUserJoinedProject(int projectID) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "delete from tbl_project_user where projectID = ? and isCreater = 'false'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectID);
            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }
    
    @Override
    public boolean deletePUByProjectID(int projectID) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "delete from tbl_project_user where projectID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectID);
            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean isExistUserInProject(Project project, User user) {
        boolean isExist = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_project_user where userID = ? and projectID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserID());
            pstmt.setInt(2, project.getProjectID());
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                isExist = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    @Override
    public boolean removeUserLeaProject(User user, Project project) {
        boolean isExist = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "delete from tbl_project_user where userID = ? and projectID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserID());
            pstmt.setInt(2, project.getProjectID());
            pstmt.executeUpdate();
            isExist = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    @Override
    public List<ProjectUserDetails> getPUByProjectID(int projectID) {
        List<ProjectUserDetails> pudList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_project_user where projectID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectUserDetails pud = new ProjectUserDetails();
                pud.setProject_userID(rs.getInt("project_userID"));
                pud.setUser(UserDAO.getInstance().getUserByID(rs.getInt("userID")));
                pud.setProject(ProjectDAO.getInstance().getProjectByID(rs.getInt("projectID")));
                pud.setCreater(rs.getInt("isCreater"));
                pudList.add(pud);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return pudList;
    }

}
