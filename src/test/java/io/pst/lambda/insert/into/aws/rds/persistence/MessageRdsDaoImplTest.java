/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.insert.into.aws.rds.persistence;

import io.pst.lambda.insert.into.aws.rds.exception.DaoException;
import io.pst.lambda.insert.into.aws.rds.model.Message;
import io.pst.lambda.insert.into.aws.rds.persistence.impl.MessageRdsDaoImpl;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link MessageRdsDaoImpl} unit test.
 * 
 * @author Daniel Ibanez
 */
public class MessageRdsDaoImplTest {

    private EntityManagerFactory mockEntityManagerFactory;
    private EntityManager mockEntityManager;
    private Message mockMessage;

    @Before
    public void setUp() {
        mockEntityManagerFactory = mock(EntityManagerFactory.class);
        mockEntityManager = mock(EntityManager.class);
        when(mockEntityManager.getTransaction()).thenReturn(mock(EntityTransaction.class));
        mockMessage = mock(Message.class);
    }

    @Test
    public void create_EverythingIsCorrect_ClosesEntityManager(){
        // Given
        MessageDao messageDao = new MessageRdsDaoImpl(mockEntityManagerFactory);
        when(mockEntityManagerFactory.createEntityManager()).thenReturn(mockEntityManager);

        // When
        messageDao.create(mockMessage);

        // Then
        verify(mockEntityManager, times(1)).close();
    }

    @Test(expected = DaoException.class)
    public void create_PersistThrowsPersistenceException_ThrowsDaoExceptionAndClosesEntityManager(){
        // Given
        MessageDao messageDao = new MessageRdsDaoImpl(mockEntityManagerFactory);
        when(mockEntityManagerFactory.createEntityManager()).thenReturn(mockEntityManager);
        doThrow(PersistenceException.class).when(mockEntityManager).persist(any());

        // When
        messageDao.create(mockMessage);

        // Then
        verify(mockEntityManager, times(1)).close();
    }

}

