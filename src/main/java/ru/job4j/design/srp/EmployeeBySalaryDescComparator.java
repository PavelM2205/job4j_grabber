package ru.job4j.design.srp;

import java.util.Comparator;

public class EmployeeBySalaryDescComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return (int) (o2.getSalary() - o1.getSalary());
    }
}
