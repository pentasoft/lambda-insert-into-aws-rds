/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.insert.into.aws.rds.exception;

/**
 *  DAO exception.
 * 
 * @author Daniel Ibanez
 *
 */
public class DaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DaoException() {
        super();
    }

    public DaoException(final String message) {
        super(message);
    }

    public DaoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DaoException(final Throwable throwable) {
        super(throwable);
    }
    
}
