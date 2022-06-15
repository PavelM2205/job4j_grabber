package ru.job4j.design.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReportForAccountingTest {

    @Test
    public void whenAccountingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 200);
        store.add(worker);
        ReportForAccounting report = new ReportForAccounting(store);
        var formatter = new SimpleDateFormat("yyyy-MM-dd");
        double changeSalary = worker.getSalary() * report.BONUS;
        StringBuilder text = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(formatter.format(worker.getHired().getTime())).append(";")
                .append(formatter.format(worker.getFired().getTime())).append(";")
                .append(changeSalary).append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(emp -> true), is(text.toString()));
    }

}