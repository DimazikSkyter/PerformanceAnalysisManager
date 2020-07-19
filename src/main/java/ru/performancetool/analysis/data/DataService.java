package ru.performancetool.analysis.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
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
        @NonNull File folder = new File(folderPath);
        return Arrays.stream(folder.listFiles())
                .map(file -> {
                    try {
                        return new SourceData(
                                file.getName(),
                                metricSchema,
                                getFileMetricType(file.getName()),
                                FileUtils.readFileToByteArray(file));
                    } catch (StringIndexOutOfBoundsException e) {
                        log.error("Error while parcing type in file name {}", file.getName(), e);
                    } catch (IOException e) {
                        log.error("Catch exception while calculate row number in file {}", file.getName(), e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(SourceData::getFileName, o -> o));
    }

    private Class getSourceDataType(File file) {
        val name = file.getName();
        int index = name.indexOf("_", 3) + 1;
        String shortCase = name.substring(index, 3);
        return MetricsDescription.MetricsTypes.getMetricsTypesFromShortCase(shortCase).getCls();
    }

    public PurifiedData loadResult(String uuid) throws IOException {
        PurifiedData purifiedData = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(uuid)))) {
            purifiedData = (PurifiedData) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return purifiedData;
    }

    public List<SourceData> loadSources(String uuid) throws IOException {
        return Utils.loadObjectsFromZip(buildFileNameFromUUID(uuid), SourceData.class);
    }

    public String saveSourceToLocalStorage(List<SourceData> sources) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String zipFileName = buildFileNameFromUUID(uuid);
        Utils.storeToZipFile(
                zipFileName,
                sources.stream().collect(
                        Collectors.toMap(
                                SourceData::getFileName,
                                SerializationUtils::serialize)));
        return zipFileName;
    }

    public String saveResultToLocalStorage(PurifiedData data) {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    private String buildFileNameFromUUID(String uuid) {
        return String.format("result_%s.csv", uuid);
    }

    private Class<?> getFileMetricType(String fileName) {
        return MetricsDescription.MetricsTypes.valueOf(metricSchema
                .get("files")
                .get(fileName)
                .get("type")
                .asText()).getCls();
    }
}
