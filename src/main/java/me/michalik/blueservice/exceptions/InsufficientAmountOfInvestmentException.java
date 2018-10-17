package me.michalik.blueservice.exceptions;

public class InsufficientAmountOfInvestmentException extends RuntimeException {

    public InsufficientAmountOfInvestmentException() {
    }

    public InsufficientAmountOfInvestmentException(String message) {
        super(message);
    }

    public InsufficientAmountOfInvestmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientAmountOfInvestmentException(Throwable cause) {
        super(cause);
    }

    public InsufficientAmountOfInvestmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
