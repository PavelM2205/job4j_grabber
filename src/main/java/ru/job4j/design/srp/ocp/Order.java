package ru.job4j.design.srp.ocp;


public class Order {
    private CarShipping carShipping;

    public Order() {
    }

    public Order changeOrder() {
        return null;
    }

    public void goToConsumer() {
        carShipping = new CarShipping();
        carShipping.delivery(this);
    }

    private static class CarShipping {
        public void delivery(Order order) {
        }
    }

    public static void main(String[] args) {
        Order order1 = new Order();
        order1.goToConsumer();
    }
}
