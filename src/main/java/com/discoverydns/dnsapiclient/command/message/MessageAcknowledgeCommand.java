package com.discoverydns.dnsapiclient.command.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to acknowledge a specific Message, in the Account's message queue on the server.
 *
 * A Message is a notification from the DNSAPI server, for the targeted sponsor Account's Users,
 * offering information about changes performed by the server on a related object in the system.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("MessageAcknowledgeCommand")
@JsonPropertyOrder({ "id" })
public class MessageAcknowledgeCommand {
    @JsonProperty("id")
    private String id;

    public static class Builder {
        private String id;

        /**
         * Sets the UUID of the Message to acknowledge.
         * @param id The UUID of the Message to acknowledge
         * @return The {@link Builder}
         */
        public Builder withId(final String id) {
            this.id = id;
            return this;
        }

        /**
         * Builds the {@link MessageAcknowledgeCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link MessageAcknowledgeCommand}
         */
        public MessageAcknowledgeCommand build() {
            MessageAcknowledgeCommand messageAcknowledgeCommand
                    = new MessageAcknowledgeCommand();

            messageAcknowledgeCommand.id = id;

            return messageAcknowledgeCommand;
        }
    }

    private MessageAcknowledgeCommand() {
    }

    /**
     * @return The UUID of the Message to acknowledge, set on the command.
     */
    public String getId() {
        return id;
    }
}
