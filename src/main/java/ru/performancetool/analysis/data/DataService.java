package ru.performancetool.analysis.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import ru.performancetool.analysis.utilities.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
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

//    public Map<String, SourceData> convertToSourcesFromZipFile(ZipInputStream zipInputStream) throws IOException {
//        Map<String, SourceData> sourceDataMap = new HashMap<>();
//        ZipEntry zipEntry;
//        while (( zipEntry = zipInputStream.getNextEntry()) != null) {
//            byte[] extra = zipEntry.getExtra();
//            String name = zipEntry.getName();
//            SourceData sourceData = new SourceData(
//                    name,
//                    metricSchema,
//                    getFileMetricType(name),
//                    getTimeseriesType(),
//                    extra);
//            sourceDataMap.put(name, sourceData);
//        }
//        return sourceDataMap;
//    }
//
//    public Map<String, SourceData> convertToSourcesFromFolder(String folderPath) {
//        @NonNull File folder = new File(folderPath);
//        return Arrays.stream(folder.listFiles())
//                .filter(Objects::nonNull)
//                .map(file -> {
//                    String name = file.getName();
//                    try {
//                        return new SourceData(
//                                name,
//                                metricSchema,
//                                getFileMetricType(name),
//                                getTimeseriesType(),
//                                FileUtils.readFileToByteArray(file));
//                    } catch (StringIndexOutOfBoundsException e) {
//                        log.error("Error while parcing type in file name {}", name, e);
//                    } catch (IOException e) {
//                        log.error("Catch exception while calculate row number in file {}", name, e);
//                    }
//                    return null;
//                })
//                .filter(Objects::nonNull)
//                .collect(Collectors.toMap(SourceData::getFileName, o -> o));
//    }

    private Class getTimeseriesType(){
        return long.class;
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

    public void saveSourceToLocalStorage(Map<String, SourceData> sources, String uuid) throws IOException {
        String zipFileName = buildFileNameFromUUID(uuid);
        Utils.storeToZipFile(
                zipFileName,
                sources.entrySet().stream().collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> SerializationUtils.serialize(entry.getValue())
                        )));
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
