package com.discoverydns.dnsapiclient.command.message.contents;

import com.discoverydns.dnsapiclient.command.message.MessageType;

/**
 * Abstract parent class for all Message contents, which will then be parsed accordingly to the Message type.
 *
 * @author Arnaud Dumont
 */
public abstract class MessageContents {
    private final MessageType relatedMessageType;

    protected MessageContents(MessageType relatedMessageType) {
        this.relatedMessageType = relatedMessageType;
    }

    public MessageType getRelatedMessageType() {
        return relatedMessageType;
    }
}
