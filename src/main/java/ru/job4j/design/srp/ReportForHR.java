package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportForHR implements Report {
    private Store store;
    private Comparator comp;

    public ReportForHR(Store store) {
        this.store = store;
    }

    public ReportForHR(Store store, Comparator comp) {
        this.store = store;
        this.comp = comp;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        String ln = System.lineSeparator();
        text.append("Name; Salary").append(ln);
        for (var employee : sortEmployee(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(ln);
        }
        return text.toString();
    }

    private List<Employee> sortEmployee(Predicate<Employee> filter) {
        List<Employee> list = new ArrayList<>(store.findBy(filter));
        if (comp == null) {
            comp = new EmployeeBySalaryDescComparator();
        }
        list.sort(comp);
        return list;
    }
}
