/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.insert.into.aws.rds.inject;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.pst.lambda.insert.into.aws.rds.persistence.MessageDao;
import io.pst.lambda.insert.into.aws.rds.persistence.impl.MessageRdsDaoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Guice module containing common objects bindings.
 * 
 * @author Daniel Ibanez
 *
 */
public class CommonModule extends AbstractModule {

    private final Context context;
    private final EntityManagerFactory entityManagerFactory;

    public CommonModule(final Context context) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("todos");
        this.context = context;
    }
    
    @Override
    protected void configure() {
        bind(EntityManagerFactory.class).toInstance(entityManagerFactory);
        bind(Context.class).toInstance(context);
        bind(MessageDao.class).to(MessageRdsDaoImpl.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}
