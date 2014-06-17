package com.discoverydns.dnsapiclient.internal.command.message;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.message.MessageGetCommand;
import com.discoverydns.dnsapiclient.command.message.MessageGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.MessageGetView;

public class MessageGetCommandHandler extends
		BaseRestCommandHandler<MessageGetCommand, MessageGetResponse> {

	private final WebTarget messageGetTarget;

	public MessageGetCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.messageGetTarget = baseWebTarget.path("messages/{messageId}");
	}

	@Override
	public WebTarget getWebTarget(final MessageGetCommand command,
			final CommandMetaData commandMetaData) {
		WebTarget webTarget;
		try {
			webTarget = messageGetTarget.resolveTemplate("messageId",
					command.getId());
		} catch (Throwable t) {
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.requiredParameterMissing, t, "id");
		}
		return webTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final MessageGetCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final MessageGetCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public MessageGetResponse getCommandResponse(final Response restResponse,
			final CommandMetaData commandMetaData) {
		final MessageGetView messageGetView = getResponseEntity(
				MessageGetView.class, restResponse);
		return new MessageGetResponse(messageGetView);
	}

}
