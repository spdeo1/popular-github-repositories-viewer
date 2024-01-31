package com.redcare.pharmacy.popular.respositories;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationBaseTest {

	@Autowired
	protected MockMvc mockMvc;

	@RegisterExtension
	static WireMockExtension wireMockServer = WireMockExtension.newInstance()
			.options(
					WireMockConfiguration.wireMockConfig()
							.dynamicPort()
							.usingFilesUnderClasspath("wiremock")
			).build();

	@DynamicPropertySource
	static void configurationProperties(DynamicPropertyRegistry registry) {
		System.out.println("baseUrl:" + wireMockServer.baseUrl());
		registry.add("com.redcare.pharmacy.github.feign-client-base-url", wireMockServer::baseUrl);

	}

}
