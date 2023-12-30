package com.github.davitavora.jooq.exception;

public class CategoryNotFoundException extends ResourceFoundException {

    public CategoryNotFoundException(Integer id) {
        super("category", id);
    }

}
