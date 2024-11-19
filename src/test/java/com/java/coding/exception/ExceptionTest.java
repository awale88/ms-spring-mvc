package com.java.coding.exception;

import com.java.coding.utils.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ExceptionTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAssertionException(){
        Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(1,0));
        assertEquals("/ by zero", exception.getMessage());
    }
}
