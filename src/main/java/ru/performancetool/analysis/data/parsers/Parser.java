package ru.performancetool.analysis.data.parsers;

public interface Parser <T> {

    T parse(String str);
}
