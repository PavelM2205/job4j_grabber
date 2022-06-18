package ru.job4j.design.srp.lsp;

public class Division {

    public int numeratorCount(int a, int b) {
        return a * b;
    }

    public int denominatorCount(int c, int d) {
        int x = c * d;
        if (x == 0) {
            throw new IllegalArgumentException();
        }
        System.out.println("x равен: " + x);
        return x;
    }

    public int division(int a, int b, int c, int d) {
        return numeratorCount(a, b) / denominatorCount(c, d);
    }

    public static void main(String[] args) {
        ExtraDivision div = new ExtraDivision();
        System.out.println(div.division(1, 2, 2, 2));
    }

    public static class ExtraDivision extends Division {
        @Override
        public int denominatorCount(int c, int d) {
            int x = c - d;
            return x;
        }
    }
}
