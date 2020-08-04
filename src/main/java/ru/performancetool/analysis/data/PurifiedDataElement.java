package ru.performancetool.analysis.data;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
public class PurifiedDataElement<T> {

    private final String name;
    @NonNull
    private final String clob;
    @NonNull
    private T[] data;
    private Map<String, Integer> pointers;

    private class Point {
        private String name;
        private String description;
        private Map <String, Point> nextPoints;
        
    }
}
