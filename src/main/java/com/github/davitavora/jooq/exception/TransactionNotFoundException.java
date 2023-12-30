package com.github.davitavora.jooq.exception;

public class TransactionNotFoundException extends ResourceFoundException {

    public TransactionNotFoundException(Long id) {
        super("transaction", id);
    }

}
