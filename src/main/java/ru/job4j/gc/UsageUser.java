package ru.job4j.gc;

public class UsageUser {
    public static void main(String[] args) {
        for (int i = 1; i <= 100000; i++) {
            new User("User" + i, i);
        }
    }
}
