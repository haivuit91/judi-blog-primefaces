package model.entities;
// Generated Jul 25, 2014 9:34:05 AM by Hibernate Tools 3.6.0


/**
 * ProjectUserDetails generated by hbm2java
 */
public class ProjectUserDetails implements java.io.Serializable {

    private Integer projectUserId;
    private Project project;
    private User user;
    private boolean creator;

    public ProjectUserDetails() {
    }

    public ProjectUserDetails(Project project, boolean creator) {
        this.project = project;
        this.creator = creator;
    }

    public ProjectUserDetails(Project project, User user, boolean creator) {
        this.project = project;
        this.user = user;
        this.creator = creator;
    }

    public Integer getProjectUserId() {
        return this.projectUserId;
    }

    public void setProjectUserId(Integer projectUserId) {
        this.projectUserId = projectUserId;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCreator() {
        return this.creator;
    }

    public void setCreator(boolean creator) {
        this.creator = creator;
    }

}
