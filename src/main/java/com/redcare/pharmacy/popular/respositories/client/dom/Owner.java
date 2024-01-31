package com.redcare.pharmacy.popular.respositories.client.dom;

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
public class Owner {
	@JsonProperty("login")
	private String login;

	@JsonProperty("id")
	private long id;

	@JsonProperty("avatar_url")
	private String avatarUrl;

}
