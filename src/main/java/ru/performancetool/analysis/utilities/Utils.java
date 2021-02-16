package ru.performancetool.analysis.utilities;

import org.apache.commons.io.IOUtils;
import org.springframework.util.SerializationUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Utils {

    public static int countLinesNew(byte[] bytes) {
        int count = 0;
        if (bytes == null) {
            return count;
        }
        for (byte b : bytes) {
            if (b == '\n') {
                count++;
            }
        }
        return count;
    }

    public static void storeToZipFile(String zipFileName, Map<String, byte[]> mapFiles) throws IOException {
        FileOutputStream baos = new FileOutputStream(zipFileName);
        ZipOutputStream zos = new ZipOutputStream(baos);
        for (Map.Entry<String, byte[]> fileEntry : mapFiles.entrySet()) {
            ZipEntry entry = new ZipEntry(fileEntry.getKey());
            entry.setSize(fileEntry.getValue().length);
            zos.putNextEntry(entry);
            zos.write(fileEntry.getValue());
        }
        zos.closeEntry();
        zos.close();
    }

    public static <T> List<T> loadObjectsFromZip(String fileName, Class<T> cls) throws IOException {
        List <T> objects = new ArrayList<>();
        ZipFile zipFile = new ZipFile(fileName);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            InputStream inputStream = zipFile.getInputStream(entry);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            IOUtils.copy(inputStream, outputStream);
            byte[] bytes = outputStream.toByteArray();
            T object = (T) SerializationUtils.deserialize(bytes);
            objects.add(object);
        }
        return objects;
    }

    public static Function loadFunction(String fileName) throws IOException {
        return (Function) SerializationUtils.deserialize(Files.readAllBytes(Paths.get(fileName)));
    }

    public static int countLinesNew(File file) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        try {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i = 0; i < 1024; ) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                System.out.println(readChars);
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        } finally {
            is.close();
        }
    }
}
