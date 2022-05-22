package ru.job4j.grabber.utils;

import java.time.LocalDateTime;

public class HabrCareerDateTimeParser implements DateTimeParser {
    @Override
    public LocalDateTime parse(String string) {
        return LocalDateTime.parse(string.split("[+]", 2)[0]);
    }
}
