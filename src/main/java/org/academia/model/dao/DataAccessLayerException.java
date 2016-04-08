package org.academia.model.dao;

/**
 * Created by codecadet on 08/04/16.
 */
/**
 * Represents Exceptions thrown by the DAO.
 */
public class DataAccessLayerException extends RuntimeException {
    public DataAccessLayerException() {
    }

    public DataAccessLayerException(String message) {
        super(message);
    }

    public DataAccessLayerException(Throwable cause) {
        super(cause);
    }

    public DataAccessLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}