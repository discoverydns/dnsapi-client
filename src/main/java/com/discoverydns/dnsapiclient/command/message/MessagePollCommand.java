package com.discoverydns.dnsapiclient.command.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to poll for the oldest outstanding Message corresponding to the given search criteria,
 * in the Account's message queue on the server.
 *
 * A Message is a notification from the DNSAPI server, for the targeted sponsor Account's Users,
 * offering information about changes performed by the server on a related object in the system.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("MessagePollCommand")
@JsonPropertyOrder({ "searchMessageType", "searchRelatedObjectId" })
public class MessagePollCommand {
    @JsonProperty("searchMessageType")
    private String searchMessageType;
    @JsonProperty("searchRelatedObjectId")
    private String searchRelatedObjectId;

    public static class Builder {
        private String searchMessageType;
        private String searchRelatedObjectId;

        /**
         * Sets the type of the Message to look for.
         * @param searchMessageType The type of the Message to look for
         * @return The {@link Builder}
         */
        public Builder withSearchMessageType(final MessageType searchMessageType) {
            this.searchMessageType = searchMessageType.name();
            return this;
        }

        /**
         * Sets the related object's UUID of the Message to look for.
         * @param searchRelatedObjectId The related object's UUID of the Message to look for
         * @return The {@link Builder}
         */
        public Builder withSearchRelatedObjectId(final String searchRelatedObjectId) {
            this.searchRelatedObjectId = searchRelatedObjectId;
            return this;
        }

        /**
         * Builds the {@link MessagePollCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link MessagePollCommand}
         */
        public MessagePollCommand build() {
            MessagePollCommand messagePollCommand = new MessagePollCommand();

            messagePollCommand.searchMessageType = searchMessageType;
            messagePollCommand.searchRelatedObjectId = searchRelatedObjectId;

            return messagePollCommand;
        }
    }

    private MessagePollCommand() {
    }

    /**
     * @return The type of the Message to look for, set on the command.
     */
    public String getSearchMessageType() {
        return searchMessageType;
    }

    /**
     * @return The related object's UUID of the Message to look for, set on the command.
     */
    public String getSearchRelatedObjectId() {
        return searchRelatedObjectId;
    }
}
