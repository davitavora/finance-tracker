package com.github.davitavora.jooq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ResourceFoundException extends ErrorResponseException {

    public ResourceFoundException(String resource, Number identifier) {
        super(HttpStatus.NOT_FOUND, asProblemDetail(resource, identifier), null);
    }

    private static ProblemDetail asProblemDetail(String resource, Number identifier) {
        final var message = "Unable to find %s %s".formatted(resource, identifier);
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
    }

}
