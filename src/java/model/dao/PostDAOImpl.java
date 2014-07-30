/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import model.dao.service.PostDAO;
import model.entities.Post;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tuanka
 */
public class PostDAOImpl implements PostDAO {

    private static PostDAOImpl postDAOImpl;

    public static PostDAOImpl getInstance() {
        if (postDAOImpl == null) {
            postDAOImpl = new PostDAOImpl();
        }
        return postDAOImpl;
    }

    @Override
    public List<Post> getListPost() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Post> listPost = null;
        try {
            listPost = new ArrayList<>();
            transaction = session.beginTransaction();

            String hsql = "FROM Post";
            Query query = session.createQuery(hsql);
            listPost = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            //       session.close();
        }
        return listPost;
    }

    @Override
    public List<Post> getListPostByCategories(int c) throws Exception {
        List<Post> listPost = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            listPost = new ArrayList<>();
            transaction = session.beginTransaction();
            String hql = "from Post p where p.category.categoryId = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", c);
            listPost = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
//            session.close();
        }
        return listPost;
    }

    @Override
    public int getTotalPost() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePost(Post post) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(post);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean updatePost(Post post) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(post);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
//            session.close();
        }
        return true;
    }

    @Override
    public boolean insertPost(Post post) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean activePost(boolean isActive, int postID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> searchPost(String searchType, String searchKey) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post getPostByID(int postID) throws Exception {
        Post post = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Post.class);
            criteria.add(Restrictions.eq("postId", postID));
            post = (Post) criteria.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return post;
    }

    @Override
    public int getNewPostID() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkExitPost(Post post) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePost(int postId) throws Exception {
         List<Post> listPost = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            listPost = new ArrayList<>();
            transaction = session.beginTransaction();
            String hql = "delete Post p where p.postId = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", postId);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
//            session.close();
        }
        return true;
    }

}
