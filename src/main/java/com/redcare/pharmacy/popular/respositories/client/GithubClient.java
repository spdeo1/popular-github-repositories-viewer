package com.redcare.pharmacy.popular.respositories.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.redcare.pharmacy.popular.respositories.client.dom.GithubApiResponse;
import com.redcare.pharmacy.popular.respositories.client.dom.SortAttribute;
import com.redcare.pharmacy.popular.respositories.client.dom.SortOrder;
import com.redcare.pharmacy.popular.respositories.config.GithubClientConfig;

@FeignClient(name = "GithubClient", url = "${com.redcare.pharmacy.github.feign-client-base-url}", configuration = {GithubClientConfig.class, GithubClientErrorDecoder.class})
public interface GithubClient {

	@GetMapping("/search/repositories")
	GithubApiResponse getRepositories(
			@RequestParam(name = "q") String query,
			@RequestParam(name = "sort") String sortBy,
			@RequestParam(name = "order") String order,
			@RequestParam(name = "per_page") Integer perPage
	);

}
