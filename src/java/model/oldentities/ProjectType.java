/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.oldentities;

import java.util.List;

/**
 *
 * @author Admin
 */
public class ProjectType {
    private int typeID;
    private String typeName;
    private int active;
    private List<Project> projectList;

    public ProjectType() {
    }

    public ProjectType(int typeID, String typeName, int active) {
        this.typeID = typeID;
        this.typeName = typeName;
        this.active = active;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public boolean equals(Object obj) {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
