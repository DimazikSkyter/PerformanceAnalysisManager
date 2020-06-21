package ru.performancetool.analysis.data;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

@Data
public class SourceData<T> {

    private String fileName;
    private Map mapping;
    private MetricsDescription metricsDescription;
    private T[] massive;

    @SuppressWarnings("unchecked")
    public SourceData(JsonNode metricSchema, Class<T> type, int size) {
        this.mapping = getMetricSchema(metricSchema);
        this.metricsDescription = getMetricsDescription(metricSchema);
        this.massive = (T[]) Array.newInstance(type, size);
    }

    private Map getMetricSchema(JsonNode metricSchema) {
        return new HashMap();
    }

    private MetricsDescription getMetricsDescription(JsonNode metricSchema) {
        return new MetricsDescription();
    }
}
