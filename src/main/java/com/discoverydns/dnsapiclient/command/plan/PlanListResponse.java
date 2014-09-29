package com.discoverydns.dnsapiclient.command.plan;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.internal.views.response.PlanListView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link PlanListCommand},
 * returning the list of retrieved {@link PlanRecord}s.
 *
 * A Plan describes the available application features granted to an Account,
 * as well as the details and units used for billing this Account.
 *
 * @author Chris Wright
 */
@JsonRootName("PlanListResponse")
@JsonPropertyOrder({ "@uri", "planList", "totalCount" })
public class PlanListResponse {

	@JsonIgnore
	private final PlanListView planListView;

	public PlanListResponse(final PlanListView planListView) {
		this.planListView = planListView;
	}

    /**
     * @return The URI representing the search query on the DNSAPI server
     */
	@JsonProperty("@uri")
	public URI getURI() {
		return planListView.getUri();
	}

    /**
     * @return The list of retrieved {@link PlanRecord}s
     */
	@JsonProperty("planList")
	public List<PlanRecord> getPlanRecords() {
		return planListView.getPlanList();
	}

    /**
     * @return The total count of {@link PlanRecord}s that would match the query, if ignoring the pagination limitations.
     */
	@JsonProperty("totalCount")
	public long getTotalRecordCount() {
		return planListView.getTotalCount();
	}

}
