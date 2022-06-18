package ru.job4j.foodstore.food.store;

import ru.job4j.foodstore.food.Food;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    void add(Food food);
    void addAll(List<Food> list);
    List<Food> findBy(Predicate<Food> filter);
}
