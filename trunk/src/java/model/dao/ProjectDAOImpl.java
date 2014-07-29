/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entities.Project;
import model.entities.Type;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.dao.service.ProjectDAO;

/**
 *
 * @author Tuanka
 */
public class ProjectDAOImpl implements ProjectDAO {

    private static ProjectDAOImpl projectDAO;

    public static ProjectDAOImpl getInstance() {
        if (projectDAO == null) {
            projectDAO = new ProjectDAOImpl();
        }
        return projectDAO;
    }

    private HibernateUtil util;
    private Session session;

    @Override
    public List<Project> getProjects() {
        List<Project> projects = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM Project";
            Query query = session.createQuery(sql);
            projects = query.list();
            for (Project project : projects) {
                project.setProjectUserDetailses(ProjectUserDAOImpl.getInstance().getPUByProject(project));
            }
            session = util.getSessionFactory().openSession();
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
    public Project getProjectByID(int projectID) {
        Project project = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            project = (Project) session.get(Project.class, projectID);
            session = util.getSessionFactory().openSession();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return project;
    }

    @Override
    public List<Project> getProjectByName(String projectName) {
        List<Project> projects = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM Project as p WHERE p.projectName = :projectName";
            Query query = session.createQuery(sql);
            query.setParameter("projectName", projectName);
            projects = query.list();
//            session = util.getSessionFactory().openSession();
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
    public List<Project> getProjectByType(int typeID) {
        List<Project> projects = null;
        Type type = ProjectTypeDAOImpl.getInstance().getTypeByID(typeID);
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM Project as p WHERE p.type = :type";
            Query query = session.createQuery(sql);
            query.setParameter("type", type);
            projects = query.list();
//            session = util.getSessionFactory().openSession();
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
    public boolean createProject(Project project) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(project);
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isCheck = false;
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean updateProject(Project project) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(project);
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isCheck = false;
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean deleteProject(Project project) {
        boolean isCheck = false;
        if (!session.isOpen()) {
            session = util.getSessionFactory().openSession();
        }
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(project);
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean activeProject(Project project) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE Project as p set p.active = true WHERE p.projectId = :projectID";
            Query query = session.createQuery(sql);
            query.setParameter("projectID", project.getProjectId());
            query.executeUpdate();
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isCheck;
    }

    @Override
    public boolean inactiveProject(Project project) {
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE Project as p set p.active = false WHERE p.projectId = :projectID";
            Query query = session.createQuery(sql);
            query.setParameter("projectID", project.getProjectId());
            query.executeUpdate();
            tx.commit();
            isCheck = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isCheck;
    }

}
