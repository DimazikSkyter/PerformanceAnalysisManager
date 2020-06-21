package ru.performancetool.analysis.data;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DataService {

    JSONPObject metricSchema;

    public DataService(DataProperties dataProperties, JSONPObject metricSchema) {
    }

    public SourceData uploadFromFolder(String folder){
        return new SourceData();
    }

    public ResultData loadResult(String uuid){
        return new ResultData();
    }

    public SourceData loadSources(String uuid){
        return new SourceData();
    }

    public String saveSourceToLocalStorage(SourceData sourceData){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public String saveResultToLocalStorage(ResultData data){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
