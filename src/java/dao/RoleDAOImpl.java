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
import service.RoleDAO;

/**
 *
 * @author cong0_000
 */
public class RoleDAOImpl implements RoleDAO {

    private static RoleDAOImpl roleDAO;

    public static RoleDAOImpl getInstance() {
        if (roleDAO == null) {
            roleDAO = new RoleDAOImpl();
        }
        return roleDAO;
    }

    private HibernateUtil util;
    private Session session;

    @Override
    public List<Role> getRoles() {
        List<Role> roles = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM Role";
            Query query = session.createQuery(sql);
            roles = query.list();
            for (Role role : roles) {
                role.setUsers(UserDAOImpl.getInstance().getUserByRole(role.getRoleId()));
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
        return roles;
    }

    @Override
    public Role getRoleByID(int roleID) {
        Role role = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            role = (Role) session.get(Role.class, roleID);
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
        return role;
    }

    @Override
    public Role getRolesByName(String roleName) {
        Role role = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM Role as r WHERE r.roleName = :roleName";
            Query query = session.createQuery(sql);
            query.setParameter("roleName", roleName);
            role = (Role) query.uniqueResult();
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
        return role;
    }

    @Override
    public boolean createRole(Role role) {

        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(role);
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
    public boolean updateRole(Role role) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(role);
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
    public boolean removeRole(Role role) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE Role set active = false WHERE roleID = :roleID";
            Query query = session.createQuery(sql);
            query.setParameter("roleID", role.getRoleId());
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
    public boolean restoreRole(Role role) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE Role set active = true WHERE roleID = :roleID";
            Query query = session.createQuery(sql);
            query.setParameter("roleID", role.getRoleId());
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
    public boolean deleteRole(Role role) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(role);
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
