package com.redcare.pharmacy.popular.respositories.service;

import java.util.List;

import com.redcare.pharmacy.popular.respositories.controller.dom.GithubRepositoryResponseDto;
import com.redcare.pharmacy.popular.respositories.controller.dom.GithubSearchFilter;

public interface GithubRepositoryService {

	List<GithubRepositoryResponseDto> fetchPopularRepositories(GithubSearchFilter githubSearchFilter);

}
