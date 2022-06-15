package ru.job4j.design.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReportForITTest {

    @Test
    public void whenITGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Pavel", now, now, 200);
        store.add(worker);
        ReportForIT report = new ReportForIT(store);
        var formatter = new SimpleDateFormat("yyyy-MM-dd");
        String ln = System.lineSeparator();
        StringBuilder expected = new StringBuilder()
                .append("<!doctype html>").append(ln)
                .append("<html>").append(ln)
                .append("<head>").append(ln)
                .append("<head>").append(ln)
                .append("<body>").append(ln).append("\t")
                .append("<employees>").append(ln).append("\t\t")
                .append("<employee>").append(ln).append("\t\t\t")
                .append(String.format("<name>%s<name>", worker.getName())).append(ln).append("\t\t\t")
                .append(String.format("<hired>%s<hired>", formatter.format(worker.getHired().getTime())))
                .append(ln).append("\t\t\t")
                .append(String.format("<fired>%s<fired>", formatter.format(worker.getFired().getTime())))
                .append(ln).append("\t\t\t")
                .append(String.format("<salary>%.1f<salary>", worker.getSalary())).append(ln).append("\t\t")
                .append("<employee>").append(ln).append("\t")
                .append(ln)
                .append("<employees>").append(ln)
                .append("<body>").append(ln)
                .append("<html>").append(ln)
                .append(ln);
        assertThat(report.generate(em -> true), is(expected.toString()));
    }
}