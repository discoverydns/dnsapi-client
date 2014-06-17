package com.discoverydns.dnsapiclient.command.message;

import java.net.URI;
import java.util.Map;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * A Message is a notification from the DNSAPI server, for the targeted sponsor
 * Account's Users, offering information about changes performed by the server
 * on a related object in the system.
 * 
 * @author Arnaud Dumont
 */
@JsonRootName("message")
public class MessageRecord {
	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("messageCode")
	private String messageCode;
	@JsonProperty("targetAccountId")
	private String targetAccountId;
	@JsonProperty("enqueueDate")
	private LocalDateTime enqueueDate;
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("message")
	private String message;
	@JsonProperty("parameters")
	private Map<String, String> parameters;

	/**
	 * @return The URI representing the Message on the DNSAPI server
	 */
	public URI getUri() {
		return uri;
	}

	/**
	 * @return The Message UUID
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return The Message code
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @return The UUID of the Account whom this Message is for
	 */
	public String getTargetAccountId() {
		return targetAccountId;
	}

	/**
	 * @return The Message enqueue date
	 */
	public LocalDateTime getEnqueueDate() {
		return enqueueDate;
	}

	/**
	 * @return The Message human-readable subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return The Message human-readable message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return The Message parameters, for machine use. The contents of the map
	 *         will depend on the message code
	 */
	public Map<String, String> getParameters() {
		return parameters;
	}
}
