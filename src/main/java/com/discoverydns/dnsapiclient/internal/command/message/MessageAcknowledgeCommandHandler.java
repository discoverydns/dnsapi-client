package com.discoverydns.dnsapiclient.internal.command.message;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.message.MessageAcknowledgeCommand;
import com.discoverydns.dnsapiclient.command.message.MessageAcknowledgeResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.request.MessageAcknowledgeView;
import com.discoverydns.dnsapiclient.internal.views.response.MessageGetView;

public class MessageAcknowledgeCommandHandler
		extends
		BaseRestCommandHandler<MessageAcknowledgeCommand, MessageAcknowledgeResponse> {

	private final WebTarget messageAcknowledgeTarget;

	public MessageAcknowledgeCommandHandler(final WebTarget baseWebTarget) {
		super(Method.PUT, Response.Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.messageAcknowledgeTarget = baseWebTarget
				.path("messages/{messageId}/acknowledge");
	}

	@Override
	public WebTarget getWebTarget(MessageAcknowledgeCommand command,
			CommandMetaData commandMetaData) {
		WebTarget webTarget;
		try {
			webTarget = messageAcknowledgeTarget.resolveTemplate("messageId",
					command.getId());
		} catch (Throwable t) {
			throw new DNSAPIClientException(
					DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
					t, "id");
		}
		return webTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final MessageAcknowledgeCommand command,
			final CommandMetaData commandMetaData) {
		return new WithEntityInvocationBuilderFactory(
				MediaType.APPLICATION_JSON_TYPE);
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final MessageAcknowledgeCommand command,
			final CommandMetaData commandMetaData) {
		return new WithEntityInvocationBuildInvoker<MessageAcknowledgeView>(
				new MessageAcknowledgeView());
	}

	@Override
	public MessageAcknowledgeResponse getCommandResponse(Response restResponse,
			CommandMetaData commandMetaData) {
		final MessageGetView messageGetView = getResponseEntity(
				MessageGetView.class, restResponse);
		return new MessageAcknowledgeResponse(messageGetView);
	}
}
