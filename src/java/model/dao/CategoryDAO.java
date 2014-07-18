/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.service.CategoryDAOService;
import model.entities.Category;

/**
 *
 * @author ThanhTuan
 */
public class CategoryDAO implements CategoryDAOService {

    private static CategoryDAO categoryDAO;

    public static CategoryDAO getInstance() {
        if (categoryDAO == null) {
            categoryDAO = new CategoryDAO();
        }
        return categoryDAO;
    }

    @Override
    public Category getCategoryByID(int catID) throws Exception {
        Category category = new Category();
        String sql = "select * from tbl_category where catID =? and isActive =1 ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setInt(1, catID);
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            category.setCatID(rs.getInt("catID"));
            category.setCatName(rs.getString("catName"));
            category.setActive(true);
        }
        return category;
    }

    @Override
    public Category getCategoryByName(String catName) throws Exception {
        Category category = new Category();
        String sql = "select * from tbl_category where catName =? and isActive =1 ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setString(1, catName);
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            category.setCatID(rs.getInt("catID"));
            category.setCatName(rs.getString("catName"));
            category.setActive(true);
        }
        return category;
    }

    @Override
    public boolean createCategory(Category category) throws Exception {
        String sql = "insert into tbl_category (catName,isActive) values(?,?)";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setString(1, category.getCatName());
        sm.setBoolean(2, category.isActive());
        return sm.executeUpdate() == 1;
    }

    @Override
    public boolean removeCategory(Category category) throws Exception {
        String sql = "delete tbl_category where catID = ? ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setInt(1, category.getCatID());
        return sm.executeUpdate() == 1;
    }

    @Override
    public boolean updateCategoryByID(Category category) throws Exception {
        String sql = "update tbl_category set catName =? ,isActive = ? where catID=? ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setString(1, category.getCatName());
        sm.setBoolean(2, category.isActive());
        sm.setInt(3, category.getCatID());
        return sm.executeUpdate() == 1;
    }

    @Override
    public boolean updateCategoryByName(Category category) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean activeCategory(Category category) throws Exception {
        String sql = "update tbl_category set isActive = ? where catID= ? ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setBoolean(1, category.isActive());
        sm.setInt(2, category.getCatID());
        return sm.executeUpdate() == 1;
    }

    @Override
    public List<Category> getList() {
        try {
            List<Category> list = new ArrayList<>();
            String sql = "select * from tbl_category where isActive =1 ";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement sm = conn.prepareStatement(sql);
            ResultSet rs = sm.executeQuery();
            while (rs.next()) {
                Category item = new Category();
                item.setCatID(rs.getInt("catID"));
                item.setCatName(rs.getString("catName"));
                item.setActive(true);
                list.add(item);
            }
            return list;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
