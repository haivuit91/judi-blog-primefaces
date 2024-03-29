/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao;

import model.entities.Project;
import model.entities.ProjectUserDetails;
import model.entities.User;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.dao.service.ProjectUserDAO;

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
    public List<ProjectUserDetails> getPUByProject(Project project) {
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
    public List<Project> getMyProject(User user) {
        List<Project> projects = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT p.project FROM ProjectUserDetails as p WHERE p.user = :user and p.creator = true";
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
    public List<Project> getProjectsByUser(User user){
        List<Project> projects = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT p.project FROM ProjectUserDetails as p WHERE p.user = :user";
            Query query = session.createQuery(sql);
            query.setParameter("user", user);
            projects = query.list();
            session.close();
            for (Project project : projects) {
                project.setUsers(ProjectUserDAOImpl.getInstance().getUsersByProject(project));
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
    public List<User> getUsersByProject(Project project){
        List<User> users = null;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT p.user FROM ProjectUserDetails as p WHERE p.project = :project";
            Query query = session.createQuery(sql);
            query.setParameter("project", project);
            users = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }
    
    @Override
    public boolean isExistUserInProject(Project project, User user) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "FROM ProjectUserDetails as p WHERE p.user = :user and p.project = :project";
            Query query = session.createQuery(sql);
            query.setParameter("user", user);
            query.setParameter("project", project);
            if (query.list().size() > 0) {
                isCheck = true;
            }
            tx.commit();
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
    public boolean createPUD(ProjectUserDetails pud) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(pud);
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
    public boolean updatePUD(ProjectUserDetails pud) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(pud);
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
    public boolean deletePUByProject(Project project) {
        
        boolean isCheck = false;
        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "DELETE FROM ProjectUserDetails as p WHERE p.project = :project";
            Query query = session.createQuery(sql);
            query.setParameter("project", project);
            int result = query.executeUpdate();
            tx.commit();
            System.out.println(result);
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
    public boolean removeUserLeaProject(User user, Project project) {
        boolean isCheck = false;

        session = util.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "DELETE FROM ProjectUserDetails as p WHERE p.user = :user and p.project = :project";
            Query query = session.createQuery(sql);
            query.setParameter("user", user);
            query.setParameter("project", project);
            query.executeUpdate();
            tx.commit();
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

}
