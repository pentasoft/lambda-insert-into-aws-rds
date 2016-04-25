package io.pst.lambda.insert.into.aws.rds.persistence;

import io.pst.lambda.insert.into.aws.rds.exception.DaoException;
import io.pst.lambda.insert.into.aws.rds.model.Message;

public interface MessageDao {
     void create(Message message) throws DaoException;
 }
