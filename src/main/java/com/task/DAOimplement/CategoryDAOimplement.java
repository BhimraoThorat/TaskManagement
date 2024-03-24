package com.task.DAOimplement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.task.DAO.CategoryDAO;
import com.task.Entity.Category;
import com.task.util.HibernateUtil;

public class CategoryDAOimplement implements CategoryDAO {
    private SessionFactory sessionFactory;

    public CategoryDAOimplement() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void addCategory(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(category);
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
    public Category getCategoryById(int categoryId) {
        Session session = sessionFactory.openSession();
        Category category = null;
        try {
            category = session.get(Category.class, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(category);
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
    public void deleteCategory(int categoryId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                session.delete(category);
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
    public List<Category> getAllCategories() {
        List<Category> categories = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Category";
            Query<Category> query = session.createQuery(hql, Category.class);
            categories = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
