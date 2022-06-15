package ru.job4j.design.srp.ocp;

import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private Map<String, User> map = new HashMap<>();
    private Out out;

    public UserStore() {
    }

    public void printUser(User user) {
        out = new Out();
        out.print(user);
    }

    private static class Out {
        public void print(User user) {
            System.out.println(user.getName());
        }
    }

    private static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
