package ru.job4j.foodstore.food.store;

import ru.job4j.foodstore.food.Food;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

public interface Store {
    boolean accept(Food food);
    boolean add(Food food);
    List<Food> findBy(Predicate<Food> filter);
    void clearStore();

    default double expiryCount(Food food) {
        LocalDateTime now = LocalDateTime.now();
        double betweenNowAndCreate = Duration.between(food.getCreateDate(), now).toDays();
        double betweenExpiryAndCreate = Duration.between(food.getCreateDate(), food.getExpiryDate()).toDays();
        return (betweenNowAndCreate / betweenExpiryAndCreate) * 100;
    }
}
