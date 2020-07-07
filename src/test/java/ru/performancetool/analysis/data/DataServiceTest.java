package ru.performancetool.analysis.data;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    @Test
    void testSerializationUtils() {

        TestClass testClass = new TestClass(1, 5);
        byte[] bytes = SerializationUtils.serialize(testClass);
        TestClass testClass2 = (TestClass) SerializationUtils.deserialize(bytes);
        Assertions.assertEquals(testClass, testClass2);
        log.info(testClass2.toString());
    }

    private String getJSONString() {
        return "";
    }

    @AllArgsConstructor
    @ToString
    static class TestClass implements Serializable {
        int x = 0, y = 0;

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof TestClass) {
                return ((TestClass) obj).x == x && ((TestClass) obj).y == y;
            }
            return false;
        }
    }
}