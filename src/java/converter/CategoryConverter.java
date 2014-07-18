/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.dao.CategoryDAO;
import model.dao.PostDAO;
import model.dao.service.CategoryDAOService;
import model.dao.service.PostDAOService;
import model.entities.Category;

/**
 *
 * @author Thanh
 */
@ManagedBean
@RequestScoped
@FacesConverter("CategoryConverter")
public class CategoryConverter implements Converter{
     private final CategoryDAOService  CATEGORY_SERVICE= CategoryDAO.getInstance();
    /**
     * Creates a new instance of CategoryConverter
     */
    public CategoryConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Category category = null;
        if (value != null) {
            try {
                category = CATEGORY_SERVICE.getCategoryByID(Integer.valueOf(value));
            } catch (Exception ex) {
                Logger.getLogger(CategoryConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return category;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Category category = (Category) value;
        return category.getCatID() + "";
    }
    
}
