package ru.performancetool.analysis.data;

import java.util.Map;

public class ResultDataElement <T> {

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
