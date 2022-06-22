package ru.job4j.parking.transport;

public abstract class Transport {
    public static final int MIN_SIZE = 1;

    public abstract int getSize();

    public abstract String getModel();

    public abstract String getNumber();
}
