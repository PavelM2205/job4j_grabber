package ru.job4j.parking.transport;

public class Car extends Transport {
    private final int size;
    private final String model;
    private final String number;

    public Car(int size, String model, String number) {
        this.size = size;
        this.model = model;
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }
}
