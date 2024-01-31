package com.redcare.pharmacy.popular.respositories.client.dom;

public enum GithubQueryQualifier {

	POPULAR_REPOSITORIES("stars:>"),
	CREATED_DATE_AND_AFTER("created:>="),
	PROGRAMMING_LANGUAGE("language:");

	private final String value;

	GithubQueryQualifier(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
