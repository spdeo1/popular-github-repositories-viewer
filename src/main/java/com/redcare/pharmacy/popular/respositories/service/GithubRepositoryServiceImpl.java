package com.redcare.pharmacy.popular.respositories.service;

import static com.redcare.pharmacy.popular.respositories.client.dom.GithubQueryQualifier.CREATED_DATE_AND_AFTER;
import static com.redcare.pharmacy.popular.respositories.client.dom.GithubQueryQualifier.POPULAR_REPOSITORIES;
import static com.redcare.pharmacy.popular.respositories.client.dom.GithubQueryQualifier.PROGRAMMING_LANGUAGE;
import static com.redcare.pharmacy.popular.respositories.client.dom.SortAttribute.STARS;
import static com.redcare.pharmacy.popular.respositories.client.dom.SortOrder.DESC;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.redcare.pharmacy.popular.respositories.client.GithubClient;
import com.redcare.pharmacy.popular.respositories.client.GithubClientSearchQueryBuilder;
import com.redcare.pharmacy.popular.respositories.client.dom.GithubApiResponse;
import com.redcare.pharmacy.popular.respositories.controller.dom.GithubRepositoryResponseDto;
import com.redcare.pharmacy.popular.respositories.controller.dom.GithubSearchFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the {@link GithubRepositoryService} interface that fetches popular repositories
 * based on the provided filters using the GitHub API.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GithubRepositoryServiceImpl implements GithubRepositoryService {
	public static final String POPULAR_REPOSITORY_CONSTANT = "0";

	private final GithubClient githubClient;

	/**
	 * Fetches a list of popular GitHub repositories based on the provided filter criteria.
	 *
	 * @param githubSearchFilter The filter containing criteria for the GitHub API query.
	 * @return A list of popular repositories represented as {@link GithubRepositoryResponseDto}.
	 */
	@Override
	public List<GithubRepositoryResponseDto> fetchPopularRepositories(final GithubSearchFilter githubSearchFilter) {

		notNull(githubSearchFilter, "filter cannot be null");
		isTrue(githubSearchFilter.getTop() >= 0, "top should be positive");
		GithubClientSearchQueryBuilder builder = new GithubClientSearchQueryBuilder();
		String query = buildQuery(githubSearchFilter, builder);
		log.debug("Query built: {}", query);
		GithubApiResponse response = githubClient.getRepositories(query, STARS.getValue(), DESC.getValue(), githubSearchFilter.getTop());
		log.debug("Received response from GitHub API: {}", response);
		return mapToDtoList(response);

	}

	private static String buildQuery(final GithubSearchFilter githubSearchFilter, final GithubClientSearchQueryBuilder builder) {

		return builder
				.addQueryParam(POPULAR_REPOSITORIES, POPULAR_REPOSITORY_CONSTANT)
				.addQueryParam(CREATED_DATE_AND_AFTER, Objects.nonNull(githubSearchFilter.getCreatedDate()) ? githubSearchFilter.getCreatedDate().toString() : null)
				.addQueryParam(PROGRAMMING_LANGUAGE, Objects.nonNull(githubSearchFilter.getLanguage()) ? githubSearchFilter.getLanguage().getValue() : null)
				.build();

	}

	private List<GithubRepositoryResponseDto> mapToDtoList(final GithubApiResponse response) {

		return response.getRepositories().stream()
				.map(repo -> GithubRepositoryResponseDto.builder()
						.name(repo.getName())
						.link(repo.getHtmlUrl())
						.description(repo.getDescription())
						.stars(repo.getWatchersCount())
						.createdAt(repo.getCreatedAt())
						.language(repo.getLanguage())
						.build())
				.toList();

	}
}
