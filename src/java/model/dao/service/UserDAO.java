/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao.service;

import model.entities.User;
import java.util.List;

/**
 *
 * @author cong0_000
 */
public interface UserDAO {
    /**
     * get All User from DB
     *
     * @return User List by the user's status
     */
    public List<User> getUsers();

    /**
     * Get UserList by userID
     *
     * @param userID is id of user
     * @return User by UserID
     */
    public User getUserByID(int userID);

    /**
     * Get UserList by userID
     *
     * @param userName is name of user
     * @return User by UserID
     */
    public User getUserByUserName(String userName);


    /**
     * Get User list by Role
     *
     * @param roleID is name of user
     * @return User List by Role
     */
    public List<User> getUserByRole(int roleID);

    /**
     * Check username and password when Login
     *
     * @param userName name of User
     * @param password password of User
     * @return true if userName and password == true. The opposite is false
     */
    public boolean checkLogin(String userName, String password);

    /**
     * Check isExist of User when create new User
     *
     * @param userName name of User
     * @return true if userName is Exist. The opposite is false
     */
    public boolean checkUser(String userName);

    /**
     * Check isExist of User when create new User
     *
     * @param email name of User
     * @return true if userName is Exist. The opposite is false
     */
    public boolean checkEmail(String email);

    /**
     * Create new User
     *
     * @param user is a Object User
     * @return true if create User successful. false if failed!
     */
    public boolean createUser(User user);

    /**
     * Update user's profile by User
     *
     * @param user is a Object User
     * @return true if update Profile of User successful. false if failed!
     */
    public boolean updateProfile(User user);

    /**
     * Update user's avatar by User
     *
     * @param user is a Object User
     * @return true if update Profile of User successful. false if failed!
     */
    public boolean updateAvatar(User user);

    /**
     * Update user's password by User
     *
     * @param user is a Object User
     * @return true if update Profile of User successful. false if failed!
     */
    public boolean updatePassword(User user);

    /**
     * update profile of User by Administrator
     *
     * @param user is a Object User
     * @return true if update Profile of User successful. false if failed!
     */
    public boolean updateUserByAdmin(User user);
    
    /**
     * remove The User's active
     *
     * @param user
     * @return true if remove The user's status successful. false if failed!
     */
    public boolean removeUser(User user);

    /**
     * restore The User's active
     *
     * @param user
     * @return true if restore The user's status successful. false if failed!
     */
    public boolean restoreUser(User user);

    /**
     * Delete User
     *
     * @param user
     * @return true if delete The user's status successful. false if failed!
     */
    public boolean deleteUser(User user);

    /**
     *
     * @param user
     * @return true if make administrator successful
     */
    public boolean makeAdmin(User user);
}
