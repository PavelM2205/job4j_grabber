package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.function.Predicate;

public class ReportToJSON implements Report {
    private Store store;

    public ReportToJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String json = "";
        Gson gson = new GsonBuilder().create();
        json = gson.toJson(store.findBy(filter));
        return json;
    }
}
