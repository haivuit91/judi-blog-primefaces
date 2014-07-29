/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Project;
import entity.ProjectUserDetails;
import entity.Role;
import entity.User;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.UserDAO;
import util.Support;

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
    public List<User> getUsers() {
        List<User> users = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM User";
            Query query = session.createQuery(sql);
            users = query.list();
            for (User user : users) {
                user.setProjectUserDetails(ProjectUserDAOImpl.getInstance().getProjectByUser(user));
            }
            session = util.getSessionFactory().openSession();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
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
        } finally {
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
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> getUserByRole(int roleID) {
        Role role = RoleDAOImpl.getInstance().getRoleByID(roleID);
        List<User> users = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT FROM User as u WHERE role = :role";
            Query query = session.createQuery(sql);
            query.setParameter("role", role);
            users = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public boolean checkLogin(String userName, String password) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM User WHERE userName = :userName and pwd = :pwd";
            Query query = session.createQuery(sql);
            query.setParameter("userName", userName);
            query.setParameter("pwd", Support.encryptMD5(password));
            if (query.list().size() > 0) {
                isCheck = true;
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isCheck = false;
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean checkUser(String userName) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM User WHERE userName = :userName";
            Query query = session.createQuery(sql);
            query.setParameter("userName", userName);
            if (query.list().size() > 0) {
                isCheck = true;
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isCheck = false;
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean checkEmail(String email) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM User WHERE email = :email";
            Query query = session.createQuery(sql);
            query.setParameter("email", email);
            if (query.list().size() > 0) {
                isCheck = true;
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isCheck = false;
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean createUser(User user) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isCheck = false;
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean updateProfile(User user) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE User set fullName = :fname, birthOfDay = :bd, gender = :gender, idCard = :idCard,"
                    + "address = :address, email = :email, phoneNumber = :phone WHERE userID = :userID";
            Query query = session.createQuery(sql);
            query.setParameter("userID", user.getUserId());
            query.setParameter("fname", user.getFullName());
            query.setParameter("bd", user.getBirthOfDay());
            query.setParameter("gender", user.isGender());
            query.setParameter("idCard", user.getIdCard());
            query.setParameter("address", user.getAddress());
            query.setParameter("email", user.getEmail());
            query.setParameter("phone", user.getPhoneNumber());
            query.executeUpdate();
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean updateAvatar(User user) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE User set imagePath = :image WHERE userID = :userID";
            Query query = session.createQuery(sql);
            query.setParameter("userID", user.getUserId());
            query.setParameter("image", user.getImagePath());
            query.executeUpdate();
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean updatePassword(User user) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE User set pwd = :pwd WHERE userID = :userID";
            Query query = session.createQuery(sql);
            query.setParameter("userID", user.getUserId());
            query.setParameter("pwd", user.getPwd());
            query.executeUpdate();
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean updateUserByAdmin(User user) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isCheck = false;
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean resetPass(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeUser(User user) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE User set active = false WHERE userID = :userID";
            Query query = session.createQuery(sql);
            query.setParameter("userID", user.getUserId());
            query.executeUpdate();
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean restoreUser(User user) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE User set active = true WHERE userID = :userID";
            Query query = session.createQuery(sql);
            query.setParameter("userID", user.getUserId());
            query.executeUpdate();
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean deleteUser(int userID) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(userID);
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isCheck;
    }

}
