package ru.job4j.design.srp.lsp;

public class SumOfNumbers {

    public int sum(int a, int b) {
        if (!(a >= 0 && b >= 0)) {
            throw new IllegalArgumentException();
        }
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(new SumOfThreeNumbers().sum(-1, 3));
    }

    public static class SumOfThreeNumbers extends SumOfNumbers {

        @Override
        public int sum(int a, int b) {
            return a + b;
        }

        public int sumOfThree(int a, int b, int c) {
            return a + b + c;
        }
    }
}
