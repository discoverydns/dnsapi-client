package com.discoverydns.dnsapiclient.internal.views.response;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.command.user.UserRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("users")
public class UserListView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("userList")
	private List<UserRecord> userList;
	@JsonProperty("totalCount")
	private long totalCount;

	public URI getUri() {
		return uri;
	}

	public List<UserRecord> getUserList() {
		return userList;
	}

	public long getTotalCount() {
		return totalCount;
	}

}
