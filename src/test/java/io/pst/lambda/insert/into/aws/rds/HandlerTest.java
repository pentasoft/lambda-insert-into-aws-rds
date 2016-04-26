/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.insert.into.aws.rds;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.inject.Injector;
import io.pst.lambda.insert.into.aws.rds.exception.DaoException;
import io.pst.lambda.insert.into.aws.rds.model.Message;
import io.pst.lambda.insert.into.aws.rds.persistence.MessageDao;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link Handler} unit test.
 * 
 * @author Daniel Ibanez
 */
public class HandlerTest {

    private Injector mockInjector;
    private MessageDao mockMessageDao;
    private Context mockContext;
    private Message mockMessage;

    @Before
    public void setUp() {
        mockInjector = mock(Injector.class);
        mockMessageDao = mock(MessageDao.class);
        mockContext = mock(Context.class);
        mockMessage = mock(Message.class);
    }

    @Test
    public void handleRequest_WithExistingInjector_UsesExistingInjector(){
        // Given
        Handler handler = new Handler(mockInjector);
        when(mockInjector.getInstance(any(Class.class))).thenReturn(mockMessageDao);

        // When
        handler.handleRequest(mockMessage, mockContext);

        // Then
        verify(mockInjector, times(1)).getInstance(any(Class.class));
    }

    @Test
    public void handleRequest_EverythingIsCorrect_ReturnSuccessString(){
        // Given
        Handler handler = new Handler(mockInjector);
        when(mockInjector.getInstance(any(Class.class))).thenReturn(mockMessageDao);
        when(mockMessage.getName()).thenReturn(UUID.randomUUID().toString());

        // When
        String result = handler.handleRequest(mockMessage, mockContext);

        // Then
        assertThat(result, startsWith("Successfully processed"));
    }

    @Test
    public void handleRequest_ReturnsDaoException_ReturnDaoExceptionString(){
        // Given
        Handler handler = new Handler(mockInjector);
        when(mockInjector.getInstance(any(Class.class))).thenReturn(mockMessageDao);
        doThrow(DaoException.class).when(mockMessageDao).create(any(Message.class));

        // When
        String result = handler.handleRequest(mockMessage, mockContext);

        // Then
        assertThat(result, startsWith("DAO Exception on processing message"));
    }

}

