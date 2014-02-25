package com.discoverydns.dnsapiclient.command.message;

import com.discoverydns.dnsapiclient.internal.views.MessagePollView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link MessagePollCommand},
 * describing the details of the retrieved Message.
 *
 * A Message is a notification from the DNSAPI server, for the targeted sponsor Account's Users,
 * offering information about changes performed by the server on a related object in the system.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("MessagePollResponse")
@JsonPropertyOrder({ "message" })
public class MessagePollResponse {
    @JsonIgnore
    private final MessagePollView messagePollView;

    public MessagePollResponse(final MessagePollView messagePollView) {
        this.messagePollView = messagePollView;
    }

    /**
     * @return The retrieved Message on the DNSAPI server, or null if no message was found
     */
    @JsonProperty("message")
    public Message getMessage() {
        return messagePollView.getMessage();
    }

    /**
     * @return The count of outstanding Messages on the DNSAPI server, in the Account's message queue
     */
    @JsonProperty("outstandingMessagesCount")
    public Integer getOutstandingMessagesCount() {
        return messagePollView.getOutstandingMessagesCount();
    }
}
