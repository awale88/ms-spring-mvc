package com.java.coding.jupiter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CsvFileSourceTest{

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1)
    void testFileSourceUsingClassPath(String country, int reference){
        assertNotNull(country);
        assertNotEquals(0,reference);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/two-column.csv", numLinesToSkip = 1)
    void testFileSourceUsingFile(String country, int reference){
        assertNotNull(country);
        assertNotEquals(0,reference);
    }

    @ParameterizedTest(name = "[{index}], {arguments}")
    @CsvFileSource(files = "src/test/resources/two-column.csv", useHeadersInDisplayName = true)
    void testFileSourceAndHeader(String country, int reference){
        assertNotNull(country);
        assertNotEquals(0,reference);
    }
}
