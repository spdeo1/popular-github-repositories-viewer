package com.redcare.pharmacy.popular.respositories.controller.dom;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubSearchFilter {

	@Schema(description = "An option to be able to view the top 10, 50, 100 repositories", implementation = Integer.class)
	@Max(100)
	private int top = 10;
	@Schema(description = "A filter for the programming language", implementation = Language.class)
	private Language language;
	@Schema(description = "Given a date in yyyy-MM-dd, the most popular repositories created from this date onwards should be returned")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;

}
