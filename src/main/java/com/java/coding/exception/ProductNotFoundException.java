package com.java.coding.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String id) {
        super(String.format("No product found with ID: %s", id));
    }

    public ProductNotFoundException(String id, Throwable cause) {
        super(String.format("No product found with ID: %s" , id), cause);
    }
}
