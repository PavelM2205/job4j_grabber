package ru.job4j.parking;

import ru.job4j.parking.transport.Transport;

public interface Parking {
    boolean park(Transport transport);
    boolean remove(Transport transport);
}
