package com.discoverydns.dnsapiclient.command.message;

import com.discoverydns.dnsapiclient.command.message.contents.MessageContents;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.joda.time.LocalDateTime;

import java.net.URI;

/**
 * A Message is a notification from the DNSAPI server, for the targeted sponsor Account's Users,
 * offering information about changes performed by the server on a related object in the system.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("message")
public class Message {
    private final URI uri;
    private final String id;
    private final MessageType messageType;
    private final String relatedObjectId;
    private final String sponsorAccountId;
    private final LocalDateTime createDate;
    private final LocalDateTime acknowledgedDate;
    private final String message;
    private final MessageContents messageContents;

    public Message(URI uri, String id, MessageType messageType,
                   String relatedObjectId, String sponsorAccountId,
                   LocalDateTime createDate, LocalDateTime acknowledgedDate,
                   String message, MessageContents messageContents) {
        this.uri = uri;
        this.id = id;
        this.messageType = messageType;
        this.relatedObjectId = relatedObjectId;
        this.sponsorAccountId = sponsorAccountId;
        this.createDate = createDate;
        this.acknowledgedDate = acknowledgedDate;
        this.message = message;
        this.messageContents = messageContents;
    }

    /**
     * @return The URI representing the retrieved Message on the DNSAPI server
     */
    public URI getURI() {
        return uri;
    }

    /**
     * @return The Message UUID
     */
    public String getId() {
        return id;
    }

    /**
     * @return The Message type
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * @return The related object's UUID of the Message
     */
    public String getRelatedObjectId() {
        return relatedObjectId;
    }

    /**
     * @return The UUID of the Account whom this Message is for
     */
    public String getSponsorAccountId() {
        return sponsorAccountId;
    }

    /**
     * @return The Message creation date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @return The Message acknowledge date, or null if not acknowledged yet
     */
    public LocalDateTime getAcknowledgedDate() {
        return acknowledgedDate;
    }

    /**
     * @return The Message human-readable message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return The Message contents, to be parsed accordingly to the message type
     */
    public MessageContents getMessageContents() {
        return messageContents;
    }
}
