/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.olddao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.olddao.service.RoleDAOService;
import model.oldentities.Role;
import model.oldentities.User;

/**
 *
 * @author Admin
 */
public class RoleDAO implements RoleDAOService {

    private static RoleDAO roleDAO;

    public static RoleDAO getInstance() {
        if (roleDAO == null) {
            roleDAO = new RoleDAO();
        }
        return roleDAO;
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roleList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_role";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("roleName"));
                role.setPathImage(rs.getString("imagePath"));
                role.setActive(rs.getInt("isActive"));
                List<User> userList = UserDAO.getInstance().getUserByRole(rs.getInt("roleID"));
                role.setUserList(userList);
                roleList.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }

    @Override
    public Role getRoleByID(int roleID) {
        Role role = new Role();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_role where roleID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roleID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("roleName"));
                role.setPathImage(rs.getString("imagePath"));
                role.setActive(rs.getInt("isActive"));
                List<User> userList = UserDAO.getInstance().getUserByRole(rs.getInt("roleID"));
                role.setUserList(userList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role getRolesByName(String roleName) {
         Role role = new Role();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_role where roleName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, roleName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("roleName"));
                role.setPathImage(rs.getString("imagePath"));
                role.setActive(rs.getInt("isActive"));
                List<User> userList = UserDAO.getInstance().getUserByRole(rs.getInt("roleID"));
                role.setUserList(userList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public boolean createRole(Role role) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "insert into tbl_role(roleName, imagePath, isActive) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, role.getRoleName());
            pstmt.setString(2, role.getPathImage());
            pstmt.setInt(3, role.getActive());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean updateRole(Role role) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_role set roleName = ?, imagePath = ?, isActive = ? where roleID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, role.getRoleName());
            pstmt.setString(2, role.getPathImage());
            pstmt.setInt(3, role.getActive());
            pstmt.setInt(4, role.getRoleID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean removeRole(Role role) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_role set isActive = '0' where roleID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, role.getRoleID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean restoreRole(Role role) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_role set isActive = '1' where roleID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, role.getRoleID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean deleteRole(int roleID) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "delete from tbl_role where roleID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roleID);
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

}
