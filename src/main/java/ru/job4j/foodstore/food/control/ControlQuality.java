package ru.job4j.foodstore.food.control;

import ru.job4j.foodstore.food.Food;
import ru.job4j.foodstore.food.store.Store;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ControlQuality implements Control {
    private final Store trash;
    private final Store shop;
    private final Store warehouse;
    private static final double WAREHOUSE_PERCENT = 25;
    private static final double SHOP_PERCENT = 75;
    private static final double SHOP_DISCOUNT_PERCENT = 100;
    private static final double DISCOUNT = 20;

    public ControlQuality(Store trash, Store shop, Store warehouse) {
        this.trash = trash;
        this.shop = shop;
        this.warehouse = warehouse;
    }

    @Override
    public void distribute(List<Food> list) {
        List<Food> products = new ArrayList<>(list);
        distributeToWarehouse(products);
        distributeToShop(products);
        distributeToShopWithDiscount(products);
        distributeToTrash(products);
    }

    private void distributeToWarehouse(List<Food> list) {
        distributeTo(list, food -> expiryCount(food) < WAREHOUSE_PERCENT, warehouse);
    }

    private void distributeToShop(List<Food> list) {
        distributeTo(list, food -> expiryCount(food) <= SHOP_PERCENT, shop);
    }

    private void distributeToShopWithDiscount(List<Food> list) {
        List<Food> result = list.stream().filter(food -> expiryCount(food) < SHOP_DISCOUNT_PERCENT)
                .peek(food -> food.setDiscount(DISCOUNT)).peek(shop::add).toList();
        list.removeAll(result);
    }

    private void distributeTo(List<Food> list, Predicate<Food> filter, Store store) {
        List<Food> result = list.stream().filter(filter).toList();
        store.addAll(result);
        list.removeAll(result);
    }

    private void distributeToTrash(List<Food> list) {
        trash.addAll(list);
    }

    private double expiryCount(Food food) {
        LocalDateTime now = LocalDateTime.now();
        double betweenNowAndCreate = differenceBetweenDate(food.getCreateDate(), now);
        double betweenExpiryAndCreate = differenceBetweenDate(food.getCreateDate(), food.getExpiryDate());
        return (betweenNowAndCreate / betweenExpiryAndCreate) * 100;
    }

    private long differenceBetweenDate(LocalDateTime start, LocalDateTime finish) {
        return Duration.between(start, finish).toDays();
    }
}
