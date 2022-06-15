package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForIT implements Report {
    private Store store;

    public ReportForIT(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        String ln = System.lineSeparator();
        text.append("<!doctype html>").append(ln)
                .append("<html>").append(ln)
                .append("<head>").append(ln)
                .append("<head>").append(ln)
                .append("<body>").append(ln).append("\t")
                .append("<employees>").append(ln).append("\t\t");
        for (var employee : store.findBy(filter)) {
            text.append("<employee>").append(ln).append("\t\t\t")
                    .append(String.format("<name>%s<name>", employee.getName())).append(ln)
                    .append("\t\t\t")
                    .append(String.format("<hired>%s<hired>", dateFormatting(employee.getHired())))
                    .append(ln).append("\t\t\t")
                    .append(String.format("<fired>%s<fired>", dateFormatting(employee.getFired())))
                    .append(ln).append("\t\t\t")
                    .append(String.format("<salary>%.1f<salary>", employee.getSalary())).append(ln)
                    .append("\t\t")
                    .append("<employee>").append(ln).append("\t")
                    .append(ln);
        }
        text.append("<employees>").append(ln)
                .append("<body>").append(ln)
                .append("<html>").append(ln)
                .append(ln);
        return text.toString();
    }
}
