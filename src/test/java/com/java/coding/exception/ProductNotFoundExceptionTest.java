package com.java.coding.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ProductNotFoundExceptionTest {

    @Test
    void testExceptionMessage(){
        String productId = "123";
        ProductNotFoundException exception = new ProductNotFoundException(productId);
        assertEquals("No product found with ID: 123", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testExceptionWithThrowable(){
        String productId = "123";
        Throwable cause = new IllegalArgumentException("Invalid ID format");
        ProductNotFoundException exception = new ProductNotFoundException(productId, cause);
        assertEquals("No product found with ID: 123",exception.getMessage());
        assertEquals(cause,exception.getCause());
    }
}
