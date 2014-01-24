package com.discoverydns.dnsapiclient.internal.util.exception;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonGenerationException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.Test;
import org.xbill.DNS.utils.json.exception.JsonDeserializationException;
import org.xbill.DNS.utils.json.exception.JsonSerializationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class JsonProcessingExceptionTranslatorTest {
    @Test
    public void shouldTranslateJsonGenerationException() {
        String originalMessage = "originalMessage";
        JsonGenerationException jsonGenerationException = new JsonGenerationException(originalMessage);

        Exception translatedException =
                JsonProcessingExceptionTranslator.translateJsonProcessingException(jsonGenerationException);

        assertThat(translatedException, instanceOf(DNSAPIClientJsonGenerationException.class));
        assertEquals(DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode.unexpectedGenerationError,
                ((DNSAPIClientJsonGenerationException) translatedException).getExceptionCode());
        assertArrayEquals(new Object[]{originalMessage},
                ((DNSAPIClientJsonGenerationException) translatedException).getObjects());
    }

    @Test
    public void shouldTranslateJsonMappingException() {
        String originalMessage = "originalMessage";
        JsonMappingException jsonMappingException = new JsonMappingException(originalMessage);

        Exception translatedException =
                JsonProcessingExceptionTranslator.translateJsonProcessingException(jsonMappingException);

        assertThat(translatedException, instanceOf(DNSAPIClientJsonMappingException.class));
        assertEquals(DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
                ((DNSAPIClientJsonMappingException) translatedException).getExceptionCode());
        assertArrayEquals(new Object[] {"unknown", originalMessage},
                ((DNSAPIClientJsonMappingException) translatedException).getObjects());
    }

    @Test
    public void shouldTranslateJsonParseException() {
        String originalMessage = "originalMessage";
        JsonParseException jsonParseException = new JsonParseException(originalMessage, JsonLocation.NA);

        Exception translatedException =
                JsonProcessingExceptionTranslator.translateJsonProcessingException(jsonParseException);

        assertThat(translatedException, instanceOf(DNSAPIClientJsonMappingException.class));
        assertEquals(DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
                ((DNSAPIClientJsonMappingException) translatedException).getExceptionCode());
        assertArrayEquals(new Object[] {"unknown", originalMessage + "\n at [Source: N/A; line: -1, column: -1]"},
                ((DNSAPIClientJsonMappingException) translatedException).getObjects());
    }

    @Test
    public void shouldTranslateBaseJsonExceptionIfCauseOtherwise() {
        String originalMessage = "originalMessage";
        JsonGenerationException jsonGenerationException = new JsonGenerationException(
                new JsonSerializationException(
                        JsonSerializationException.JsonSerializationExceptionCode.unexpectedResourceRecordGenerationError,
                        new Object[]{originalMessage}));


        Exception translatedException =
                JsonProcessingExceptionTranslator.translateJsonProcessingException(jsonGenerationException);

        assertThat(translatedException, instanceOf(DNSAPIClientJsonGenerationException.class));
        assertEquals(DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode.unexpectedResourceRecordGenerationError,
                ((DNSAPIClientJsonGenerationException) translatedException).getExceptionCode());
        assertArrayEquals(new Object[]{originalMessage},
                ((DNSAPIClientJsonGenerationException) translatedException).getObjects());
    }

    @Test
    public void shouldTranslateJsonSerializationException() {
        String originalMessage = "originalMessage";
        JsonSerializationException jsonSerializationException = new JsonSerializationException(
                JsonSerializationException.JsonSerializationExceptionCode.unexpectedResourceRecordGenerationError,
                originalMessage);

        Exception translatedException =
                JsonProcessingExceptionTranslator.translateBaseJsonException(jsonSerializationException);

        assertThat(translatedException, instanceOf(DNSAPIClientJsonGenerationException.class));
        assertEquals(DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode.unexpectedResourceRecordGenerationError,
                ((DNSAPIClientJsonGenerationException) translatedException).getExceptionCode());
        assertArrayEquals(new Object[]{originalMessage},
                ((DNSAPIClientJsonGenerationException) translatedException).getObjects());
    }

    @Test
    public void shouldTranslateJsonDeserializationException() {
        String originalMessage = "originalMessage";
        JsonDeserializationException jsonDeserializationException = new JsonDeserializationException(
                JsonDeserializationException.JsonDeserializationExceptionCode.missingField,
                originalMessage);

        Exception translatedException =
                JsonProcessingExceptionTranslator.translateBaseJsonException(jsonDeserializationException);

        assertThat(translatedException, instanceOf(DNSAPIClientJsonMappingException.class));
        assertEquals(DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode.missingField,
                ((DNSAPIClientJsonMappingException) translatedException).getExceptionCode());
        assertArrayEquals(new Object[]{originalMessage},
                ((DNSAPIClientJsonMappingException) translatedException).getObjects());
    }
}

