package com.github.davitavora.jooq.exception;

public class ResourceFoundException extends RuntimeException {

    public ResourceFoundException(String resource, Number identifier) {
        super("Unable to find %s %s".formatted(resource, identifier));
    }

}
