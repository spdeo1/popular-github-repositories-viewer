package com.redcare.pharmacy.popular.respositories.controller.dom;

public enum Language {
	JAVASCRIPT("JavaScript"),
	CSS("CSS"),
	GO("Go"),
	JAVA("Java");

	private final String value;

	Language(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}