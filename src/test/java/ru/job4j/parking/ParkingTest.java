package ru.job4j.parking;

import org.junit.Test;
import ru.job4j.parking.transport.Car;
import ru.job4j.parking.transport.Transport;
import ru.job4j.parking.transport.Truck;
import ru.job4j.parking.transportstore.CarStore;
import ru.job4j.parking.transportstore.Store;
import ru.job4j.parking.transportstore.TruckStore;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingTest {

    @Test
    public void whenParkingOneCarThenOneMustBeIntoCarStore() {
        Store carStore = new CarStore(1);
        Store truckStore = new TruckStore(1);
        Transport car = new Car("Toyota", "К456МП");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        assertTrue(parking.park(car));
        assertThat(carStore.findBy(cr -> true), is(List.of(car)));
        assertThat(carStore.findBy(cr -> true).size(), is(1));
        assertThat(truckStore.findBy(tr -> true).size(), is(0));
    }

    @Test
    public void whenParkingOneTruckThenOneMustBeIntoTruckStore() {
        Store carStore = new CarStore(1);
        Store truckStore = new TruckStore(1);
        Transport truck = new Truck(2, "DAF", "M001MM");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        assertTrue(parking.park(truck));
        assertThat(truckStore.findBy(tr -> true), is(List.of(truck)));
        assertThat(truckStore.findBy(tr -> true).size(), is(1));
        assertThat(carStore.findBy(cr -> true).size(), is(0));
    }

    @Test
    public void whenParkingTwoTrucksThenFirstMustBeIntoCarStoreAndSecondMustBeIntoTruckStore() {
        Store carStore = new CarStore(2);
        Store truckStore = new TruckStore(1);
        Transport truck1 = new Truck(2, "DAF", "M001MM");
        Transport truck2 = new Truck(2, "MAN", "Р002РР");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        assertTrue(parking.park(truck1));
        assertTrue(parking.park(truck2));
        assertThat(carStore.findBy(tr -> true), is(List.of(truck1)));
        assertThat(truckStore.findBy(tr -> true), is(List.of(truck2)));
    }

    @Test
    public void whenParkingTwoTrucksAndOneCarThenFirstTruckIsIntoTruckStoreAndSecondAndCarAreIntoCarStore() {
        Store carStore = new CarStore(3);
        Store truckStore = new TruckStore(1);
        Transport truck1 = new Truck(2, "DAF", "M001MM");
        Transport truck2 = new Truck(2, "MAN", "Р002РР");
        Transport car = new Car("Toyota", "К456МП");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        assertTrue(parking.park(truck1));
        assertTrue(parking.park(truck2));
        assertTrue(parking.park(car));
        assertThat(carStore.findBy(cr -> true), is(List.of(truck1, car)));
        assertThat(truckStore.findBy(tr -> true), is(List.of(truck2)));
        assertThat(truckStore.findBy(tr -> true).size(), is(1));
        assertThat(carStore.findBy(cr -> true).size(), is(2));
    }

    @Test
    public void whenThereAreNotFreePlacesThenFalse() {
        Store carStore = new CarStore(1);
        Store truckStore = new TruckStore(1);
        Transport truck1 = new Truck(2, "DAF", "M001MM");
        Transport truck2 = new Truck(2, "MAN", "Р002РР");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        assertTrue(parking.park(truck1));
        assertFalse(parking.park(truck2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSizeOfTruckLessThan2ThenMustBeException() {
        Transport truck = new Truck(1, "DAF", "В005ВВ");
    }
}