package ru.job4j.design.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReportToJSONTest {

    @Test
    public void whenGeneratesTwoEmployeeToJSON() {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        Employee em1 = new Employee("Ivan", date, date, 350);
        Employee em2 = new Employee("Egor", date, date, 550);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        store.add(em1);
        store.add(em2);
        ReportToJSON report = new ReportToJSON(store);
        StringBuilder expected = new StringBuilder()
                .append("[")
                .append("{")
                .append(String.format("\"name\":\"%s\"", em1.getName()))
                .append(",")
                .append(String.format("\"hired\":\"%s\"", formatter.format(em1.getFired().getTime())))
                .append(",")
                .append(String.format("\"fired\":\"%s\"", formatter.format(em1.getFired().getTime())))
                .append(",")
                .append(String.format(Locale.ENGLISH, "\"salary\":%.1f", em1.getSalary()))
                .append("}")
                .append(",")
                .append("{")
                .append(String.format("\"name\":\"%s\"", em2.getName()))
                .append(",")
                .append(String.format("\"hired\":\"%s\"", formatter.format(em2.getFired().getTime())))
                .append(",")
                .append(String.format("\"fired\":\"%s\"", formatter.format(em2.getFired().getTime())))
                .append(",")
                .append(String.format(Locale.ENGLISH, "\"salary\":%.1f", em2.getSalary()))
                .append("}")
                .append("]");
        assertThat(report.generate(em -> true), is(expected.toString()));
    }
}