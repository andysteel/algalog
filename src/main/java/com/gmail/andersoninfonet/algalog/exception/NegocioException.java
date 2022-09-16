package com.gmail.andersoninfonet.algalog.exception;

public class NegocioException extends RuntimeException {

    /**
     * @param message
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public NegocioException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public NegocioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
