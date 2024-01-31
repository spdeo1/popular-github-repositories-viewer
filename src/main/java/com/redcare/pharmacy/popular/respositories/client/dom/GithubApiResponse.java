package com.redcare.pharmacy.popular.respositories.client.dom;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubApiResponse {

	@JsonProperty("total_count")
	private long totalCount;

	@JsonProperty("incomplete_results")
	private boolean incompleteResults;

	@JsonProperty("items")
	private List<GithubRepository> repositories;

}
