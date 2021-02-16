package ru.performancetool.analysis.data;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.jetbrains.annotations.NotNull;
import ru.performancetool.analysis.data.parsers.Parser;
import ru.performancetool.analysis.utilities.Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.nio.charset.StandardCharsets.UTF_8;

@Data
//TODO нужно отфильтровать основной тайм сериес колонку
public class SourceData<T> implements Serializable {

    private final String fileName;
    private final Parser<T> parser;
    private Map mapping;
    private MetricsDescription metricsDescription;
    private T[] massive;
    private long[] localTimeseries;
    private AtomicInteger iterator = new AtomicInteger(0);

    @SuppressWarnings("unchecked")
    public SourceData(String fileName,
                      JsonNode metricSchema,
                      Class<T> type,
                      byte[] data) throws IOException {
        this.fileName = fileName;
        this.mapping = getMetricSchema(metricSchema);
        this.metricsDescription = getMetricsDescription(metricSchema);
        this.parser = createParser(type);
        int columnCount = metricsDescription.getMetricsDescription().size();
        int rowsCount = Utils.countLinesNew(data);
        this.massive = createMassive(type, (columnCount - 1) * rowsCount);
        this.localTimeseries = new long[rowsCount];
        fillLocalTimeSeries(data);
        writeToMassive(data, columnCount);
    }

    private void fillLocalTimeSeries(byte[] data) {

    }

    private Parser createParser(@NotNull Class<T> type) {
        if (type.equals(int.class)) {
            return (Parser<Integer>) Integer::parseInt;
        } else if (type.equals(double.class)) {
            return (Parser<Double>) Double::parseDouble;
        } else if (type.equals(long.class)) {
            return (Parser<Long>) Long::parseLong;
        }
        return (Parser<String>) str -> str;
    }

    @SuppressWarnings("unchecked")
    private T[] createMassive(Class<T> type, int size) {
        return (T[]) Array.newInstance(type, size);
    }

    private void writeToMassive(byte[] data, int columCount) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        CSVFormat csvFormat = CSVFormat.newFormat(',');
        CSVParser csv = CSVParser.parse(byteArrayInputStream, UTF_8, csvFormat);

        csv.getRecords().forEach(record -> {
                    IntStream.range(0, columCount).forEach(
                            operand -> {
                                T value = parser.parse(record.get(operand));
                                setNext(value);
                            }
                    );
                }
        );
    }

    private void setNext(T value) {
        massive[iterator.getAndIncrement()] = value;
    }

    private Map getMetricSchema(JsonNode metricSchema) {
        return new HashMap();
    }

    private MetricsDescription getMetricsDescription(JsonNode metricSchema) {
        return new MetricsDescription();
    }

    public void storeToArchive() {

    }
}
