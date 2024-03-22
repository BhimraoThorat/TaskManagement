package com.task.DAOimplement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.task.DAO.CommentDAO;
import com.task.Entity.Comment;
import com.task.util.HibernateUtil;


public class CommentDAOimplement implements CommentDAO {
    private SessionFactory sessionFactory;

    public CommentDAOimplement() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void addComment(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(comment);
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
    public Comment getCommentById(int commentId) {
        Session session = sessionFactory.openSession();
        Comment comment = null;
        try {
            comment = session.get(Comment.class, commentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return comment;
    }

    @Override
    public void updateComment(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(comment);
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
    public void deleteComment(int commentId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Comment comment = session.get(Comment.class, commentId);
            if (comment != null) {
                session.delete(comment);
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
}
