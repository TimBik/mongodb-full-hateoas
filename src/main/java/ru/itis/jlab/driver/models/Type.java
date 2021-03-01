package ru.itis.jlab.driver.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Type {
    TECHNIQUE("technique"), FOOD("food"), WATER("water"), CHEMICALS("chemicals"), CLOTHES("clothes");

    private String value;
    private static final Map<String, Type> ENUM_MAP;

    static {
        Map<String, Type> map = new HashMap<>();
        for (Type instance : Type.values()) {
            map.put(instance.value(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    Type(String type) {
        this.value = type;
    }

    public String value() {
        return this.value;
    }

    public static Type fromValue(String value) {
        return ENUM_MAP.get(value);
    }

    /**
     * Used by the Mongo codec
     *
     * @return
     */
    public static Type getDefaultValue() {
        return CLOTHES;
    }

    /**
     * Required to properly convert Java Enum name to value.
     * Value is used by front-end and usually uses <br>
     * 1. lowercase <br>
     * 2. dashes instead of underscores <br> <br>
     */
    @Override
    public String toString() {
        return this.value;
    }
}
