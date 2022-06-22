package ru.job4j.parking.transportstore;

import ru.job4j.parking.transport.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CarStore implements Store {
    private int size;
    private final List<Transport> carStore = new ArrayList<>(size);

    public CarStore(int size) {
        this.size = size;
    }

    @Override
    public boolean add(Transport transport) {
        boolean result = false;
        if (size >= transport.getSize()) {
            carStore.add(transport);
            size -= transport.getSize();
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Transport transport) {
        boolean result = false;
        if (carStore.remove(transport)) {
            size += transport.getSize();
            result = true;
        }
        return result;
    }

    @Override
    public List<Transport> findBy(Predicate<Transport> filter) {
        return carStore.stream().filter(filter).toList();
    }
}
