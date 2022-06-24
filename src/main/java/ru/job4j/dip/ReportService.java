package ru.job4j.dip;

public class ReportService {
    private Printer printer = new Printer();

    public String makeReport() {
        return "";
    }

    public boolean saveReport() {
        return false;
    }

    public void printReport() {
    }


    public class Printer {

        public void print(String string) {
            System.out.println();
        }
    }
}
