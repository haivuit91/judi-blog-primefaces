/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.entities.Category;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import model.dao.service.CategoryDAO;

/**
 *
 * @author monkeydluffy
 */
public class CategoryDAOImlp implements CategoryDAO {

    private static CategoryDAOImlp categoryDAOImlp;

    public static CategoryDAOImlp getInstance() {
        if (categoryDAOImlp == null) {
            categoryDAOImlp = new CategoryDAOImlp();
        }
        return categoryDAOImlp;
    }
    private HibernateUtil util;
    private Session session = null;

    @Override
    public List<Category> getList() {
        List<Category> listCategory = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            listCategory = new ArrayList<>();
            List temp = session.createCriteria(Category.class).list();
            for (Object item : temp) {
                Category category = (Category) item;
                listCategory.add(category);
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listCategory;
    }

    @Override
    public Category getCategoryByID(int CategoryID) {
        Category categorys = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            categorys = (Category) session.get(Category.class, CategoryID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categorys;
    }

    @Override
    public Category getCategoryByName(String categoryName) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            Criteria criteria = session.createCriteria(Category.class);
            tx = session.beginTransaction();
            criteria.add(Restrictions.eq("categoryName", categoryName));
            return (Category) criteria.uniqueResult();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean createCategory(Category category) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(category);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean removeCategory(Category category) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(category);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateCategoryByID(Category category) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(category);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateCategoryByName(Category category) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(category);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean activeCategory(Category category) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(category);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;

    }

}
