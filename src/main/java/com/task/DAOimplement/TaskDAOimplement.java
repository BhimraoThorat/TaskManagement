package com.task.DAOimplement;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.task.DAO.TaskDAO;
import com.task.Entity.Task;
import com.task.util.HibernateUtil;

public class TaskDAOimplement implements TaskDAO {
    private SessionFactory sessionFactory;

    public TaskDAOimplement() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void addTask(Task task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Task getTaskById(int taskId) {
        Session session = sessionFactory.openSession();
        Task task = null;
        try {
            task = session.get(Task.class, taskId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public void updateTask(Task task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteTask(int taskId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Task task = session.get(Task.class, taskId);
            if (task != null) {
                session.delete(task);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Task";
            Query<Task> query = session.createQuery(hql, Task.class);
            tasks = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
