package com.redcare.pharmacy.popular.respositories.controller;


import static com.redcare.pharmacy.popular.respositories.controller.dom.Language.JAVA;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.redcare.pharmacy.popular.respositories.IntegrationBaseTest;
import com.redcare.pharmacy.popular.respositories.controller.dom.Language;

class GithubRepositoriesControllerITest extends IntegrationBaseTest {

	@Test
	void testFetchPopularRepositoriesWithoutFilter() throws Exception {
		this.mockMvc.perform(get("/api/v1/repositories/popular").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", hasSize(10)))
				.andExpect(jsonPath("$[0].description").value("freeCodeCamp.org's open-source codebase and curriculum. Learn to code for free."))
				.andExpect(jsonPath("$[0].link").value("https://github.com/freeCodeCamp/freeCodeCamp"))
				.andExpect(jsonPath("$[0].stars").value(382533))
				.andExpect(jsonPath("$[0].language").value("TypeScript"))
				.andExpect(jsonPath("$[0].createdAt").value("2014-12-24T17:49:19"))
				.andExpect(jsonPath("$[0].name").value("freeCodeCamp"));
	}

	@Test
	void testFetchPopularRepositoriesWithAllFilters() throws Exception {

		LocalDate givenDate = LocalDate.parse("2023-12-14", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.mockMvc.perform(get("/api/v1/repositories/popular")
						.param("language", JAVA.name())
						.param("createdDate", givenDate.toString())
						.param("top", "20")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", hasSize(20)))
				.andExpect(jsonPath("$[*].language", everyItem(is("Java"))))
				.andExpect(jsonPath("$[*].createdAt", everyItem(greaterThanOrEqualTo(givenDate.toString()))));

	}

	@Test
	void testFetchPopularRepositoriesThrowsServiceUnavailable() throws Exception {

		LocalDate givenDate = LocalDate.parse("1991-12-14", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.mockMvc.perform(get("/api/v1/repositories/popular")
						.param("createdDate", givenDate.toString())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Service Unavailable"))
				.andExpect(jsonPath("$.status").value(INTERNAL_SERVER_ERROR.getReasonPhrase()))
				.andExpect(jsonPath("$.code").value(INTERNAL_SERVER_ERROR.value()));

	}

	@Test
	void testFetchPopularRepositoriesThrowsNotModifiedError() throws Exception {

		LocalDate givenDate = LocalDate.parse("1991-12-15", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.mockMvc.perform(get("/api/v1/repositories/popular")
						.param("createdDate", givenDate.toString())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Not Modified"))
				.andExpect(jsonPath("$.status").value(INTERNAL_SERVER_ERROR.getReasonPhrase()))
				.andExpect(jsonPath("$.code").value(INTERNAL_SERVER_ERROR.value()));

	}

	@Test
	void testFetchPopularRepositoriesThrowsValidationFailedException() throws Exception {

		LocalDate givenDate = LocalDate.parse("1991-12-16", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.mockMvc.perform(get("/api/v1/repositories/popular")
						.param("createdDate", givenDate.toString())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Validation failed, or the endpoint has been spammed."))
				.andExpect(jsonPath("$.status").value(INTERNAL_SERVER_ERROR.getReasonPhrase()))
				.andExpect(jsonPath("$.code").value(INTERNAL_SERVER_ERROR.value()));

	}

	@Test
	void testFetchPopularRepositoriesThrowsUnknownRemoteException() throws Exception {

		LocalDate givenDate = LocalDate.parse("1991-12-17", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.mockMvc.perform(get("/api/v1/repositories/popular")
						.param("createdDate", givenDate.toString())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("unknown error"))
				.andExpect(jsonPath("$.status").value(INTERNAL_SERVER_ERROR.getReasonPhrase()))
				.andExpect(jsonPath("$.code").value(INTERNAL_SERVER_ERROR.value()));

	}

}