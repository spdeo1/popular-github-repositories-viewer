package com.redcare.pharmacy.popular.respositories.client.dom;

public enum SortAttribute {
	STARS("stars");

	private final String value;

	SortAttribute(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	}
