package ru.job4j.parking;

import org.junit.Ignore;
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

public class ParkingTest {

    @Ignore
    @Test
    public void whenParkingOneCarThenOneMustBeIntoCarStore() {
        Store carStore = new CarStore(1);
        Store truckStore = new TruckStore(1);
        Transport car = new Car(1, "Toyota", "К456МП");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        parking.park(car);
        assertThat(carStore.findBy(cr -> true).get(0).getNumber(), is("К456МП"));
        assertThat(carStore.findBy(cr -> true).size(), is(1));
        assertThat(truckStore.findBy(tr -> true).size(), is(0));
    }

    @Ignore
    @Test
    public void whenParkingOneTruckThenOneMustBeIntoTruckStore() {
        Store carStore = new CarStore(1);
        Store truckStore = new TruckStore(1);
        Transport truck = new Truck(2, "DAF", "M001MM");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        parking.park(truck);
        assertThat(truckStore.findBy(tr -> true).get(0).getNumber(), is("M001MM"));
        assertThat(truckStore.findBy(tr -> true).size(), is(1));
        assertThat(carStore.findBy(cr -> true).size(), is(0));
    }

    @Ignore
    @Test
    public void whenParkingTwoTrucksThenFirstMustBeIntoCarStoreAndSecondMustBeIntoTruckStore() {
        Store carStore = new CarStore(2);
        Store truckStore = new TruckStore(1);
        Transport truck1 = new Truck(2, "DAF", "M001MM");
        Transport truck2 = new Truck(2, "MAN", "Р002РР");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        parking.park(truck1);
        parking.park(truck2);
        assertThat(truckStore.findBy(tr -> true).get(0).getNumber(), is("M001MM"));
        assertThat(carStore.findBy(tr -> true).get(0).getNumber(), is("Р002РР"));
    }

    @Ignore
    @Test
    public void whenParkingTwoTrucksAndOneCarThenFirstTruckIsIntoTruckStoreAndSecondAndCarAreIntoCarStore() {
        Store carStore = new CarStore(3);
        Store truckStore = new TruckStore(1);
        Transport truck1 = new Truck(2, "DAF", "M001MM");
        Transport truck2 = new Truck(2, "MAN", "Р002РР");
        Transport car = new Car(1, "Toyota", "К456МП");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        parking.park(truck1);
        parking.park(truck2);
        parking.park(car);
        assertThat(truckStore.findBy(tr -> true).get(0).getNumber(), is("M001MM"));
        assertThat(truckStore.findBy(tr -> true).size(), is(1));
        assertThat(carStore.findBy(cr -> true).get(0).getNumber(), is("Р002РР"));
        assertThat(carStore.findBy(cr -> true).get(1).getNumber(), is("К456МП"));
        assertThat(carStore.findBy(cr -> true).size(), is(2));
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void whenThereAreNotFreePlacesThenMustBeException() {
        Store carStore = new CarStore(1);
        Store truckStore = new TruckStore(1);
        Transport truck1 = new Truck(2, "DAF", "M001MM");
        Transport truck2 = new Truck(2, "MAN", "Р002РР");
        Parking parking = new StreetParking(List.of(carStore, truckStore));
        parking.park(truck1);
        parking.park(truck2);
    }
}