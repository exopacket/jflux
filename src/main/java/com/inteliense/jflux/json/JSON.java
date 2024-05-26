package com.inteliense.jflux.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSON {

    public static <Any> Any parse(String str) {
        return (Any) JsonParser.parseString(str);
    }

    public static <Any> Any parse(String str, Class<?> c) {
        Gson gson = new Gson();
        return (Any) gson.fromJson(str, c);
    }

    public static String string(Object o) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(o);
    }

    public static String prettyString(Object o) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(o);
    }

    // legacy

    public static JSONObject getObject(String body) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(body);
            return (JSONObject) obj;
        } catch (Exception ignored) {
        }
        return new JSONObject();
    }

    public static String getString(JSONObject obj) {
        return getString(obj, false);
    }

    public static String getString(JSONObject obj, boolean format) {
        try {
            String jsonStr = obj.toJSONString();
            Gson gson = (format) ? (new GsonBuilder().setPrettyPrinting().create()) : (new GsonBuilder().create());
            JsonObject jsonObject = JsonParser.parseString(jsonStr).getAsJsonObject();
            return gson.toJson(jsonObject);
        } catch (Exception ignored) { }
        return "{}";
    }

}
