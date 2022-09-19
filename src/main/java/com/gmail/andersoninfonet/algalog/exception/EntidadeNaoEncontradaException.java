package com.gmail.andersoninfonet.algalog.exception;

/**
 * @since 0.0.1
 */
public class EntidadeNaoEncontradaException extends NegocioException {

    /**
     * @param message String
     * @since 0.0.1
     */
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

    /**
     * @param cause Throwable
     * @since 0.0.1
     */
    public EntidadeNaoEncontradaException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message String
     * @param cause Throwable
     * @since 0.0.1
     */
    public EntidadeNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message String
     * @param cause Throwable
     * @param enableSuppression boolean
     * @param writableStackTrace boolean
     * @since 0.0.1
     */
    public EntidadeNaoEncontradaException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
