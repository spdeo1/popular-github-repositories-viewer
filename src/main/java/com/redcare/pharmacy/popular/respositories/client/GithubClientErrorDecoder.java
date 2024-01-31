package com.redcare.pharmacy.popular.respositories.client;

import com.redcare.pharmacy.popular.respositories.exception.dom.GithubClientRemoteException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GithubClientErrorDecoder implements ErrorDecoder {
	@Override
	public Exception decode(final String methodKey, final Response response) {
		switch (response.status()) {
			case 304:
				return new GithubClientRemoteException("Not Modified");
			case 422:
				return new GithubClientRemoteException("Validation failed, or the endpoint has been spammed.");
			case 503:
				return new GithubClientRemoteException("Service Unavailable");
			default:
				return new GithubClientRemoteException("unknown error");
		}
	}
}
