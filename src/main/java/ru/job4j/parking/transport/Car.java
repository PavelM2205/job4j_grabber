package ru.job4j.parking.transport;

public class Car extends Transport {
    private final String model;
    private final String number;

    public Car(String model, String number) {
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
        return Transport.MIN_SIZE;
    }
}
