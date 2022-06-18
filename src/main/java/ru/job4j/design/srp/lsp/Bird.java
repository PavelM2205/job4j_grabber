package ru.job4j.design.srp.lsp;

import java.util.List;

public class Bird {

    public void eat() {
        System.out.println("Bird eats");
    }

    public void fly() {
        System.out.println("Bird flies");
    }

    public static void main(String[] args) {
        List<Bird> list = List.of(new Bird(), new Penguin());
        for (var el : list) {
            el.fly();
        }
    }

    public static class Penguin extends Bird {

        @Override
        public void eat() {
            System.out.println("Penguin eat");
        }

        @Override
        public void fly() {
            throw new UnsupportedOperationException("Penguin can not fly");
        }
    }
}
