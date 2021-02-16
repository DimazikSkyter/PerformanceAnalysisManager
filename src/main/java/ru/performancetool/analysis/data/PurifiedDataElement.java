package ru.performancetool.analysis.data;

import lombok.Data;
import lombok.NonNull;

import java.util.Map;

@Data
public class PurifiedDataElement<T> {

    private final String name;
    @NonNull
    private final String description;
    @NonNull
    private final T[] data;
    private Map<String, Integer> pointers;

    public PurifiedDataElement(String name, String description, T[] data) {
        this.name = name;
        this.description = description;
        this.data = data;
    }

    private class Point {
        private String name;
        private String description;
        private Map <String, Point> nextPoints;
        
    }
}
