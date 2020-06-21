package ru.performancetool.analysis.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DataService {

    private JsonNode metricSchema;
    private DataProperties dataProperties;

    public DataService(DataProperties dataProperties, JsonNode metricSchema) {
        this.dataProperties = dataProperties;
        this.metricSchema = metricSchema;
    }

    public Map<String, SourceData> uploadFromFolder(String folderPath) {
        File folder = new File(folderPath);
        return Arrays.stream(folder.listFiles())
                .map(file -> {
                    try {
                        return new SourceData(metricSchema, getSourceDataType(file), Utils.countLinesNew(file));
                    } catch (StringIndexOutOfBoundsException e) {
                        log.error("Error while parcing type in file name {}", file.getName(), e);
                    } catch (IOException e) {
                        log.error("Catch exception while calculate row number in file {}", file.getName(), e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(o -> o.getFileName(), o -> o));
    }

    private Class getSourceDataType(File file) {
        val name = file.getName();
        int index = name.indexOf("_", 3) + 1;
        String shortCase = name.substring(index, 3);
        return MetricsDescription.MetricsTypes.getMetricsTypesFromShortCase(shortCase).getCls();
    }

    public ResultData loadResult(String uuid) {
        return new ResultData();
    }

    public SourceData loadSources(String uuid) {
        return new SourceData();
    }

    public String saveSourceToLocalStorage(SourceData sourceData) {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public String saveResultToLocalStorage(ResultData data) {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
