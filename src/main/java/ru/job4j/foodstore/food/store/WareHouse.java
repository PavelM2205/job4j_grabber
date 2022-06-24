package ru.job4j.foodstore.food.store;

import ru.job4j.foodstore.food.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class WareHouse implements Store {
    private final List<Food> store = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        return expiryCount(food) < Percents.TWENTY_FIVE.getPercent();
    }

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (accept(food)) {
            store.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return store.stream().filter(filter).toList();
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
