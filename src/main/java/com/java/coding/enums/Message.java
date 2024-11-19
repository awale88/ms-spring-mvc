package com.java.coding.enums;

public enum Message {

    PRODUCT_FOUND("Product found"),
    PRODUCT_NOT_FOUND("No product found with ID: %s"),
    PRODUCT_CREATED("Product created successfully"),
    PRODUCT_UPDATED("Product updated successfully"),
    PRODUCT_DELETED("Product deleted successfully"),
    PRODUCTS_RETRIEVED("Products retrieved successfully");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }

}
