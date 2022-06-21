package ru.job4j.parking;

import ru.job4j.parking.transport.Transport;
import ru.job4j.parking.transportstore.Store;

import java.util.List;

public class StreetParking implements Parking {
    private final List<Store> stores;

    public StreetParking(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public boolean park(Transport transport) {
        return false;
    }

    @Override
    public boolean remove(Transport transport) {
        return false;
    }
}
