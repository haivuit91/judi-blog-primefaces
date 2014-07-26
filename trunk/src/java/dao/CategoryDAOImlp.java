/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Category;
import hibernate.HibernateUtil;
import java.util.List;
import model.dao.service.CategoryDAOService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author monkeydluffy
 */
public class CategoryDAOImlp implements CategoryDAOService{
    
    private static CategoryDAOImlp categoryDAOImlp;
    public static CategoryDAOImlp getInstance(){
        if(categoryDAOImlp==null){
            categoryDAOImlp = new CategoryDAOImlp();
        }
        return categoryDAOImlp;
    }
    private HibernateUtil util;
    private Session session = null;
    @Override
    public List<Category> getList() {
        List<Category> listCategory =null;
        session = util.getSessionFactory().openSession();
        Transaction tx =null;
        try{
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return listCategory;
    }

    @Override
    public Category getCategoryByID(int CategoryID){
        Category categorys = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            categorys = (Category) session.get(Category.class, CategoryID);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return categorys;
    }

    @Override
    public Category getCategoryByName(String categoryID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createCategory(Category category) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeCategory(Category category) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateCategoryByID(Category category) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateCategoryByName(Category category) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean activeCategory(Category category) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
