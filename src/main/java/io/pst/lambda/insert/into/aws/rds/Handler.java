/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.insert.into.aws.rds;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.pst.lambda.insert.into.aws.rds.exception.DaoException;
import io.pst.lambda.insert.into.aws.rds.inject.CommonModule;
import io.pst.lambda.insert.into.aws.rds.model.Message;
import io.pst.lambda.insert.into.aws.rds.persistence.MessageDao;

/**
 * AWS Lambda handler processing messages entries.
 * 
 * @author Daniel Ibanez
 *
 */
public class Handler implements RequestHandler<Message, String> {

    private Injector injector;

    public Handler(Injector injector) {
        this();
        this.injector = injector;
    }

    public Handler() {
    }

    public String handleRequest(Message message, Context context) {

        this.injector = (injector != null) ? injector : Guice.createInjector(new CommonModule(context));

        final MessageDao messageDao = injector.getInstance(MessageDao.class);
        try {
            messageDao.create(message);
            return "Successfully processed " + String.format("%s message", message.getName());
        }
        catch (DaoException e) {
            return "DAO Exception on processing message " + String.format("%s message", message.getName());
        }
    }

}
