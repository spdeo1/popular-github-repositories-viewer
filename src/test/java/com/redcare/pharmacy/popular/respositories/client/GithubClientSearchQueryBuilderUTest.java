package com.redcare.pharmacy.popular.respositories.client;

import static com.redcare.pharmacy.popular.respositories.service.GithubRepositoryServiceImpl.POPULAR_REPOSITORY_CONSTANT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.redcare.pharmacy.popular.respositories.client.dom.GithubQueryQualifier;

class GithubClientSearchQueryBuilderUTest {

	@Test
	void testBuild_withQueryParams_shouldReturnCorrectQueryString() {
		GithubClientSearchQueryBuilder builder = new GithubClientSearchQueryBuilder();
		LocalDate givenDate = LocalDate.parse("2024-01-31");

		builder.addQueryParam(GithubQueryQualifier.POPULAR_REPOSITORIES, POPULAR_REPOSITORY_CONSTANT)
				.addQueryParam(GithubQueryQualifier.PROGRAMMING_LANGUAGE, "Java")
				.addQueryParam(GithubQueryQualifier.CREATED_DATE_AND_AFTER, givenDate.toString());

		String result = builder.build();

		assertThat(result).isEqualTo("stars:>0 language:Java created:>=2024-01-31");
	}

	@Test
	void testBuild_withEmptyQueryParams_shouldReturnEmptyString() {
		GithubClientSearchQueryBuilder builder = new GithubClientSearchQueryBuilder();

		String result = builder.build();

		assertThat(result).isEmpty();
	}

	@Test
	void testBuild_withNullQueryParams_shouldReturnEmptyString() {
		GithubClientSearchQueryBuilder builder = new GithubClientSearchQueryBuilder();

		builder.addQueryParam(GithubQueryQualifier.POPULAR_REPOSITORIES, null)
				.addQueryParam(GithubQueryQualifier.PROGRAMMING_LANGUAGE, null)
				.addQueryParam(GithubQueryQualifier.CREATED_DATE_AND_AFTER, null);

		String result = builder.build();

		assertThat(result).isEmpty();
	}

	@Test
	void testBuild_withEmptyValues_shouldSkipEmptyValues() {
		GithubClientSearchQueryBuilder builder = new GithubClientSearchQueryBuilder();

		builder.addQueryParam(GithubQueryQualifier.POPULAR_REPOSITORIES, POPULAR_REPOSITORY_CONSTANT)
				.addQueryParam(GithubQueryQualifier.PROGRAMMING_LANGUAGE, "")
				.addQueryParam(GithubQueryQualifier.CREATED_DATE_AND_AFTER, "");

		String result = builder.build();

		assertThat(result).isEqualTo("stars:>0");
	}

	@Test
	void testBuild_withNullKeys_shouldReturnEmptyString() {
		GithubClientSearchQueryBuilder builder = new GithubClientSearchQueryBuilder();

		builder.addQueryParam(null, POPULAR_REPOSITORY_CONSTANT)
				.addQueryParam(null, "some value")
				.addQueryParam(null, "");

		String result = builder.build();

		assertThat(result).isEmpty();
	}

	@Test
	void testBuild_withNullKey_shouldSkipNullKey() {
		GithubClientSearchQueryBuilder builder = new GithubClientSearchQueryBuilder();

		builder.addQueryParam(GithubQueryQualifier.POPULAR_REPOSITORIES, POPULAR_REPOSITORY_CONSTANT)
				.addQueryParam(GithubQueryQualifier.PROGRAMMING_LANGUAGE, "Java")
				.addQueryParam(null, "some value" );

		String result = builder.build();

		assertThat(result).isEqualTo("stars:>0 language:Java");
	}

}