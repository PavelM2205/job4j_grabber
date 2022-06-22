package ru.job4j.parking.transportstore;

import ru.job4j.parking.transport.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TruckStore implements Store {
    private int size;
    private final List<Transport> truckStore;

    public TruckStore(int size) {
        this.size = size;
        this.truckStore = new ArrayList<>(size);
    }

    @Override
    public boolean add(Transport transport) {
        boolean result = false;
        if (transport.getSize() > Transport.MIN_SIZE && size >= Transport.MIN_SIZE) {
            truckStore.add(transport);
            size--;
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Transport transport) {
        boolean result = false;
        if (truckStore.remove(transport)) {
            size++;
            result = true;
        }
        return result;
    }

    @Override
    public List<Transport> findBy(Predicate<Transport> filter) {
        return truckStore.stream().filter(filter).toList();
    }
}
