package com.redcare.pharmacy.popular.respositories.exception.dom;

public class GithubClientRemoteException extends RuntimeException {

	public GithubClientRemoteException() {
		super();
	}

	public GithubClientRemoteException(final String message) {
		super(message);
	}

	public GithubClientRemoteException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public GithubClientRemoteException(final Throwable cause) {
		super(cause);
	}

	protected GithubClientRemoteException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
