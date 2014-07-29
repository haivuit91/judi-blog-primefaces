package entity;
// Generated Jul 25, 2014 9:34:05 AM by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "project", catalog = "db_judiwebsite"
)
public class Project implements java.io.Serializable {

    private Integer projectId;
    private Type type;
    private String projectName;
    private String description;
    private Date startDate;
    private int duration;
    private boolean active;
    private List<ProjectUserDetails> projectUserDetails = new ArrayList<ProjectUserDetails>();

    public Project() {
    }

    public Project(Type type, String projectName, String description, Date startDate, int duration, boolean active) {
        this.type = type;
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.duration = duration;
        this.active = active;
    }

    public Project(Integer projectId, Type type, String projectName, String description, Date startDate, int duration, boolean active) {
        this.projectId = projectId;
        this.type = type;
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.duration = duration;
        this.active = active;
    }

    
    
    public Project(Type type, String projectName, String description, Date startDate, int duration, boolean active, List<ProjectUserDetails> projectUserDetails) {
        this.type = type;
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.duration = duration;
        this.active = active;
        this.projectUserDetails = projectUserDetails;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "projectID", unique = true, nullable = false)
    public Integer getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeID", nullable = false)
    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(name = "projectName", nullable = false, length = 105)
    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "description", nullable = false, length = 205)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "startDate", nullable = false, length = 10)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "duration", nullable = false)
    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Column(name = "active", nullable = false)
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL)
    public List<ProjectUserDetails> getProjectUserDetailses() {
        return this.projectUserDetails;
    }

    public void setProjectUserDetailses(List<ProjectUserDetails> projectUserDetails) {
        this.projectUserDetails = projectUserDetails;
    }

}
