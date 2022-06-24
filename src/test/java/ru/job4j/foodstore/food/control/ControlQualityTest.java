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
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 20));
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
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
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 20));
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
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
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 20));
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        control.distribute(list);
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
    }

    @Test
    public void whenExpiryIsSpentOn75PercentsThenFoodMustBeIntoShopAndPriceNotChanged() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(75);
        LocalDateTime expiry = now.plusDays(25);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 20));
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        control.distribute(list);
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
        assertThat(shop.findBy(fd -> true).get(0).getPrice(), is(list.get(0).getPrice()));
    }

    @Test
    public void whenExpiryIsSpentOn76PercentsThenFoodMustBeIntoShopAndDiscountEqual20() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(76);
        LocalDateTime expiry = now.plusDays(24);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 20));
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
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
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 20));
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        control.distribute(list);
        double expectedPrice = 120;
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
        assertThat(shop.findBy(fd -> true).get(0).getPrice(), is(expectedPrice));
    }

    @Test
    public void whenExpiryIsSpentOn100PercentsThenFoodMustBeIntoTrash() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime create = now.minusDays(100);
        LocalDateTime expiry = now.plusDays(0);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(new Food("apple", expiry, create, 150, 20));
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
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
        List<Food> list = List.of(new Food("apple", appleExpiry, appleCreate, 150, 20),
                new Food("meat", meatExpiry, meatCreate, 500, 20));
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        control.distribute(list);
        assertThat(shop.findBy(fd -> true).get(0), is(list.get(0)));
        assertThat(shop.findBy(fd -> true).get(1), is(list.get(1)));
    }

    @Test
    public void whenAddFourFoodsThenMustBeAllAllCase() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appleCreate = now.minusDays(20);
        LocalDateTime appleExpiry = now.plusDays(80);
        LocalDateTime meatCreate = now.minusDays(30);
        LocalDateTime meatExpiry = now.plusDays(70);
        LocalDateTime colaCreate = now.minusDays(80);
        LocalDateTime colaExpiry = now.plusDays(20);
        LocalDateTime milkCreate = now.minusDays(120);
        LocalDateTime milkExpiry = now.minusDays(5);
        double expectedPriceOfCola = 64;
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Food> list = List.of(
                new Food("apple", appleExpiry, appleCreate, 150, 20),
                new Food("meat", meatExpiry, meatCreate, 500, 20),
                new Food("coca-cola", colaExpiry, colaCreate, 80, 20),
                new Food("milk", milkExpiry, milkCreate, 90, 20)
        );
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        control.distribute(list);
        assertThat(warehouse.findBy(fd -> true).size(), is(1));
        assertThat(warehouse.findBy(fd -> true).get(0).getName(), is("apple"));
        assertThat(shop.findBy(fd -> true).size(), is(2));
        assertThat(shop.findBy(fd -> true).get(0).getName(), is("meat"));
        assertThat(shop.findBy(fd -> true).get(1).getPrice(), is(expectedPriceOfCola));
        assertThat(trash.findBy(fd -> true).size(), is(1));
        assertThat(trash.findBy(fd -> true).get(0).getName(), is("milk"));
    }

    @Test
    public void whenChangeDateAndResortThenFoodsIntoStoresMustSwapPlaces() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appleCreate = now.minusDays(20);
        LocalDateTime appleExpiry = now.plusDays(80);
        LocalDateTime meatCreate = now.minusDays(30);
        LocalDateTime meatExpiry = now.plusDays(70);
        LocalDateTime milkCreate = now.minusDays(120);
        LocalDateTime milkExpiry = now.minusDays(5);
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        Food apple = new Food("apple", appleExpiry, appleCreate, 150, 20);
        Food meat = new Food("meat", meatExpiry, meatCreate, 500, 20);
        Food milk = new Food("milk", milkExpiry, milkCreate, 90, 20);
        List<Food> list = List.of(apple, meat, milk);
        control.distribute(list);
        assertThat(warehouse.findBy(fd -> true), is(List.of(apple)));
        assertThat(shop.findBy(fd -> true), is(List.of(meat)));
        assertThat(trash.findBy(fd -> true), is(List.of(milk)));
        appleCreate = now.minusDays(30);
        appleExpiry = now.plusDays(70);
        apple.setCreateDate(appleCreate);
        apple.setExpiryDate(appleExpiry);
        meatCreate = now.minusDays(100);
        meatExpiry = now;
        meat.setCreateDate(meatCreate);
        meat.setExpiryDate(meatExpiry);
        milkCreate = now.minusDays(20);
        milkExpiry = now.plusDays(80);
        milk.setCreateDate(milkCreate);
        milk.setExpiryDate(milkExpiry);
        control.resort();
        assertThat(warehouse.findBy(fd -> true), is(List.of(milk)));
        assertThat(shop.findBy(fd -> true), is(List.of(apple)));
        assertThat(trash.findBy(fd -> true), is(List.of(meat)));
    }
}