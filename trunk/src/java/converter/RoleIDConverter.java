package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.dao.RoleDAOImpl;
import model.dao.service.RoleDAO;
import model.entities.Role;

@FacesConverter("roleConverter")
public class RoleIDConverter implements Converter {

    private final RoleDAO ROLE_SERVICE = RoleDAOImpl.getInstance();

    /**
     * Creates a new instance of RoleIDConverter
     */
    public RoleIDConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Role roleName = null;
        if (value != null) {
            roleName = ROLE_SERVICE.getRoleByName(value);
            roleName.setRoleName(value);
        }
        return roleName;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String roleName = "";
        if (value instanceof Role) {
            Role role = (Role) value;
            roleName = role.getRoleName();
        } else if (value instanceof String) {
            roleName = (String) value;
        }
        return roleName;
    }
}
