package ru.performancetool.analysis.data;


import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Data;

import javax.xml.transform.Source;
import java.lang.reflect.Array;

@Data
public class SourceData<T> {


    private JSONPObject mapping;
    private MetricsDescription metricsDescription;
    private T[] massive;

    @SuppressWarnings("unchecked")
    public SourceData(JSONPObject mapping, MetricsDescription metricsDescription, Class<T> type, int size) {
        this.mapping = mapping;
        this.metricsDescription = metricsDescription;
        this.massive = (T[]) Array.newInstance(type, size);
    }

}
