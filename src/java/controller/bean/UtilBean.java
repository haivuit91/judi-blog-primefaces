/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cong0_000
 */
@ManagedBean
@RequestScoped
public class UtilBean {

    private String contextPath;
    private String date;
    private String titlePage;
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

    /**
     * @return the date
     */
    public String getDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        date=df.format(new Date());
        return date;
    }

    /**
     * @return the titlePage
     */
    public String getTitlePage() {
        if(titlePage == null){
            titlePage = "Home";
        }
        return titlePage;
    }

    /**
     * @param titlePage the titlePage to set
     */
    public void setTitlePage(String titlePage) {
        this.titlePage = titlePage;
    }
    
}
