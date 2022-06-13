package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> condition = (t1, t2) -> comparator.compare(t1, t2) < 0;
        return compare(value, comparator, condition);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> condition = (t1, t2) -> comparator.compare(t1, t2) > 0;
        return compare(value, comparator, condition);
    }

    private <T> T compare(List<T> value, Comparator<T> comparator, BiPredicate<T, T> condition) {
        List<T> list = new ArrayList<>(value);
        T result = list.isEmpty() ? null : list.get(0);
        for (var el : list) {
            if (condition.test(result, el)) {
                result = el;
            }
        }
        return result;
    }
}
