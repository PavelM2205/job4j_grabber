package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenMaxThenC() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("a", "b", "c");
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        assertEquals("c", maxMin.max(list, comp));
    }

    @Test
    public void whenMinThenA() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("a", "b", "c");
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        assertEquals("a", maxMin.min(list, comp));
    }

    @Test
    public void whenEmptyListThenNull() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of();
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        assertNull(maxMin.max(list, comp));
    }
}