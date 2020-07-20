package ru.performancetool.analysis.store;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.Cache;
import org.springframework.cache.support.NoOpCache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Storage {

    private StorageProperty storageProperty;
    private Cache cache;
    private String storageDir;

    public Storage(StorageProperty storageProperty) {
        this.storageProperty = storageProperty;
        this.storageDir = storageProperty.getStoragePath();
        this.cache = new NoOpCache("storage");
    }

    public void saveToCache(String name, byte[] bytes) {
        cache.put(name, bytes);
    }

    public void saveToStorage(String name, byte[] bytes) throws IOException {
        Files.write(getPath(name), bytes);
    }

    public byte[] loadFromCache(String name) {
        return cache.get(name, byte[].class);
    }

    public byte[] loadFromStorage(String name) throws IOException {
        return Files.readAllBytes(getPath(name));
    }

    @NotNull
    private Path getPath(String name) {
        return Paths.get(storageDir + name);
    }
}
