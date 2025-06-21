package com.codegym.baitap2.service;

import com.codegym.baitap2.model.Feedback;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Feedback> findAllToday() {
        String queryStr = "SELECT f FROM Feedback f WHERE f.date = :todayDate ORDER BY f.id DESC";
        TypedQuery<Feedback> query = entityManager.createQuery(queryStr, Feedback.class);
        query.setParameter("todayDate", LocalDate.now());
        return query.getResultList();
    }

    @Override
    public Feedback findById(Long id) {
        Session session = sessionFactory.openSession();
        Feedback feedback = session.get(Feedback.class, id);
        session.close();
        return feedback;
    }

    @Override
    public void save(Feedback feedback) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            feedback.setDate(LocalDate.now());
            session.saveOrUpdate(feedback);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void like(Long id) {
        Feedback feedback = findById(id);
        if (feedback != null) {
            feedback.setLikes(feedback.getLikes() + 1);
            save(feedback);
        }
    }
}