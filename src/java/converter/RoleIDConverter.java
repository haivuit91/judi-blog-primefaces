/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.dao.RoleDAO;
import model.dao.service.RoleDAOService;
import model.entities.Role;

/**
 *
 * @author AMIN
 */
@ManagedBean
@RequestScoped
@FacesConverter("roleConverter")
public class RoleIDConverter implements Converter {

    private final RoleDAOService ROLE_SERVICE = RoleDAO.getInstance();

    /**
     * Creates a new instance of RoleIDConverter
     */
    public RoleIDConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Role role = null;
        if (value != null) {
            role = ROLE_SERVICE.getRolesByName(value);
            role.getRoleName();
        }
        return role;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Role role = (Role) value;
        return role.getRoleName()+ "";
    }
}
