package com.java.coding.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

;

@SpringBootTest
public class LocalDateTimeTest {

    private LocalDate localDate = LocalDate.parse("2024-10-30");
    private LocalDateTime localDateTime = LocalDateTime.parse("2014-03-13T22:55:30.11");
    private DateTimeFormatter fullFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    private DateTimeFormatter fullDateTimeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);

    @Test
    void testLocalDate(){
        assertThat(localDate.format(fullFormat), is("Wednesday, October 30, 2024"));
    }

    @Test
    void testLocalDateTime(){
        //Thursday, March 13, 2014 2:23:33 PM EDT
        //DAY, MONTH DATE
        LocalTime localTime = LocalTime.parse("22:55:30");
        LocalDateTime dateTime = localDate.atTime(localTime);

        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of("America/Chicago"));

        assertThat(zonedDateTime.format(fullDateTimeFormat), is("Wednesday, October 30, 2024, 10:55:30 PM Central Daylight Time"));
    }

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
    }

}