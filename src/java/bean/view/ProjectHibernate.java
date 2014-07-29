/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.view;

import entity.Project;
import entity.Type;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.dao.ProjectDAOImpl;
import model.dao.ProjectTypeDAOImpl;
import model.dao.service.ProjectDAO;
import model.dao.service.TypeDAO;

/**
 *
 * @author batman
 */
@ManagedBean
@RequestScoped
public class ProjectHibernate {
    
    private final ProjectDAO PROJECT_HIBERNATE = ProjectDAOImpl.getInstance();
    private final TypeDAO TYPE_HIBERNATE = ProjectTypeDAOImpl.getInstance();
    

    private Project project;
    private List<Type> types;
    private List<Project> projects;
    private List<Project> filteredProjects;
    private Type type;
    
    public ProjectHibernate() {
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Project> getProjects() {
////        projects = PROJECT_HIBERNATE.getProjects();
//        projects = PROJECT_HIBERNATE.getProjects();
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getFilteredProjects() {
        return filteredProjects;
    }

    public void setFilteredProjects(List<Project> filteredProjects) {
        this.filteredProjects = filteredProjects;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    
}
