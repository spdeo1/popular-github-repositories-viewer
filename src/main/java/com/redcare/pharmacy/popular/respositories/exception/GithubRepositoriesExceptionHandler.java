package com.redcare.pharmacy.popular.respositories.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.redcare.pharmacy.popular.respositories.exception.dom.ErrorResponse;
import com.redcare.pharmacy.popular.respositories.exception.dom.GithubClientRemoteException;

@RestControllerAdvice
public class GithubRepositoriesExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {

		return new ResponseEntity<>(ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.code(HttpStatus.BAD_REQUEST.value())
				.message(exception.getMessage())
				.timestamp(LocalDateTime.now())
				.details(request.getDescription(false)).build(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler({GithubClientRemoteException.class})
	public ResponseEntity<ErrorResponse> handleGithubClientRemoteException(GithubClientRemoteException exception, WebRequest request) {

		return new ResponseEntity<>(ErrorResponse.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(exception.getMessage())
				.timestamp(LocalDateTime.now())
				.details(request.getDescription(false)).build(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
