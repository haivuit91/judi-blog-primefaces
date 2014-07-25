/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Role;
import entity.User;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.UserDAO;

/**
 *
 * @author cong0_000
 */
public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl userDAO;

    public static UserDAOImpl getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    private HibernateUtil util;
    private Session session;

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM User";
            Query query = session.createQuery(sql);
            users = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally{
            session.close();
        }
        return users;
    }

    @Override
    public User getUserByID(int userID) {
        User user = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = (User) session.get(User.class, userID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally{
            session.close();
        }
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM User as u WHERE u.userName = :userName";
            Query query = session.createQuery(sql);
            query.setParameter("userName", userName);
            user = (User) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally{
            session.close();
        }
        return user;
    }

    @Override
    public List<User> getUserByRole(int roleID) {
        List<User> users = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM User WHERE role = :role";
            Query query = session.createQuery(sql);
            users = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally{
            session.close();
        }
        return users;
    }

    @Override
    public boolean checkLogin(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkUser(String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProfile(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateAvatar(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePassword(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateUserByAdmin(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean resetPass(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeUser(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean restoreUser(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUser(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}