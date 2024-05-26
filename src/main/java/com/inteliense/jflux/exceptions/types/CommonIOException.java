package com.inteliense.jflux.exceptions.types;

public class CommonIOException extends ExceptionAdapter {

    public CommonIOException() {
        super();
    }

    public CommonIOException(String message) {
        super(message);
    }

    public CommonIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonIOException(Throwable cause) {
        super(cause);
    }

    protected CommonIOException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    protected void onError() {

    }
}
