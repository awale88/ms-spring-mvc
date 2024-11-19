package com.java.coding.util;

import com.java.coding.utils.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StringUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"mom", "dad", "nayan", "racecar", "radar"})
    void testPalindromes(String candidates) {
        assertTrue(StringUtils.isPalindrome(candidates));
    }
}
