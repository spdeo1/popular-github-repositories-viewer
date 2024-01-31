package com.redcare.pharmacy.popular.respositories.client;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.redcare.pharmacy.popular.respositories.client.dom.GithubQueryQualifier;

public class GithubClientSearchQueryBuilder {

	private final Map<GithubQueryQualifier, String> queryParams;

	public GithubClientSearchQueryBuilder() {
		this.queryParams = new LinkedHashMap<>();
	}

	public GithubClientSearchQueryBuilder addQueryParam(GithubQueryQualifier key, String value) {
		if (Objects.nonNull(key) && Objects.nonNull(value) && !value.isEmpty()) {
			queryParams.put(key, value);
		}
		return this;
	}

	public String build() {

		if (!queryParams.isEmpty()) {
			return queryParams.entrySet().stream()
					.map(entry -> entry.getKey().getValue() + entry.getValue())
					.collect(Collectors.joining(" "));
		}
		return "";
	}

}
