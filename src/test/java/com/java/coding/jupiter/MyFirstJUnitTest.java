package com.java.coding.jupiter;

import com.java.coding.utils.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFirstJUnitTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAddition(){
        assertEquals(2, calculator.add(1,1));
    }

    @Test
    void testSubtraction(){
        assertEquals(2, calculator.subtract(6,4));
    }

    @Test
    void testMultiplication(){
        assertEquals(24, calculator.multiply(6,4));
    }

    @Test
    void testDivide(){
        assertEquals(2, calculator.divide(4,2));
    }
}
