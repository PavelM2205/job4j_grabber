package ru.job4j.foodstore.food.store;

public enum Percents {
    TWENTY_FIVE(25), SEVENTY_FIVE(75), ONE_HUNDRED(100);

    private final double percent;

    Percents(double percent) {
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }
}
