package com.redcare.pharmacy.popular.respositories.client.dom;

public enum SortOrder {
	ASC("asc"), DESC("desc");

	private final String value;

	SortOrder(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}