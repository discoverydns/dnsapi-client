package com.discoverydns.dnsapiclient.command.user;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * List record representing a User, from the list returned in the {@link UserListResponse}.
 *
 * A User is a member of an Account, who can connect to the DNSAPI server
 * to perform operations on his behalf.
 *
 * @author Chris Wright
 */
@JsonRootName("user")
public class UserRecord {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("username")
	private String username;
	@JsonProperty("status")
	private String status;
	@JsonProperty("createDate")
	private String createDate;
	@JsonProperty("lastUpdateDate")
	private String lastUpdateDate;

	public UserRecord() {
	}

	public UserRecord(URI uri, String id, String name, String username,
			String status, String createDate, String lastUpdateDate) {
		this.uri = uri;
		this.id = id;
		this.name = name;
		this.username = username;
		this.status = status;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
	}

    /**
     * @return The URI representing the User list record on the DNSAPI server
     */
	public URI getUri() {
		return uri;
	}

    /**
     * @return The User UUID
     */
	public String getId() {
		return id;
	}

    /**
     * @return The User name
     */
	public String getName() {
		return name;
	}

    /**
     * @return The User username
     */
	public String getUsername() {
		return username;
	}

    /**
     * @return The User status
     */
	public String getStatus() {
		return status;
	}

    /**
     * @return The creation date of the User
     */
	public String getCreateDate() {
		return createDate;
	}

    /**
     * @return The last update date of the User
     */
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserRecord)) {
			return false;
		}
		UserRecord other = (UserRecord) obj;
		if (createDate == null) {
			if (other.createDate != null) {
				return false;
			}
		} else if (!createDate.equals(other.createDate)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (lastUpdateDate == null) {
			if (other.lastUpdateDate != null) {
				return false;
			}
		} else if (!lastUpdateDate.equals(other.lastUpdateDate)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (uri == null) {
			if (other.uri != null) {
				return false;
			}
		} else if (!uri.equals(other.uri)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

}
