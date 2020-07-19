package ru.performancetool.analysis.data;

import lombok.Data;

import java.util.Map;

@Data
public class PurifiedDataElement<T> {

    private String name;
    private String clob;
    private long[] timeSeries;
    private T[] data;
    private Map<String, Integer> pointers;

    private class Point {
        private String name;
        private String description;
        private Map <String, Point> nextPoints;
        
    }
}
