package org.saad.tradehub_be.errorhandler;

public class TimeOutException extends RuntimeException {
    public TimeOutException(String errorText) {
        super(errorText);
    }
}
