package ru.job4j.parking.transportstore;

import ru.job4j.parking.transport.Transport;

import java.util.List;
import java.util.function.Predicate;

public class CarStore implements Store {
    private final int size;

    public CarStore(int size) {
        this.size = size;
    }

    @Override
    public void add(Transport transport) {

    }

    @Override
    public void delete(Transport transport) {

    }

    @Override
    public List<Transport> findBy(Predicate<Transport> filter) {
        return null;
    }
}
