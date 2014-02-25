package com.discoverydns.dnsapiclient.internal.command.message;

import com.discoverydns.dnsapiclient.command.message.MessagePollCommand;
import com.discoverydns.dnsapiclient.command.message.MessagePollResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.MessagePollView;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MessagePollCommandHandler extends
        BaseRestCommandHandler<MessagePollCommand, MessagePollResponse> {
    private final WebTarget messagePollTarget;

    public MessagePollCommandHandler(final WebTarget baseWebTarget) {
        super(Method.GET, Response.Status.OK.getStatusCode(),
                MediaType.APPLICATION_JSON_TYPE);
        this.messagePollTarget = baseWebTarget.path("messages/");
    }

    @Override
    public WebTarget getWebTarget(MessagePollCommand command, CommandMetaData commandMetaData) {
        WebTarget resolvedMessagePollTarget = messagePollTarget;
        if (command.getSearchMessageType() != null) {
            resolvedMessagePollTarget = resolvedMessagePollTarget.queryParam(
                    "searchMessageType", command.getSearchMessageType());
        }
        if (command.getSearchRelatedObjectId() != null) {
            resolvedMessagePollTarget = resolvedMessagePollTarget.queryParam(
                    "searchRelatedObjectId", command.getSearchRelatedObjectId());
        }
        return resolvedMessagePollTarget;
    }

    @Override
    public InvocationBuildInvoker getInvocationBuildInvoker(MessagePollCommand command,
                                                            CommandMetaData commandMetaData) {
        return new NoEntityInvocationBuildInvoker();
    }

    @Override
    public InvocationBuilderFactory getInvocationBuilderFactory(MessagePollCommand command,
                                                                CommandMetaData commandMetaData) {
        return new NoEntityInvocationBuilderFactory();
    }

    @Override
    public MessagePollResponse getCommandResponse(Response restResponse,
                                                  CommandMetaData commandMetaData) {
        final MessagePollView messagePollView = getResponseEntity(MessagePollView.class, restResponse);

        return new MessagePollResponse(messagePollView);
    }
}
