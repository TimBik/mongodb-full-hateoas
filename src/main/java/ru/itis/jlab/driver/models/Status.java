package ru.itis.jlab.driver.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Status {

    AVAILABLE("available"), UNAVAILABLE("unavailable"), CREATED("created"), DECLINED("declined");

    private String value;
    private static final Map<String, Status> ENUM_MAP;

    static {
        Map<String, Status> map = new HashMap<String, Status>();
        for (Status instance : Status.values()) {
            map.put(instance.value(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    Status(String type) {
        this.value = type;
    }

    public String value() {
        return this.value;
    }

    public static Status fromValue(String value) {
        return ENUM_MAP.get(value);
    }

    /**
     * Used by the Mongo codec
     *
     * @return
     */
    public static Status getDefaultValue() {
        return DECLINED;
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
