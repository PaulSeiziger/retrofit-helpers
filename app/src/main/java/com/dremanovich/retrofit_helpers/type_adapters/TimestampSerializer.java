package com.dremanovich.retrofit_helpers.type_adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Timestamp;

public class TimestampSerializer implements JsonSerializer<Timestamp> {
    @Override
    public JsonElement serialize(Timestamp src, Type typeOfSrc, JsonSerializationContext context) {
        return src == null ? null : new JsonPrimitive(src.getTime());
    }
}
