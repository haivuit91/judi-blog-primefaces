/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Category;
import java.util.List;

/**
 *
 * @author Tuanka
 */
public interface CategoryDAO {
    
    /**
     * get all category
     * @return 
     */
    List<Category> getList();
    
    /**
     * get Category by ID
     * @param CategoryID 
     * @return Category
     * @throws Exception
     */
    Category getCategoryByID(int CategoryID) throws Exception;
    /**
     * get Category by Category name
     * @param categoryID
     * @return Category
     * @throws Exception 
     */
    Category getCategoryByName(String categoryID) throws Exception;
    /**
     * create new Category
     * @param category
     * @return true if create success
     * @throws Exception 
     */
    boolean createCategory(Category category) throws Exception;
    /**
     * remove Category
     * @param category
     * @return true if remove success
     * @throws Exception error while remove
     */
    boolean removeCategory(Category category) throws Exception;
    /**
     * update Category by categoryID
     * @param category
     * @return true if update success
     * @throws Exception 
     */
    boolean updateCategoryByID(Category category) throws Exception;
    /**
     * update Category by CategoryName
     * @param category
     * @return true if update success
     * @throws Exception 
     */
    boolean updateCategoryByName(Category category) throws Exception;
    /**
     * enable or disable Category
     * @param category  category.isActive = true if enable Category
     * @return true if active success
     * @throws Exception 
     */
    boolean activeCategory(Category category) throws Exception;
}
