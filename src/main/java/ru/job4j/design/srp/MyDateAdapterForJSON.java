package ru.job4j.design.srp;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyDateAdapterForJSON implements JsonSerializer<Calendar> {
    @Override
    public JsonElement serialize(Calendar calendar, Type type, JsonSerializationContext jsonSerializationContext) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(calendar.getTime());
        return calendar == null ? null : new JsonPrimitive(date);
    }
}
