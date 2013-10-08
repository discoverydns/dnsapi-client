package com.discoverydns.dnsapiclient.command.user;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.internal.views.UserListView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("UserListResponse")
@JsonPropertyOrder({ "@uri", "userList", "totalCount" })
public class UserListResponse {

	@JsonIgnore
	private final UserListView userListView;

	public UserListResponse(final UserListView userListView) {
		this.userListView = userListView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return userListView.getUri();
	}

	@JsonProperty("userList")
	public List<UserRecord> getUserRecords() {
		return userListView.getUserList();
	}

	@JsonProperty("totalCount")
	public long getTotalRecordCount() {
		return userListView.getTotalCount();
	}

}
