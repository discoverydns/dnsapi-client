package com.discoverydns.dnsapiclient.command.nameServerInterfaceSet;

import java.net.Inet4Address;
import java.net.Inet6Address;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Physical interface of a NameServer Interface Set, capable of answering to DNS queries.
 * Returned in the {@link NameServerInterfaceSetGetResponse}.
 *
 * @author Chris Wright
 */
public class NameServerInterface {

	@JsonProperty("order")
	private int order;
	@JsonProperty("name")
	private String name;
	@JsonProperty("ipv4Address")
	private Inet4Address ipv4Address;
	@JsonProperty("ipv6Address")
	private Inet6Address ipv6Address;

    /**
     * @return The order of the NameServer Interface in its Set
     */
	public int getOrder() {
		return order;
	}

    /**
     * @return The name of the NameServer Interface
     */
	public String getName() {
		return name;
	}

    /**
     * @return The I.P.v4 address of the NameServer Interface
     */
	public Inet4Address getIpv4Address() {
		return ipv4Address;
	}

    /**
     * @return The I.P.v6 address of the NameServer Interface
     */
	public Inet6Address getIpv6Address() {
		return ipv6Address;
	}

    /**
     * Sets the order of the NameServer Interface in its Set.
     * @param order The order to set
     */
	public void setOrder(int order) {
		this.order = order;
	}

    /**
     * Sets the name of the NameServer Interface.
     * @param name The name to set
     */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * Sets the I.P.v4 address of the NameServer Interface.
     * @param ipv4Address The I.P.v4 address to set
     */
	public void setIpv4Address(Inet4Address ipv4Address) {
		this.ipv4Address = ipv4Address;
	}

    /**
     * Sets the I.P.v6 address of the NameServer Interface.
     * @param ipv6Address The I.P.v6 address to set
     */
	public void setIpv6Address(Inet6Address ipv6Address) {
		this.ipv6Address = ipv6Address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ipv4Address == null) ? 0 : ipv4Address.hashCode());
		result = prime * result
				+ ((ipv6Address == null) ? 0 : ipv6Address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
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
		if (!(obj instanceof NameServerInterface)) {
			return false;
		}
		NameServerInterface other = (NameServerInterface) obj;
		if (ipv4Address == null) {
			if (other.ipv4Address != null) {
				return false;
			}
		} else if (!ipv4Address.equals(other.ipv4Address)) {
			return false;
		}
		if (ipv6Address == null) {
			if (other.ipv6Address != null) {
				return false;
			}
		} else if (!ipv6Address.equals(other.ipv6Address)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (order != other.order) {
			return false;
		}
		return true;
	}

}
