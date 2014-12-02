package com.discoverydns.dnsapiclient.internal.command.account;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.account.AccountGetCommand;
import com.discoverydns.dnsapiclient.command.account.AccountGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.AccountGetView;

public class AccountGetCommandHandler extends
		BaseRestCommandHandler<AccountGetCommand, AccountGetResponse> {

	private final WebTarget accountGetTarget;

	public AccountGetCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.accountGetTarget = baseWebTarget.path("accounts/{accountId}");
	}

	@Override
	public WebTarget getWebTarget(final AccountGetCommand command,
			final CommandMetaData commandMetaData) {
		WebTarget webTarget;
		try {
			webTarget = accountGetTarget.resolveTemplate("accountId",
					command.getIdOrIdentifier());
		} catch (Throwable t) {
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.requiredParameterMissing, t, "idOrIdentifier");
		}
		return webTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final AccountGetCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final AccountGetCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public AccountGetResponse getCommandResponse(final Response restResponse,
			final CommandMetaData commandMetaData) {
		final AccountGetView accountGetView = getResponseEntity(
				AccountGetView.class, restResponse);
		return new AccountGetResponse(accountGetView);
	}

}
