package ru.performancetool.analysis.data;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

class UtilsTest {

    @Test
    void storeToZipFile() throws IOException {
        String str1 = "123";
        String str2 = "444";
        String filePath = "test.zip";
        byte[] bytes1 = str1.getBytes();
        byte[] bytes2 = str2.getBytes();
        Map <String, byte[]> map = new HashMap();
        map.put(str1, bytes1);
        map.put(str2, bytes2);
        Utils.storeToZipFile(filePath, map);
        Files.delete(Paths.get(filePath));
    }
}