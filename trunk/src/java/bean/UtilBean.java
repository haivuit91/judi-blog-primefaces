/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author cong0_000
 */
@ManagedBean
@RequestScoped
public class UtilBean {

    private String contextPath;
    /**
     * Creates a new instance of UtilBean
     */
    public UtilBean() {
    }

    /**
     * @return the contextPath
     */
    public String getContextPath() {
        contextPath = util.Constants.CONTEXT_PATH;
        return contextPath;
    }
    
}
