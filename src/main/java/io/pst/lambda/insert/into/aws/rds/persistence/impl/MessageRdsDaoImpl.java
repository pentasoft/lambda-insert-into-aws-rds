package io.pst.lambda.insert.into.aws.rds.persistence.impl;

import com.google.inject.Inject;
import io.pst.lambda.insert.into.aws.rds.exception.DaoException;
import io.pst.lambda.insert.into.aws.rds.model.Message;
import io.pst.lambda.insert.into.aws.rds.persistence.MessageDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

public class MessageRdsDaoImpl implements MessageDao {

    private EntityManagerFactory entityManagerFactory;

    @Inject
    public MessageRdsDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void create(Message message) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(message);
            entityManager.getTransaction().commit();
        }
        catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException(e);
        }
        finally {
            entityManager.close();
        }
    }

}
