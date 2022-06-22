package ru.job4j.parking.transport;


public class Truck extends Transport {
    private final int size;
    private final String model;
    private final String number;

    public Truck(int size, String model, String number) {
        if (size <= Transport.MIN_SIZE) {
            throw new IllegalArgumentException("Truck size must be more than 1.");
        }
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
