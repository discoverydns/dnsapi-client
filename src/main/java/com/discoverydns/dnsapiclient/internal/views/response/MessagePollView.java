package com.discoverydns.dnsapiclient.internal.views.response;

import com.discoverydns.dnsapiclient.command.message.MessageRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("messagePoll")
public class MessagePollView {
	@JsonProperty("message")
	private MessageRecord messageRecord;
	@JsonProperty("outstandingMessageCount")
	private Long outstandingMessageCount;

	public MessageRecord getMessageRecord() {
		return messageRecord;
	}

	public Long getOutstandingMessageCount() {
		return outstandingMessageCount;
	}
}
