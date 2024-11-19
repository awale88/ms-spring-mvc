package com.java.coding.jupiter;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.JRE.JAVA_21;
import static org.junit.jupiter.api.condition.JRE.JAVA_8;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CollectionTest {

    @Test
    @Order(5)
    public void testStringBuilder() {
        StringBuilder sb1 = new StringBuilder("Nayan");
        StringBuilder sb2 = new StringBuilder("Awale");

        assertEquals("Nayan", sb1.toString());
        assertNotEquals(sb2, sb1);
        assertNotNull(sb1);
    }

    @Test
    @DisplayName("Basic Sum Test")
    @Order(4)
    public void testSum() {
        int a = 10;
        int b = 20;
        int sum = a + b;
        String z = null;

        assertEquals(30, sum);
        assertFalse(sum > 40);
        assertNull(z);
        assertTrue(sum > 10);
    }

    @Test
    @DisplayName("Boolean Test")
    @Order(2)
    public void testBoolean() {
        boolean a = true;
        boolean b = false;

        assertTrue(a);
        assertFalse(b);
    }

    @Test
    @DisplayName("Array Test")
    @Order(3)
    @DisabledOnOs(OS.WINDOWS)
    public void testArray() {
        int[] numbers = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, numbers);
    }

    @Test
    @DisplayName("String Test")
    @EnabledOnOs({OS.WINDOWS, OS.LINUX, OS.MAC})
    @EnabledForJreRange(min = JAVA_8, max = JAVA_21)
    @Order(1)
    void testString() {
        String str = "test";
        String name = "nayan";
        String empty = StringUtils.EMPTY;
        assertEquals("test", str);
        assertEquals(StringUtils.EMPTY, empty);
        assertNotEquals("nayan", str);
        assertNotNull(name);
        assertNotEquals(name, empty);
    }

    @ParameterizedTest
    @DisplayName("Parameterized Test")
    @Order(6)
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testValueSource(int value) {
        assertTrue(value >= 0 && value <= 6);
    }


}
