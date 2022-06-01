package ru.job4j.gc;

public class User {

    private String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }


    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s with id %d was deleted%n", name, id);
    }
}
