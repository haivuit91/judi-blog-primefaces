/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entities.Role;
import model.entities.User;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.dao.service.UserDAO;

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
            session.close();
            for (User user : users) {
                user.setProjects(ProjectUserDAOImpl.getInstance().getProjectsByUser(user));
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
            session.close();
            user.setProjects(ProjectUserDAOImpl.getInstance().getProjectsByUser(user));
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
            session.close();
            user.setProjects(ProjectUserDAOImpl.getInstance().getProjectsByUser(user));
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
            String sql = "FROM User as u WHERE u.role = :role";
            Query query = session.createQuery(sql);
            query.setParameter("role", role);
            users = query.list();
            session.close();
            for (User user : users) {
                user.setProjects(ProjectUserDAOImpl.getInstance().getProjectsByUser(user));
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
    public boolean checkLogin(String userName, String password) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM User u WHERE u.userName = :userName and u.pwd = :pwd";
            Query query = session.createQuery(sql);
            query.setParameter("userName", userName);
            query.setParameter("pwd", password);
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
            String sql = "FROM User as u WHERE u.userName = :userName";
            Query query = session.createQuery(sql);
            query.setParameter("userName", userName);
            if (query.list() != null && query.list().size() > 0) {
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
            String sql = "FROM User as u WHERE e.email = :email";
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
            String sql = "UPDATE User as u set u.fullName = :fname, u.birthOfDay = :bd, u.gender = :gender, u.idCard = :idCard,"
                    + "u.address = :address, u.email = :email, u.phoneNumber = :phone WHERE u.userID = :userID";
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
            String sql = "UPDATE User as u set u.imagePath = :image WHERE u.userId = :userID";
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
            String sql = "UPDATE User as u set u.pwd = :pwd WHERE u.userID = :userID";
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
            String sql = "UPDATE User as u set u.active = false WHERE u.userID = :userID";
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
            String sql = "UPDATE User as u set u.active = true WHERE u.userID = :userID";
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

    @Override
    public boolean makeAdmin(User user) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE User set roleID = 1 WHERE userID = :userID";
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

}
