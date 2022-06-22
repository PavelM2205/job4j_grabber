package ru.job4j.parking.transport;

import java.util.Objects;

public class Car extends Transport {
    private final String model;
    private final String number;

    public Car(String model, String number) {
        this.model = model;
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public int getSize() {
        return Transport.MIN_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(model, car.model) && Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, number);
    }
}
