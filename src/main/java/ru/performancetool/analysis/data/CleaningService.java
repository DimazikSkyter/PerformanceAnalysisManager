package ru.performancetool.analysis.data;

import java.util.Map;

public class CleaningService {

    public PurifiedData clean(Map<String, SourceData> sourceData) {
        sourceData.entrySet().stream()
                .flatMap(entry -> {
                    SourceData value = entry.getValue();
                    MetricsDescription metricsDescription = value.getMetricsDescription();
                    return metricsDescription.getMetricsDescription().stream().map(
                            pair -> new PurifiedDataElement<>("", "", null));

                });
        new PurifiedDataElement<>();
    }
}
