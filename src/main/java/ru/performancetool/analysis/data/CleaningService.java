package ru.performancetool.analysis.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import ru.performancetool.analysis.utilities.TimeSeriesMerger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Slf4j
public class CleaningService {

    private JsonNode clusterDataSchema;

    public CleaningService(JsonNode clusterDataSchema) {
        this.clusterDataSchema = clusterDataSchema;
    }

    public PurifiedData clean(Map<String, SourceData> sourceData, String name) throws Exception {
        List<long[]> timeSeriesLinks = new ArrayList<>();
        Map<JsonNode, List<SourceData>> groupedSourceData = group(timeSeriesLinks, sourceData);
        long[] timeSeries = collapseTimeSeries(timeSeriesLinks, name);
        Map<String, PurifiedDataElement> purifiedElements = groupedSourceData.entrySet().stream()
                .map(entry -> {
                    PurifiedDataElement purifiedDataElement;
                    try {
                        List collectData = entry.getValue().stream()
                                .map(sourceData1 -> new Triple(
                                        sourceData1.getLocalTimeseries(),
                                        sourceData1.getMassive(),
                                        sourceData1.getMetricsDescription().getMetricsDescription().size()))
                                .collect(Collectors.toList());
                        val purifiedName = entry.getKey().get("name").asText();
                        Class cls = getType(entry.getValue());
                        String description = entry.getKey().get("description").asText();
                        purifiedDataElement = new PurifiedDataElement(
                                purifiedName,
                                description,
                                concatData(timeSeries, collectData, cls));
                    } catch (Exception ex) {
                        log.error("Catch error on handle data {}", entry, ex);
                        throw ex;
                    }
                    return purifiedDataElement;
                })
                .collect(Collectors.toMap(
                        PurifiedDataElement::getName,
                        purifiedElement -> purifiedElement));
        return new PurifiedData(timeSeries, purifiedElements);
    }

    private Class getType(List<SourceData> value) {
        return value.get(0).getMassive()[0].getClass();
    }

    private <T, R, I> R[] concatData(long[] timeSeries, List<Triple<long[], R[], I>> dataWithTimeSeries, Class<R> type) {
        int size = timeSeries.length * calcTotalColumns(dataWithTimeSeries);
        R[] data = (R[]) Array.newInstance(type, size);
        fill(data, dataWithTimeSeries, timeSeries);
        return data;
    }

    private <R, I, T> void fill(R[] data, List<Triple<long[], R[], I>> dataWithTimeSeries, long[] timeSeries) {
        int globalIndex = 0;
        for (Triple<long[], R[], I> triTriple : dataWithTimeSeries) {
            long[] localTimeseries = triTriple.getFirst();
            R[] localData = triTriple.getSecond();
            for(int i = 0; i < timeSeries.length; i++) {
//                data[globalIndex] = getValue()
            }
        }
    }

    @NotNull
    private <T, R, I> Integer calcTotalColumns(List<Triple<T, R, I>> dataWithTimeSeries) {
        return dataWithTimeSeries.stream()
                .map(triTriple -> (Integer) triTriple.getThird())
                .reduce(Integer::sum)
                .orElse(0);
    }

    private long[] collapseTimeSeries(List<long[]> timeSeriesLinks, String name) throws Exception {
        return timeSeriesLinks.stream().reduce(TimeSeriesMerger::merge).orElseThrow(
                () -> new Exception("Get exception in collapse timeseries. Handling files with name " + name)
        );
    }

    private Map<JsonNode, List<SourceData>> group(List<long[]> timeSeriesLinks, Map<String, SourceData> sourceData) {
        return sourceData.entrySet().stream()
                //TODO нужно отделить на абстрактые метрики и расфильтровать по типам
                .flatMap(entry -> {
                    timeSeriesLinks.add(entry.getValue().getLocalTimeseries());
                    return Stream.of(entry);
                })
                .collect(Collectors.groupingBy(
                        this::getGroup,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private JsonNode getGroup(Map.Entry<String, SourceData> srcData) {
        return null;
    }

}
