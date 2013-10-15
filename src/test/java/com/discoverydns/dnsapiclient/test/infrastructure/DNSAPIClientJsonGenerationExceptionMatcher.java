package com.discoverydns.dnsapiclient.test.infrastructure;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonGenerationException;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class DNSAPIClientJsonGenerationExceptionMatcher extends
        BaseMatcher<DNSAPIClientJsonGenerationException> {
    private DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode exceptionCode;
    private Object[] arguments;
    private Class<? extends Throwable> causeClass;
    private Throwable cause;

    public DNSAPIClientJsonGenerationExceptionMatcher(
            final DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode exceptionCode,
            final Throwable cause, final Object[] objects) {
        this.exceptionCode = exceptionCode;
        this.cause = cause;
        this.arguments = objects;
    }

    public DNSAPIClientJsonGenerationExceptionMatcher(
            final DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode exceptionCode,
            final Object[] objects) {
        this.exceptionCode = exceptionCode;
        this.arguments = objects;
    }

    @Override
    public boolean matches(Object item) {
        if (item == null
            || !DNSAPIClientJsonGenerationException.class.isAssignableFrom(item.getClass())) {
            return false;
        }
        DNSAPIClientJsonGenerationException exception = (DNSAPIClientJsonGenerationException) item;
        if (!exceptionCode.equals(exception.getExceptionCode())) {
            return false;
        }
        if (arguments != null) {
            Object[] messageArguments = exception.getObjects();
            if (messageArguments == null || arguments.length != messageArguments.length) {
                return false;
            }
            for (int i = 0 ; i < arguments.length; i++) {
                if (arguments[i] == null) {
                    if (messageArguments[i] != null) {
                        return false;
                    } else {
                        continue;
                    }
                }
                if (!arguments[i].equals(messageArguments[i])) {
                    return false;
                }
            }
        }
        if (cause != null && !cause.equals(exception.getCause())) {
            return false;
        }
        if (causeClass != null) {
            if (exception.getCause() == null || !causeClass.equals(exception.getCause().getClass())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) { }
}
