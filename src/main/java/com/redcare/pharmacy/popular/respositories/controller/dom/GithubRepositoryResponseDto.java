package com.redcare.pharmacy.popular.respositories.controller.dom;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GithubRepositoryResponseDto {

	private String description;
	private String link;
	private long stars;
	private String language;
	private LocalDateTime createdAt;
	private String name;

}
