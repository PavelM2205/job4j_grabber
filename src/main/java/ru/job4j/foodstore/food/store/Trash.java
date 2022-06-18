package ru.job4j.foodstore.food.store;

import ru.job4j.foodstore.food.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Trash implements Store {
    private final List<Food> store = new ArrayList<>();

    @Override
    public void add(Food food) {
        store.add(food);
    }

    @Override
    public void addAll(List<Food> list) {
        store.addAll(list);
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return store.stream().filter(filter).toList();
    }
}
