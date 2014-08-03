/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import model.dao.ProjectTypeDAOImpl;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.dao.service.TypeDAO;
import model.entities.Type;

/**
 *
 * @author cong0_000
 */
@FacesConverter("typeConverter")
public class TypeConverter implements Converter {

    private final TypeDAO TYPE_SERVICE = ProjectTypeDAOImpl.getInstance();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Type projectType = null;
        if (value != null) {
            projectType = TYPE_SERVICE.getTypeByName(value);
        }
        return projectType;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Type t = (Type)value;
        return t.getTypeName();
    }

}
