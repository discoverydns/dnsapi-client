package com.discoverydns.dnsapiclient.internal.views.response;

import java.net.URI;
import java.util.Map;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("message")
public class MessageGetView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("messageCode")
	private String messageCode;
	@JsonProperty("enqueueDate")
	private LocalDateTime enqueueDate;
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("message")
	private String message;
	@JsonProperty("targetAccountId")
	private String targetAccountId;
	@JsonProperty("targetAccountName")
	private String targetAccountName;
	@JsonProperty("acknowledgeDate")
	private LocalDateTime acknowledgeDate;
	@JsonProperty("acknowledgeByUserId")
	private String acknowledgeByUserId;
	@JsonProperty("acknowledgeByUserName")
	private String acknowledgeByUserName;
	@JsonProperty("parameters")
	private Map<String, String> parameters;

	public URI getURI() {
		return uri;
	}

	public String getId() {
		return id;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public LocalDateTime getEnqueueDate() {
		return enqueueDate;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public String getTargetAccountId() {
		return targetAccountId;
	}

	public String getTargetAccountName() {
		return targetAccountName;
	}

	public LocalDateTime getAcknowledgeDate() {
		return acknowledgeDate;
	}

	public String getAcknowledgeByUserId() {
		return acknowledgeByUserId;
	}

	public String getAcknowledgeByUserName() {
		return acknowledgeByUserName;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

}
