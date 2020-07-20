package ru.performancetool.analysis.store;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ClusterStorage extends Storage {


    public ClusterStorage(StorageProperty storageProperty) {
        super(storageProperty);
    }

    public List<Object> loadClusterInfo(String clusterName) throws IOException {
        byte[] bytes = super.loadFromCache(clusterName);
        if(bytes == null) {
            bytes = super.loadFromStorage(clusterName);
            saveToCache(clusterName, bytes);
        }
        return deserializeCluster(bytes);

    }

    public void saveClusterInfo(String name, List<Object> cluster) throws IOException {
        super.saveToStorage(name, serializeCluster(cluster));
    }

    private byte[] serializeCluster(List<Object> cluster) {
        return new byte[11];
    }

    private List<Object> deserializeCluster(byte[] bytes) {
        return Collections.emptyList();
    }
}
