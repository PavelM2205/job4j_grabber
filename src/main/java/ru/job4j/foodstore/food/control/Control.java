package ru.job4j.foodstore.food.control;

import ru.job4j.foodstore.food.Food;

import java.util.List;

public interface Control {
    void distribute(List<Food> list);
}
