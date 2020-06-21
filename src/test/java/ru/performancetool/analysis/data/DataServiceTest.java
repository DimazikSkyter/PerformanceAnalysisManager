package ru.performancetool.analysis.data;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class DataServiceTest {

    private String path;
    private DataService dataService;

    @Autowired
    private DataProperties dataProperties;


    @BeforeAll
    void init() throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(getJSONString());
        dataService = new DataService(dataProperties, actualObj);
    }

    @Test
    void uploadFromFolder() {
    }

    @Test
    void loadResult() {
    }

    @Test
    void loadSources() {
    }

    @Test
    void saveSourceToLocalStorage() {
    }

    @Test
    void saveResultToLocalStorage() {
    }

    private String getJSONString() {
        return "";
    }
}