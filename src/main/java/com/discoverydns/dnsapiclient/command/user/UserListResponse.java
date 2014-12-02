package com.discoverydns.dnsapiclient.command.user;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.internal.views.response.UserListView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link UserListCommand},
 * returning the list of retrieved {@link UserRecord}s.
 *
 * A User is a member of an Account, who can connect to the DNSAPI server
 * to perform operations on his behalf.
 *
 * @author Chris Wright
 */
@JsonRootName("UserListResponse")
@JsonPropertyOrder({ "@uri", "userList", "totalCount" })
public class UserListResponse {

	@JsonIgnore
	private final UserListView userListView;

	public UserListResponse(final UserListView userListView) {
		this.userListView = userListView;
	}

    /**
     * @return The URI representing the search query on the DNSAPI server
     */
	@JsonProperty("@uri")
	public URI getURI() {
		return userListView.getUri();
	}

    /**
     * @return The list of retrieved {@link UserRecord}s
     */
	@JsonProperty("userList")
	public List<UserRecord> getUserRecords() {
		return userListView.getUserList();
	}

    /**
     * @return The total count of {@link UserRecord}s that would match the query, if ignoring the pagination limitations.
     */
	@JsonProperty("totalCount")
	public long getTotalRecordCount() {
		return userListView.getTotalCount();
	}

}
