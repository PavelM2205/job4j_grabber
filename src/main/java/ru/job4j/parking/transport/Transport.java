package ru.job4j.parking.transport;

public abstract class Transport {
    private int size;
    private String model;
    private String number;

    public int getSize() {
        return size;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }
}
