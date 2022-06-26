package ru.job4j.isp.menu;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(new Menu.MenuItemInfo("Сходить в магазин", List.of("Купить продукты"),
                STUB_ACTION, "1."), menu.select("Сходить в магазин").get());
        assertEquals(new Menu.MenuItemInfo("Купить продукты", List.of("Купить хлеб", "Купить молоко"),
                STUB_ACTION, "1.1."), menu.select("Купить продукты").get());
        assertEquals(new Menu.MenuItemInfo("Покормить собаку", List.of(), STUB_ACTION, "2."),
                menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenAddThenSelectMustBeSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Убраться в квартире", STUB_ACTION);
        assertEquals(new Menu.MenuItemInfo("Убраться в квартире", List.of(), STUB_ACTION, "1."),
                menu.select("Убраться в квартире").get());
    }

    @Test
    public void whenSelectEmptyMenuThenMustBeOptionalEmpty() {
        Menu menu = new SimpleMenu();
        assertEquals(menu.select("Убраться в квартире"), Optional.empty());
    }

    @Test
    public void whenWrongSelectThenMustBeOptionEmpty() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Убраться в квартире", STUB_ACTION);
        assertEquals(menu.select("Вынести мусор"), Optional.empty());
    }

    @Test
    public void whenSelectThenMustBeExecuteAction() {
        Menu menu = new SimpleMenu();
        List<String> list = new ArrayList<>();
        String test = "abc";
        ActionDelegate delegate = () -> list.add(test);
        menu.add(Menu.ROOT, "Убраться в квартире", delegate);
        Menu.MenuItemInfo itemInfo = menu.select("Убраться в квартире").get();
        itemInfo.getActionDelegate().delegate();
        assertEquals(list, List.of("abc"));
    }
}