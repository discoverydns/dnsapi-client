package com.discoverydns.dnsapiclient.framework.command;

import com.discoverydns.dnsapiclient.framework.internationalisation.InternationalisedException;

public class BaseException extends InternationalisedException {

	private static final long serialVersionUID = -7383158504886816380L;
	private final Enum<?> exceptionCode;

	public BaseException(final Enum<?> exceptionCode, final Throwable cause,
			final Object... objects) {
		super(EnumToPropertyNameConverter.convert(exceptionCode), cause,
				objects);
		this.exceptionCode = exceptionCode;
	}

	public BaseException(final Enum<?> exceptionCode, final Object... objects) {
		super(EnumToPropertyNameConverter.convert(exceptionCode), objects);
		this.exceptionCode = exceptionCode;
	}

	public Enum<?> getExceptionCode() {
		return exceptionCode;
	}

}
