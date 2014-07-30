package converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.dao.RoleDAOImpl;
import model.dao.service.RoleDAO;
import model.entities.Role;

@ManagedBean
@RequestScoped
@FacesConverter("roleIDConverter")
public class RoleIDConverter implements Converter {

    private final RoleDAO ROLE_SERVICE = RoleDAOImpl.getInstance();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Role role = null;
        if (value != null) {
            role = ROLE_SERVICE.getRoleByName(value);
            role.setRoleName(value);
        }
        System.out.println("get: " + role.getRoleName() + " - " + role.getRoleId());
        return role;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String name = "";
        if (value instanceof Role) {
            Role role = (Role) value;
            name = role.getRoleName();
            System.out.println("if: " + name);
        } else if (value instanceof String) {
            name = (String) value;
            System.out.println("else: " + name);
        }
        return name;
    }

}
