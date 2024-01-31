package com.redcare.pharmacy.popular.respositories.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.redcare.pharmacy.popular.respositories.client.GithubClient;
import com.redcare.pharmacy.popular.respositories.client.dom.GithubApiResponse;
import com.redcare.pharmacy.popular.respositories.client.dom.GithubRepository;
import com.redcare.pharmacy.popular.respositories.controller.dom.GithubRepositoryResponseDto;
import com.redcare.pharmacy.popular.respositories.controller.dom.GithubSearchFilter;

@ExtendWith(MockitoExtension.class)
class GithubRepositoryServiceImplUTest {

	@Mock
	private GithubClient githubClient;

	@InjectMocks
	private GithubRepositoryServiceImpl githubRepositoryService;

	@Test
	void testFetchPopularRepositories() {

		GithubSearchFilter filter = new GithubSearchFilter();
		GithubApiResponse apiResponse = new GithubApiResponse();
		apiResponse.setRepositories(Collections.singletonList(createMockRepository()));

		when(githubClient.getRepositories(anyString(), any(), any(), anyInt())).thenReturn(apiResponse);

		List<GithubRepositoryResponseDto> result = githubRepositoryService.fetchPopularRepositories(filter);

		assertThat(result).hasSize(1);
		GithubRepositoryResponseDto responseDto = result.get(0);
		assertThat(responseDto.getName()).isEqualTo("TestRepo");
		assertThat(responseDto.getStars()).isEqualTo(100);

	}

	@Test
	void testFetchPopularRepositoriesWithNullFilterThrowsIllegalArgumentException() {

		assertThatThrownBy(() -> githubRepositoryService.fetchPopularRepositories(null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("filter cannot be null");

	}

	@Test
	void testFetchPopularRepositoriesWithNegativeTopValueInFilterThrowsIllegalArgumentException() {

		GithubSearchFilter filter = new GithubSearchFilter();
		filter.setTop(-20);
		assertThatThrownBy(() -> githubRepositoryService.fetchPopularRepositories(filter))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("top should be positive");

	}

	private GithubRepository createMockRepository() {
		GithubRepository repository = new GithubRepository();
		repository.setName("TestRepo");
		repository.setHtmlUrl("https://github.com/test/repo");
		repository.setDescription("Test description");
		repository.setWatchersCount(100);
		repository.setCreatedAt(LocalDateTime.now());
		repository.setLanguage("Java");
		return repository;
	}

}