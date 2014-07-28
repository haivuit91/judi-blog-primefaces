/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import entity.Post;
import hibernate.HibernateUtil;
import java.util.List;
import javassist.compiler.JvstCodeGen;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import service.PostDAO;

/**
 *
 * @author monkeydluffy
 */
public class PostDAOImpl implements PostDAO {

    private static PostDAOImpl postDAOImpl;

    public static PostDAOImpl getInstance() {
        if (postDAOImpl == null) {
            postDAOImpl = new PostDAOImpl();
        }
        return postDAOImpl;
    }
    private Session session;
    private HibernateUtil util;

    @Override
    public List<Post> getListPost() {
        List<Post> listPost = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Post";
            Query query = session.createQuery(hql);
            listPost = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listPost;
    }

    @Override
    public List<Post> getListPostByCategories(int c) {
        List<Post> listPosts = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            Category cate = CategoryDAOImlp.getInstance().getCategoryByID(c);
            tx = session.beginTransaction();
            String sql = "FROM Post WHERE category = :category";
            Query query = session.createQuery(sql);
            query.setParameter("category", cate);
            listPosts = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listPosts;
    }

    @Override
    public int getTotalPost() {
       List results = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =session.beginTransaction();
            String hql = "FROM Post";
            Query query = session.createQuery(hql);
            results = query.list();
            
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results.size();
    }

    @Override
    public boolean deletePost(int postID) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            Post post = PostDAOImpl.getInstance().getPostByID(postID);
            tx = session.beginTransaction();
            session.delete(post);
            tx.commit();
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
    public boolean updatePost(Post post) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(post);
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
    public boolean insertPost(Post post) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(post);
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
    public boolean activePost(boolean isActive, int postID){
     boolean isCheck = false;
     session = util.getSessionFactory().openSession();
     Transaction tx = null;
     try{
        tx = session.beginTransaction();
        String hql =  "UPDATE Post set active = :isActive WHERE postId =:postID";
        Query query = session.createQuery(hql);
        query.setParameter("isActive", false);
        query.setParameter("postID", postID);
        tx.commit();
        isCheck= true;
     }catch(HibernateException e){
         if(tx!= null){
             tx.rollback();
         }
         e.printStackTrace();
     }
     return isCheck;
    }

    @Override
    public List<Post> searchPost(String searchType, String searchKey) {
        List<Post> listPost = null;
        session = util.getSessionFactory().openSession();
        Transaction tx= null;
        try{
            tx= session.beginTransaction();
            Criteria criteria = session.createCriteria(Post.class);
            criteria.add(Restrictions.like(searchType, searchKey));
            listPost = criteria.list();
            
        }catch(HibernateException e){
            if(tx!= null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return listPost;
    }

    @Override
    public Post getPostByID(int postID) {
        Post post = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            post = (Post) session.get(Post.class, postID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
//            session.close();
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

}
