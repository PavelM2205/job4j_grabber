package ru.job4j.grabber.utils;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class HabrCareerDateTimeParserTest {

    @Test
    public void whenParseThenDateWithoutTimeZone() {
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        String date = "2022-05-21T13:56:19+03:00";
        LocalDateTime result = parser.parse(date);
        LocalDateTime expected = LocalDateTime.of(2022, 05,
                21, 13, 56, 19);
        assertEquals(expected, result);
    }
}