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
        assertEquals("c", maxMin.max(list, Comparator.naturalOrder()));
    }

    @Test
    public void whenMinThenA() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("a", "b", "c");
        assertEquals("a", maxMin.min(list, Comparator.naturalOrder()));
    }

    @Test
    public void whenEmptyListThenNull() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of();
        assertNull(maxMin.max(list, Comparator.naturalOrder()));
    }
}