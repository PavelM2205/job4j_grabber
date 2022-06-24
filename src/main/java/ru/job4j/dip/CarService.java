package ru.job4j.dip;

import ru.job4j.parking.transport.Car;

public class CarService {
    CarRecover recover;

    public CarService(CarRecover recover) {
        this.recover = recover;
    }

    public boolean carRecover(Car car) {
        recover.recover(car);
        return true;
    }

    public boolean carWash(Car car) {
        return false;
    }


    public static class CarRecover {

        public boolean recover(Car car) {
            return false;
        }
    }
}
