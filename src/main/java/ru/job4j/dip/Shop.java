package ru.job4j.dip;

import java.util.HashMap;
import java.util.Map;

public class Shop {
    private MemStore store = new MemStore();

    public void addItem() {
    }

    public Item getItem() {
        return null;
    }


    public class MemStore {
        private Map<String, Item> store = new HashMap<>();

        public void add() {
        }

        public void remove() {
        }
    }

    private class Item {
        private String name;
        private double price;

        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
}
