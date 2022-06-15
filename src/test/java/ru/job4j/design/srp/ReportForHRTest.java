package ru.job4j.design.srp;

import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReportForHRTest {

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Fedor", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        ReportForHR report = new ReportForHR(store);
        String ln = System.lineSeparator();
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary").append(ln)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(ln)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(ln);
        assertThat(report.generate(emp -> true), is(expected.toString()));
    }

}