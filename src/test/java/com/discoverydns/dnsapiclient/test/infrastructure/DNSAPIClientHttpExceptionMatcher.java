package com.discoverydns.dnsapiclient.test.infrastructure;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientHttpException;

public class DNSAPIClientHttpExceptionMatcher extends BaseExceptionMatcher {
    private final int statusCode;

    public DNSAPIClientHttpExceptionMatcher(
            DNSAPIClientHttpException.DNSAPIClientHttpExceptionCode exceptionCode,
            int statusCode,
            Object[] objects) {
        super(DNSAPIClientHttpException.class, exceptionCode, objects);
        this.statusCode = statusCode;
    }

    @Override
    public boolean matches(Object item) {
        return super.matches(item)
                && ((DNSAPIClientHttpException)item).getStatusCode() == statusCode;
    }
}
