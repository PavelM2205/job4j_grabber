package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForAccounting implements Report {
    private Store store;

    public ReportForAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary").append(System.lineSeparator());
        for (var employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateFormatting(employee.getHired())).append(";")
                    .append(dateFormatting(employee.getFired())).append(";")
                    .append(changeSalary(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private double changeSalary(double salary) {
        return salary * 1.2;
    }
}
