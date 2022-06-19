package ru.job4j.parking;

import ru.job4j.parking.autotransport.Transport;

public interface Parking {
    void park(Transport transport);
    void remove(Transport transport);
}
