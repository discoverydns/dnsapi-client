package com.discoverydns.dnsapiclient.command.message;

import java.net.URI;
import java.util.Map;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.internal.views.MessageGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the
 * {@link com.discoverydns.dnsapiclient.DNSAPIClient} from the DNSAPI server
 * subsequently to the sending of a {@link MessageGetCommand}, describing the
 * details of the retrieved Message.
 * 
 * A Message is a notification from the DNSAPI server, for the targeted sponsor
 * Account's Users, offering information about changes performed by the server
 * on a related object in the system.
 * 
 * @author Chris Wright
 */
@JsonRootName("MessageGetResponse")
@JsonPropertyOrder({ "@uri", "id", "messageCode", "enqueueDate", "subject",
		"message", "targetAccountId", "targetAccountName", "acknowledgeDate",
		"acknowledgeByUserId", "acknowledgeByUserName", "parameters" })
public class MessageGetResponse {

	@JsonIgnore
	private final MessageGetView messageGetView;

	public MessageGetResponse(MessageGetView messageGetView) {
		this.messageGetView = messageGetView;
	}

	/**
	 * @return The URI representing the Message on the DNSAPI server
	 */
	@JsonProperty("@uri")
	public URI getURI() {
		return messageGetView.getURI();
	}

	/**
	 * @return The Message UUID
	 */
	@JsonProperty("id")
	public String getId() {
		return messageGetView.getId();
	}

	/**
	 * @return The Message code
	 */
	@JsonProperty("messageCode")
	public String getMessageCode() {
		return messageGetView.getMessageCode();
	}

	/**
	 * @return The Message enqueue date
	 */
	@JsonProperty("enqueueDate")
	public LocalDateTime getEnqueueDate() {
		return messageGetView.getEnqueueDate();
	}

	/**
	 * @return The Message human-readable subject
	 */
	@JsonProperty("subject")
	public String getSubject() {
		return messageGetView.getSubject();
	}

	/**
	 * @return The Message human-readable message
	 */
	@JsonProperty("message")
	public String getMessage() {
		return messageGetView.getMessage();
	}

	/**
	 * @return The UUID of the Account whom this Message is for
	 */
	@JsonProperty("targetAccountId")
	public String getTargetAccountId() {
		return messageGetView.getTargetAccountId();
	}

	/**
	 * @return The Name of the Account whom this Message is for
	 */
	@JsonProperty("targetAccountName")
	public String getTargetAccountName() {
		return messageGetView.getTargetAccountName();
	}

	/**
	 * @return The Message acknowledge date, or null if not acknowledged yet
	 */
	@JsonProperty("acknowledgeDate")
	public LocalDateTime getAcknowledgeDate() {
		return messageGetView.getAcknowledgeDate();
	}

	/**
	 * @return The UUID of the user who acknowledged the Message, or null if not
	 *         acknowledged yet
	 */
	@JsonProperty("acknowledgeByUserId")
	public String getAcknowledgeByUserId() {
		return messageGetView.getAcknowledgeByUserId();
	}

	/**
	 * @return The name of the user who acknowledged the Message, or null if not
	 *         acknowledged yet
	 */
	@JsonProperty("acknowledgeByUserName")
	public String getAcknowledgeByUserName() {
		return messageGetView.getAcknowledgeByUserName();
	}

	/**
	 * @return The Message parameters, for machine use. The contents of the map
	 *         will depend on the message code
	 */
	@JsonProperty("parameters")
	public Map<String, String> getParameters() {
		return messageGetView.getParameters();
	}

}
