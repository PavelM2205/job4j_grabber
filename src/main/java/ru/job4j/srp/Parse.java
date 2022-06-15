package ru.job4j.srp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parse {

    private final Map<String, String> map = new HashMap<>();

    public void parseArgs(String[] args) {
        for (var el : args) {
            if (!el.matches("-.+=.+")) {
                throw new IllegalArgumentException();
            }
            Arrays.stream(args)
                    .map(str -> str.replace("-", ""))
                    .map(str -> str.split("=", 2))
                    .forEach(massive -> map.put(massive[0], massive[1]));
        }
    }

    public String get(String key) {
        return map.get(key);
    }
}
