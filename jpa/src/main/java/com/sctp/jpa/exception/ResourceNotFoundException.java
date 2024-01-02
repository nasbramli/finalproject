package com.sctp.jpa.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String id) {
        super("Could not find product: " + id);
    }
}
