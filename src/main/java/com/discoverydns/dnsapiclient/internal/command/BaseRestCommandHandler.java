package com.discoverydns.dnsapiclient.internal.command;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.discoverydns.dnsapiclient.DNSAPIClientCommandMetaData;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientHttpException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientHttpException.DNSAPIClientHttpExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandHandler;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.views.ErrorView;

public abstract class BaseRestCommandHandler<T, R> implements CommandHandler<T> {

	private final static Logger log = LoggerFactory
			.getLogger(BaseRestCommandHandler.class);

	private final Method method;
	private final int expectedStatusCode;
	private final MediaType expectedMediaType;
	private final String expectedMediaTypeString;

	public BaseRestCommandHandler(final Method method,
			final int expectedResponseCode) {
		this.method = method;
		this.expectedStatusCode = expectedResponseCode;
		this.expectedMediaType = null;
		this.expectedMediaTypeString = null;
	}

	public BaseRestCommandHandler(final Method method,
			final int expectedResponseCode, final MediaType expectedMediaType) {
		this.method = method;
		this.expectedStatusCode = expectedResponseCode;
		this.expectedMediaType = expectedMediaType;
		this.expectedMediaTypeString = null;
	}

	public BaseRestCommandHandler(final Method method,
			final int expectedResponseCode, final String expectedMediaTypeString) {
		this.method = method;
		this.expectedStatusCode = expectedResponseCode;
		this.expectedMediaType = null;
		this.expectedMediaTypeString = expectedMediaTypeString;
	}

	public abstract WebTarget getWebTarget(T command,
			CommandMetaData commandMetaData);

	public abstract InvocationBuildInvoker getInvocationBuildInvoker(T command,
			CommandMetaData commandMetaData);

	public abstract InvocationBuilderFactory getInvocationBuilderFactory(
			T command, CommandMetaData commandMetaData);

	public abstract R getCommandResponse(Response restResponse,
			CommandMetaData commandMetaData);

	public <E> E getResponseEntity(final Class<E> entityClass,
			final Response restResponse) {
		if (!restResponse.hasEntity()) {
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.expectedEntity);
		}
		return restResponse.readEntity(entityClass);
	}

	@Override
	public Object execute(final T command, final CommandMetaData commandMetaData)
			throws Throwable {
		final WebTarget webTarget = getWebTarget(command, commandMetaData);
		Invocation.Builder invocationBuilder = getInvocationBuilderFactory(
				command, commandMetaData).buildInvocationBuilder(webTarget);

		final String clientTransactionId = (String) commandMetaData
				.get(DNSAPIClientCommandMetaData.CLIENT_TRANSACTION_ID);
		invocationBuilder = invocationBuilder.header(
				DNSAPIClientHeaders.CLIENT_TRANSACTION_ID, clientTransactionId);
		if (expectedMediaType != null) {
			invocationBuilder = invocationBuilder.accept(expectedMediaType);
		} else if (expectedMediaTypeString != null) {
			invocationBuilder = invocationBuilder.accept(expectedMediaTypeString);
		}
		final Invocation invocation = getInvocationBuildInvoker(command,
				commandMetaData).invoke(invocationBuilder, method);
		final Response restResponse = invocation.invoke();
		final String serverTransactionId = restResponse
				.getHeaderString(DNSAPIClientHeaders.SERVER_TRANSACTION_ID);
		commandMetaData.put(DNSAPIClientCommandMetaData.SERVER_TRANSACTION_ID,
				serverTransactionId);
		if (restResponse.getStatus() != expectedStatusCode) {
			String errorMessage = null;
			if (restResponse.hasEntity()) {
				try {
					final ErrorView errorView = getResponseEntity(
							ErrorView.class, restResponse);
					errorMessage = errorView.getMessage();
				} catch (final Throwable t) {
					log.debug("Error getting or no error entity found", t);
				}
			}
			throw new DNSAPIClientHttpException(
					DNSAPIClientHttpExceptionCode.serverError,
					restResponse.getStatus(), restResponse.getStatus(),
					errorMessage != null ? errorMessage : restResponse
							.getStatusInfo().getReasonPhrase());
		}
		final R commandResponse = getCommandResponse(restResponse,
				commandMetaData);
		restResponse.close();

		return commandResponse;
	}
}
