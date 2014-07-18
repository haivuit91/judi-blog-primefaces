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
import model.dao.service.UserDAOService;
import model.entities.Role;
import model.entities.User;

/**
 *
 * @author Admin
 */
public class UserDAO implements UserDAOService {

    private static UserDAO userDAO;

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    /**
     *
     * @param isActive The user's status
     * @return User List by the user's status
     */
    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_user";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setPwd(rs.getString("pwd"));
                user.setFullName(rs.getString("fullName"));
                user.setBirthday(rs.getDate("birthday"));
                user.setGender(rs.getInt("gender"));
                user.setIdCard(rs.getString("idCard"));
                user.setAddress(rs.getString("userAddress"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPathImage(rs.getString("imagePath"));
                Role role = RoleDAO.getInstance().getRoleByID(rs.getInt("roleID"));
                user.setRole(role);
                user.setIdActive(rs.getString("idActive"));
                user.setActive(rs.getInt("isActive"));
                userList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     *
     * @param userName is name of user
     * @return User List by User Name
     */
    @Override
    public List<User> findUserByUserName(String userName) {
        List<User> userList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_user where userName like '" + "%" + userName + "%" + "'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setPwd(rs.getString("pwd"));
                user.setFullName(rs.getString("fullName"));
                user.setBirthday(rs.getDate("birthday"));
                user.setGender(rs.getInt("gender"));
                user.setIdCard(rs.getString("idCard"));
                user.setAddress(rs.getString("userAddress"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPathImage(rs.getString("imagePath"));
                Role role = RoleDAO.getInstance().getRoleByID(rs.getInt("roleID"));
                user.setRole(role);
                user.setIdActive(rs.getString("idActive"));
                user.setActive(rs.getInt("isActive"));
                userList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     *
     * @param userID is id of user
     * @return User by UserID
     */
    @Override
    public User getUserByID(int userID) {
        User user = new User();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_user where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setPwd(rs.getString("pwd"));
                user.setFullName(rs.getString("fullName"));
                user.setBirthday(rs.getDate("birthday"));
                user.setGender(rs.getInt("gender"));
                user.setIdCard(rs.getString("idCard"));
                user.setAddress(rs.getString("userAddress"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPathImage(rs.getString("imagePath"));
                Role role = RoleDAO.getInstance().getRoleByID(rs.getInt("roleID"));
                user.setRole(role);
                user.setIdActive(rs.getString("idActive"));
                user.setActive(rs.getInt("isActive"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    @Override
    public User getUserByUserName(String userName) {
        User user = new User();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_user where userName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setPwd(rs.getString("pwd"));
                user.setFullName(rs.getString("fullName"));
                user.setBirthday(rs.getDate("birthday"));
                user.setGender(rs.getInt("gender"));
                user.setIdCard(rs.getString("idCard"));
                user.setAddress(rs.getString("userAddress"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPathImage(rs.getString("imagePath"));
                Role role = RoleDAO.getInstance().getRoleByID(rs.getInt("roleID"));
                user.setRole(role);
                user.setIdActive(rs.getString("idActive"));
                user.setActive(rs.getInt("isActive"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     *
     * @param roleID is name of user
     * @return User List by Role
     */
    @Override
    public List<User> getUserByRole(int roleID) {
        List<User> userList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_user where roleID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roleID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setPwd(rs.getString("pwd"));
                user.setFullName(rs.getString("fullName"));
                user.setBirthday(rs.getDate("birthday"));
                user.setGender(rs.getInt("gender"));
                user.setIdCard(rs.getString("idCard"));
                user.setAddress(rs.getString("userAddress"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPathImage(rs.getString("imagePath"));
                user.setActive(rs.getInt("isActive"));
                userList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     *
     * @param key is attributes used to search
     * @param value is keyword use for searching
     * @return
     */
    @Override
    public List<User> findUsers(String key, String value) {
        List<User> userList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_user where " + key + " like '" + "%" + value + "%" + "'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setPwd(rs.getString("pwd"));
                user.setFullName(rs.getString("fullName"));
                user.setBirthday(rs.getDate("birthday"));
                user.setGender(rs.getInt("gender"));
                user.setIdCard(rs.getString("idCard"));
                user.setAddress(rs.getString("userAddress"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPathImage(rs.getString("imagePath"));
                Role role = RoleDAO.getInstance().getRoleByID(rs.getInt("roleID"));
                user.setRole(role);
                user.setActive(rs.getInt("isActive"));
                userList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     *
     * @param userName name of User
     * @param pwd password of User
     * @return true if userName and password == true. The opposite is false
     */
    @Override
    public boolean checkLogin(String userName, String pwd) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_user where userName = ? and pwd = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                isCheck = rs.next();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return isCheck;
    }

    /**
     *
     * @param userName name of User
     * @return true if userName is Exist. The opposite is false
     */
    @Override
    public boolean checkUser(String userName) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_user where userName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                isCheck = rs.next();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return isCheck;
    }

    /**
     *
     * @param user is a Object User
     * @return true if create User successful. false if failed!
     */
    @Override
    public boolean createUser(User user) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "insert into tbl_user(userName, pwd, fullName, birthday, gender, idCard, userAddress, email,"
                    + "phone, imagePath, roleID, idActive, isActive) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPwd());
            pstmt.setString(3, user.getFullName());
//            pstmt.setDate(4, (java.sql.Date) user.getBirthday());
            java.util.Date date = user.getBirthday();
            pstmt.setDate(4, new java.sql.Date(date.getTime()));
            pstmt.setInt(5, user.getGender());
            pstmt.setString(6, user.getIdCard());
            pstmt.setString(7, user.getAddress());
            pstmt.setString(8, user.getEmail());
            pstmt.setString(9, user.getPhone());
            pstmt.setString(10, user.getPathImage());
            pstmt.setInt(11, user.getRole().getRoleID());
            pstmt.setString(12, user.getIdActive());
            pstmt.setInt(13, user.getActive());

            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    /**
     *
     * @param user is a Object User
     * @return true if update Profile of User successful. false if failed!
     */
    @Override
    public boolean updateProfile(User user) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_user set fullName = ?, birthday = ?, gender = ?, idCard = ?, userAddress = ?, email = ?,"
                    + "phone = ? where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getFullName());
            pstmt.setDate(2, (java.sql.Date) user.getBirthday());
            pstmt.setInt(3, user.getGender());
            pstmt.setString(4, user.getIdCard());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getPhone());
            pstmt.setInt(8, user.getUserID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    /**
     * update profile of User by Administrator
     *
     * @param user is a Object User
     * @return true if update Profile of User successful. false if failed!
     */
    @Override
    public boolean updateUserByAdmin(User user) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_user set fullName = ?, birthday = ?, gender = ?, userAddress = ?, email = ?,"
                    + "phone = ?, imagePath = ?, roleID=?, pwd=?, idCard = ? where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getFullName());
            pstmt.setDate(2, (java.sql.Date) user.getBirthday());
            pstmt.setInt(3, user.getGender());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getPhone());
            pstmt.setString(7, user.getPathImage());
            pstmt.setInt(8, user.getRole().getRoleID());
            pstmt.setString(9, user.getPwd());
            pstmt.setString(10, user.getIdCard());
            pstmt.setInt(11, user.getUserID());

            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    /**
     * remove The User's active
     *
     * @param user is a Object User
     * @return true if remove The user's status successful. false if failed!
     */
    @Override
    public boolean removeUser(User user) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_user set isActive='0' where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    /**
     *
     * @param user is a Object User
     * @return true if restore The user's status successful. false if failed!
     */
    @Override
    public boolean restoreUser(User user) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_user set isActive='1', idActive='' where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    /**
     *
     * @param userID UserID will be delete
     * @return true if delete The user's status successful. false if failed!
     */
    @Override
    public boolean deleteUser(int userID) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "delete from tbl_user where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean updatePassword(User user) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_user set pwd = ? where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPwd());
            pstmt.setInt(2, user.getUserID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck; 
    }

    @Override
    public boolean updateAvatar(User user) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_user set imagePath = ? where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPathImage());
            pstmt.setInt(2, user.getUserID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isCheck; 
    }

}
