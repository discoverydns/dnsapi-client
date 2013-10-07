package com.discoverydns.dnsapiclient.command.nameServerSet;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nameServerSet")
public class NameServerSetRecord {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("createDate")
	private String createDate;
	@JsonProperty("lastUpdateDate")
	private String lastUpdateDate;

	public NameServerSetRecord() {
	}

	public NameServerSetRecord(URI uri, String id, String name,
			String createDate, String lastUpdateDate) {
		this.uri = uri;
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
	}

	public URI getUri() {
		return uri;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCreateDate() {
		return createDate;
	}

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
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
		if (!(obj instanceof NameServerSetRecord)) {
			return false;
		}
		NameServerSetRecord other = (NameServerSetRecord) obj;
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
		if (uri == null) {
			if (other.uri != null) {
				return false;
			}
		} else if (!uri.equals(other.uri)) {
			return false;
		}
		return true;
	}

}
