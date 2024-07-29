package com.inteliense.jflux.http.api.server.exceptions;

import com.inteliense.jflux.exceptions.types.ExceptionAdapter;

public class APIException extends ExceptionAdapter {

    public APIException(String message) { }

    @Override
    protected void onError() {

    }

}
