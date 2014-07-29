/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.olddao.UserDAO;
import model.olddao.service.UserDAOService;
import model.oldentities.User;

/**
 *
 * @author cong0_000
 */
@FacesConverter("userConverter")
public class UserConverter implements Converter{

    private final UserDAOService USER_SERVICE = UserDAO.getInstance();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        User user = null;
        if (value != null) {
            user = USER_SERVICE.getUserByUserName(value);
            user.setUserName(value);
        }
        return user;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String name = "";
        if(value instanceof User){
            User user = (User) value;
            name = user.getUserName();
        }else if( value instanceof String){
            name = (String)value;
        }
        return name;
    }
    
}
