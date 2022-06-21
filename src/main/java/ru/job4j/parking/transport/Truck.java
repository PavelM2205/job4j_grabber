package ru.job4j.parking.transport;

import ru.job4j.parking.transportstore.TruckStore;

public class Truck extends Transport {
    private final int size;
    private final String model;
    private final String number;

    public Truck(int size, String model, String number) {
        this.size = size;
        this.model = model;
        this.number = number;
    }

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
