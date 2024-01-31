package com.redcare.pharmacy.popular.respositories.client.dom;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class GithubRepository {

	@JsonProperty("id")
	private long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("full_name")
	private String fullName;
	@JsonProperty("owner")
	private Owner owner;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
	@JsonProperty("language")
	private String language;
	@JsonProperty("watchers_count")
	private long watchersCount;
	@JsonProperty("html_url")
	private String htmlUrl;
	@JsonProperty("description")
	private String description;

}
