package com.discoverydns.dnsapiclient.internal.views.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("error")
public class ErrorView {

	@JsonProperty("message")
	private String message;

	public String getMessage() {
		return message;
	}

}
