package ru.job4j.design.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReportToXMLTest {

    @Test
    public void whenGeneratesTwoEmployeesToXML() {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        Employee em1 = new Employee("Ivan", date, date, 500);
        Employee em2 = new Employee("Petr", date, date, 400);
        store.add(em1);
        store.add(em2);
        ReportToXML report = new ReportToXML(store);
        String ln = System.lineSeparator();
        var formatter = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(ln)
                .append("<employees>")
                .append(ln)
                .append("<employee>")
                .append(ln)
                .append(String.format("<name>%s</name>", em1.getName()))
                .append(ln)
                .append(String.format("<hired>%s</hired>", formatter.format(em1.getHired().getTime())))
                .append(ln)
                .append(String.format("<fired>%s</fired>", formatter.format(em1.getFired().getTime())))
                .append(ln)
                .append(String.format(Locale.ENGLISH, "<salary>%.1f</salary>", em1.getSalary()))
                .append(ln)
                .append("</employee>")
                .append(ln)
                .append("<employee>")
                .append(ln)
                .append(String.format("<name>%s</name>", em2.getName()))
                .append(ln)
                .append(String.format("<hired>%s</hired>", formatter.format(em2.getHired().getTime())))
                .append(ln)
                .append(String.format("<fired>%s</fired>", formatter.format(em2.getFired().getTime())))
                .append(ln)
                .append(String.format(Locale.ENGLISH, "<salary>%.1f</salary>", em2.getSalary()))
                .append(ln)
                .append("</employee>")
                .append(ln)
                .append("</employees>")
                .append(ln);
        assertThat(report.generate(em -> true), equalToIgnoringWhiteSpace(expected.toString()));
    }
}