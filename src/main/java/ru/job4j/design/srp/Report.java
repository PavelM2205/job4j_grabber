package ru.job4j.design.srp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);

    default String dateFormatting(Calendar calendar) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }
}
