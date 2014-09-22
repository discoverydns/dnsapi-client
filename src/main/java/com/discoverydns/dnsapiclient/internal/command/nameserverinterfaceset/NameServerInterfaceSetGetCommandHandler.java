package com.discoverydns.dnsapiclient.internal.command.nameserverinterfaceset;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterfaceSetGetCommand;
import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterfaceSetGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.NameServerInterfaceSetGetView;

public class NameServerInterfaceSetGetCommandHandler
		extends
		BaseRestCommandHandler<NameServerInterfaceSetGetCommand, NameServerInterfaceSetGetResponse> {

	private final WebTarget nameServerInterfaceSetGetTarget;

	public NameServerInterfaceSetGetCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.nameServerInterfaceSetGetTarget = baseWebTarget
				.path("nameserverinterfacesets/{nameServerInterfaceSetId}");
	}

	@Override
	public WebTarget getWebTarget(
			final NameServerInterfaceSetGetCommand command,
			final CommandMetaData commandMetaData) {
		WebTarget webTarget;
		try {
			webTarget = nameServerInterfaceSetGetTarget.resolveTemplate(
					"nameServerInterfaceSetId", command.getIdOrName());
		} catch (Throwable t) {
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.requiredParameterMissing, t, "idOrName");
		}
		return webTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final NameServerInterfaceSetGetCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final NameServerInterfaceSetGetCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public NameServerInterfaceSetGetResponse getCommandResponse(
			final Response restResponse, final CommandMetaData commandMetaData) {
		final NameServerInterfaceSetGetView nameServerInterfaceSetGetView = getResponseEntity(
				NameServerInterfaceSetGetView.class, restResponse);
		return new NameServerInterfaceSetGetResponse(
				nameServerInterfaceSetGetView);
	}

}
