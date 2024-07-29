package com.inteliense.jflux.http.api.base.endpoints.expectations;

import com.inteliense.jflux.http.api.base.endpoints.expectations.builtin.BodyExpectations;
import com.inteliense.jflux.http.api.base.endpoints.expectations.builtin.CookieExpectations;
import com.inteliense.jflux.http.api.base.endpoints.expectations.builtin.FormDataExpectations;
import com.inteliense.jflux.http.api.base.endpoints.expectations.builtin.HeaderExpectations;

public class ApiExpectations {

    private BodyExpectations bodyExpectations = null;
    private CookieExpectations cookieExpectations = null;
    private FormDataExpectations formDataExpectations = null;
    private HeaderExpectations headerExpectations = null;

    public ApiExpectations() { }

    public void putBodyExpectations(BodyExpectations bodyExpectations) { this.bodyExpectations = bodyExpectations; }
    public void putCookieExpectations(CookieExpectations cookieExpectations) { this.cookieExpectations = cookieExpectations; }
    public void putFormDataExpectations(FormDataExpectations formDataExpectations) { this.formDataExpectations = formDataExpectations; }
    public void putHeaderExpectations(HeaderExpectations headerExpectations) { this.headerExpectations = headerExpectations; }

}
