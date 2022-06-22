package ru.job4j.parking.transportstore;

import ru.job4j.parking.transport.Transport;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    boolean add(Transport transport);
    boolean delete(Transport transport);
    List<Transport> findBy(Predicate<Transport> filter);
}
