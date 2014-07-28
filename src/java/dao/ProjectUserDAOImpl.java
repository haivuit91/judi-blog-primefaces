/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Project;
import entity.ProjectUserDetails;
import entity.User;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.ProjectUserDAO;

/**
 *
 * @author cong0_000
 */
public class ProjectUserDAOImpl implements ProjectUserDAO{

    private static ProjectUserDAOImpl projectUserDAO;

    public static ProjectUserDAOImpl getInstance() {
        if (projectUserDAO == null) {
            projectUserDAO = new ProjectUserDAOImpl();
        }
        return projectUserDAO;
    }
    
    private HibernateUtil util;
    private Session session;

    @Override
    public List<ProjectUserDetails> getAllPUList() {
        List<ProjectUserDetails> puList = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM ProjectUserDetails";
            Query query = session.createQuery(sql);
            puList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return puList;
    }

    @Override
    public ProjectUserDetails getPUByID(int puID) {
        ProjectUserDetails pu = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            pu = (ProjectUserDetails) session.get(ProjectUserDetails.class, puID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pu;
    }

    @Override
    public List<ProjectUserDetails> getPUByProjectID(Project project) {
        List<ProjectUserDetails> puList = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM ProjectUserDetails WHERE project = :project";
            Query query = session.createQuery(sql);
            query.setParameter("project", project);
            puList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return puList;
    }

    @Override
    public List<Project> getProjectByUser(User user) {
        List<Project> projects = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT p.project FROM ProjectUserDetails as p WHERE p.user = :user";
            Query query = session.createQuery(sql);
            query.setParameter("user", user);
            projects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return projects;
    }

    @Override
    public List<User> getUserByProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUserNotJoin(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkJoinUser(User user, Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isExistUserInProject(Project project, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createPUD(ProjectUserDetails pud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePUD(ProjectUserDetails pud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUserJoinedProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePUByProjectID(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeUserLeaProject(User user, Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
