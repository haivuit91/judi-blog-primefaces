/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao;

import model.entities.Type;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.dao.service.TypeDAO;

/**
 *
 * @author cong0_000
 */
public class ProjectTypeDAOImpl implements TypeDAO{

    private static ProjectTypeDAOImpl projectTypeImpl;

    public static ProjectTypeDAOImpl getInstance() {
        if (projectTypeImpl == null) {
            projectTypeImpl = new ProjectTypeDAOImpl();
        }
        return projectTypeImpl;
    }

    private HibernateUtil util;
    private Session session;

    
    @Override
    public List<Type> getTypes() {
        List<Type> types = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM Type";
            Query query = session.createQuery(sql);
            types = query.list();
            for (Type type : types) {
                type.setProjects(ProjectDAOImpl.getInstance().getProjectByType(type.getTypeId()));
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
        return types;
    }

    @Override
    public Type getTypeByID(int typeID) {
        Type type = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            type = (Type) session.get(Type.class, typeID);
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
        return type;
    }

    @Override
    public Type getTypeByName(String typeName) {
        Type type = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM Type as r WHERE r.typeName = :typeName";
            Query query = session.createQuery(sql);
            query.setParameter("typeName", typeName);
            type = (Type) query.uniqueResult();
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
        return type;
    }

    @Override
    public boolean createType(Type type) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(type);
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
    public boolean updateType(Type type) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(type);
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
    public boolean deleteType(int typeID) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(typeID);
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
    public boolean activeType(Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inactiveType(Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
