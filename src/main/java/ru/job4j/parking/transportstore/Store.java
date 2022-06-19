package ru.job4j.parking.transportstore;

import ru.job4j.parking.autotransport.Transport;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    void add(Transport transport);
    void delete(Transport transport);
    List<Transport> findBy(Predicate<Transport> filter);
}
