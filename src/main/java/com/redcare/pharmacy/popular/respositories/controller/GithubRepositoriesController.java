package com.redcare.pharmacy.popular.respositories.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redcare.pharmacy.popular.respositories.controller.dom.GithubRepositoryResponseDto;
import com.redcare.pharmacy.popular.respositories.controller.dom.GithubSearchFilter;
import com.redcare.pharmacy.popular.respositories.service.GithubRepositoryService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/repositories")
public class GithubRepositoriesController {

	private final GithubRepositoryService githubRepositoryService;

	@Operation(summary = "Retrieve a list of the most popular repositories.")
	@GetMapping("/popular")
	public ResponseEntity<List<GithubRepositoryResponseDto>> fetchPopularRepositories(@ParameterObject @Valid GithubSearchFilter githubSearchFilter) {
		List<GithubRepositoryResponseDto> popularRepositories = githubRepositoryService.fetchPopularRepositories(githubSearchFilter);
		return ResponseEntity.ok(popularRepositories);
	}
}
