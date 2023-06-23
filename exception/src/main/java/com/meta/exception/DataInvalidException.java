package com.meta.exception;

/**
 * <p>
 * Its indicate the custom exception.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class DataInvalidException extends RuntimeException {

    public DataInvalidException(final String message) {
        super(message);
    }
}
