package ru.job4j.foodstore.food.control;

import org.junit.Test;
import ru.job4j.foodstore.food.Food;
import ru.job4j.foodstore.food.store.Shop;
import ru.job4j.foodstore.food.store.Store;
import ru.job4j.foodstore.food.store.Trash;
import ru.job4j.foodstore.food.store.WareHouse;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ControlQualityTest {

    @Test
    public void whenExpiryIsSpentOn20PercentsThenFoodMustBeIntoWarehouse() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(20);
        LocalDateTime expiry = now.plusDays(80);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 0));
        ControlQuality control = new ControlQuality(trash, shop, warehouse);
        control.distribute(list);
        assertThat(warehouse.findBy(fd -> true).get(0), is(list.get(0)));
    }

    @Test
    public void whenExpiryIsSpentOn25PercentsThenFoodMustBeIntoShop() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(25);
        LocalDateTime expiry = now.plusDays(75);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 0));
        ControlQuality control = new ControlQuality(trash, shop, warehouse);
        control.distribute(list);
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
    }

    @Test
    public void whenExpiryIsSpentOn60PercentsThenFoodMustBeIntoShop() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(40);
        LocalDateTime expiry = now.plusDays(60);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 0));
        ControlQuality control = new ControlQuality(trash, shop, warehouse);
        control.distribute(list);
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
    }

    @Test
    public void whenExpiryIsSpentOn75PercentsThenFoodMustBeIntoShopAndDiscount0() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(75);
        LocalDateTime expiry = now.plusDays(25);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 0));
        ControlQuality control = new ControlQuality(trash, shop, warehouse);
        control.distribute(list);
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
        assertThat(shop.findBy(fd -> true).get(0).getDiscount(), is(0.0));
    }

    @Test
    public void whenExpiryIsSpentOn76PercentsThenFoodMustBeIntoShopAndDiscountEqual20() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(76);
        LocalDateTime expiry = now.plusDays(24);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 0));
        ControlQuality control = new ControlQuality(trash, shop, warehouse);
        control.distribute(list);
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
        assertThat(shop.findBy(fd -> true).get(0).getDiscount(), is(20.0));
    }

    @Test
    public void whenExpiryIsSpentOn99PercentsThenFoodMustBeIntoShopAndDiscountEqual20() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(99);
        LocalDateTime expiry = now.plusDays(1);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 0));
        ControlQuality control = new ControlQuality(trash, shop, warehouse);
        control.distribute(list);
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
        assertThat(shop.findBy(fd -> true).get(0).getDiscount(), is(20.0));
    }

    @Test
    public void whenExpiryIsSpentOn100PercentsThenFoodMustBeIntoTrash() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(100);
        LocalDateTime expiry = now.plusDays(0);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 0));
        ControlQuality control = new ControlQuality(trash, shop, warehouse);
        control.distribute(list);
        assertThat(trash.findBy(fd -> true).get(0), is(list.get(0)));
    }

    @Test
    public void whenAddTwoFoodsWithExpiry40And50PercentsThenMustBeTwoFoodsIntoShop() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appleCreate = now.minusDays(40);
        LocalDateTime appleExpiry = now.plusDays(60);
        LocalDateTime meatCreate = now.minusDays(50);
        LocalDateTime meatExpiry = now.plusDays(50);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", appleExpiry, appleCreate, 150, 0),
                new Food("meat", meatExpiry, meatCreate, 500, 0));
        ControlQuality control = new ControlQuality(trash, shop, warehouse);
        control.distribute(list);
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
        assertThat(shop.findBy(fd -> true).get(1), is(list.get(1)));
    }
}