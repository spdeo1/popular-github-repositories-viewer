package com.redcare.pharmacy.popular.respositories.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.Request.Options;

public class GithubClientConfig {

	private final long githubClientTimeOutInMillis;

	public GithubClientConfig(@Value("${com.redcare.pharmacy.github.feign-client-timeout:6000}") final long githubClientTimeOutInMillis) {
		this.githubClientTimeOutInMillis = githubClientTimeOutInMillis;
	}

	@Bean
	public Options options() {

		return new Options(
				githubClientTimeOutInMillis, TimeUnit.MILLISECONDS,
				githubClientTimeOutInMillis, TimeUnit.MILLISECONDS,
				false);
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.NONE;
	}
}
