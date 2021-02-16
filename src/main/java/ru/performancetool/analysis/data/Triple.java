package ru.performancetool.analysis.data;

import lombok.Data;

@Data
public class Triple<T,R,I> {
    private T first;
    private R second;
    private I third;

    public Triple(T first, R second, I third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
