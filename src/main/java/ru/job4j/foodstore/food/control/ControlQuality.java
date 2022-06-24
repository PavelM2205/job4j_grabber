package ru.job4j.foodstore.food.control;

import ru.job4j.foodstore.food.Food;
import ru.job4j.foodstore.food.store.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality implements Control {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public void distribute(List<Food> list) {
        List<Food> products = new ArrayList<>(list);
        for (var store : stores) {
            for (var food : list) {
                if (store.accept(food)) {
                    store.add(food);
                }
            }
        }
    }

    public void resort() {
        List<Food> products = stores.stream()
                .map(store -> store.findBy(food -> true))
                .flatMap(List::stream)
                .toList();
        for (var store : stores) {
            store.clearStore();
        }
        distribute(products);
    }
}
