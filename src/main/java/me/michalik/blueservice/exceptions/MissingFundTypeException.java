package me.michalik.blueservice.exceptions;

public class MissingFundTypeException extends RuntimeException {

    public MissingFundTypeException() {
        super();
    }

    public MissingFundTypeException(String message) {
        super(message);
    }

    public MissingFundTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingFundTypeException(Throwable cause) {
        super(cause);
    }

    public MissingFundTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
