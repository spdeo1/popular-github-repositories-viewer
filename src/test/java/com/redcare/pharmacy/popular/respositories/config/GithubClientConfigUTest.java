package com.redcare.pharmacy.popular.respositories.config;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import feign.Logger;
import feign.Request;

class GithubClientConfigUTest {

	@Test
	void testGithubClientConfig() {

		long githubClientTimeout = 6000L;
		GithubClientConfig githubClientConfig = new GithubClientConfig(githubClientTimeout);

		Request.Options options = githubClientConfig.options();
		Logger.Level feignLoggerLevel = githubClientConfig.feignLoggerLevel();

		assertThat(options.connectTimeoutMillis()).isEqualTo(githubClientTimeout);
		assertThat(options.readTimeoutMillis()).isEqualTo(githubClientTimeout);
		assertThat(options.isFollowRedirects()).isFalse();
		assertThat(feignLoggerLevel).isEqualTo(Logger.Level.NONE);

	}

}