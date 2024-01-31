package com.redcare.pharmacy.popular.respositories.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import com.redcare.pharmacy.popular.respositories.service.GithubRepositoryService;

@WebMvcTest(controllers = GithubRepositoriesController.class)
class GithubRepositoriesControllerApiTest {

	@Autowired
	private  MockMvc mockMvc;

	@MockBean
	private GithubRepositoryService service;

	@Test
	void testFetchPopularRepositoriesThrowsIllegalArgumentException() throws Exception {

		String expectedMessage = "some message";
		when(service.fetchPopularRepositories(ArgumentMatchers.any())).thenThrow(new IllegalArgumentException(expectedMessage));

		mockMvc.perform(get("/api/v1/repositories/popular")
						.contentType("application/json"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value(expectedMessage))
				.andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.getReasonPhrase()))
				.andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()));
	}



}