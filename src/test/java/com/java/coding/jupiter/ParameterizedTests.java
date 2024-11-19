package com.java.coding.jupiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParameterizedTests {

    @ParameterizedTest
    @MethodSource("stringProvider")
    @DisplayName("String Test Using Method Source")
    void testLocalMethodSource(String argument) {
        assertNotNull(argument);
    }

    @ParameterizedTest
    @MethodSource("range")
    @DisplayName("Range Test Using Method Source")
    void testRangeWithMethodSource(int argument) {
        assertNotEquals(9, argument);
    }

    @ParameterizedTest
    @MethodSource("stringIntListProvider")
    @DisplayName("String/Int/List Test Using Method Source")
    void testStringIntListProvider(String str, int i, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(i > 0 && i <= 2);
        assertEquals(2, list.size());
    }

    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testEnumSource(TemporalUnit temporal){
        assertNotNull(temporal);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana", "orange", "kiwi");
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    static Stream<Arguments> stringIntListProvider() {
        return Stream.of(
                Arguments.arguments("apple", 1, Arrays.asList("x", "y")),
                Arguments.arguments("lemon", 2, Arrays.asList("z", "v"))
        );
    }
}
