package com.java.coding.jupiter;

import com.java.coding.utils.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class HamcrestAssertionDemo {

    private final Calculator calculator = new Calculator();


    @Test
    void testHamcrestAssertion(){
        assertThat(calculator.subtract(2,1), is(equalTo(1)));
    }

    @Test
    void testLastCharacter(){
        StringBuilder sb = new StringBuilder();
        sb.append("nayan");
        assertThat(sb.toString(), endsWithIgnoringCase("n"));
    }

    @Test
    void testArrayWithContains() {
        List<String> list = Arrays.asList("ram", "shayam", "hari");
        assertThat(list, hasItem("ram"));
        assertThat(list, not(hasItem("nayan")));
        assertThat(list, is(not(empty())));
    }

}
