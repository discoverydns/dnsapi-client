package com.discoverydns.dnsapiclient.internal.json;

import com.discoverydns.dnsapiclient.internal.util.exception.JsonProcessingExceptionTranslator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.xbill.DNS.utils.json.exception.BaseJsonException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class ErrorHandlingJacksonJsonProvider extends JacksonJsonProvider {
    public ErrorHandlingJacksonJsonProvider(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public void writeTo(Object value, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException {
        try {
            super.writeTo(value, type, genericType, annotations, mediaType, httpHeaders, entityStream);
        } catch (Exception exception) {
            if (JsonProcessingException.class.isAssignableFrom(exception.getClass())) {
                exception = JsonProcessingExceptionTranslator.translateJsonProcessingException(
                        (JsonProcessingException) exception);
            } else if (BaseJsonException.class.isAssignableFrom(exception.getClass())) {
                exception = JsonProcessingExceptionTranslator.translateBaseJsonException(
                        (BaseJsonException) exception);
            }
            if (IOException.class.isAssignableFrom(exception.getClass())) {
                throw (IOException) exception;
            } else {
                throw (RuntimeException) exception;
            }
        }
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType,
                           Annotation[] annotations, MediaType mediaType,
                           MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException {
        try {
            return super.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
        } catch (Exception exception) {
            if (JsonProcessingException.class.isAssignableFrom(exception.getClass())) {
                exception = JsonProcessingExceptionTranslator.translateJsonProcessingException(
                        (JsonProcessingException) exception);
            } else if (BaseJsonException.class.isAssignableFrom(exception.getClass())) {
                exception = JsonProcessingExceptionTranslator.translateBaseJsonException(
                        (BaseJsonException) exception);
            }
            if (IOException.class.isAssignableFrom(exception.getClass())) {
                throw (IOException) exception;
            } else {
                throw (RuntimeException) exception;
            }
        }
    }
}
