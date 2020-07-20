package ru.performancetool.analysis.store;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "storage")
public class StorageProperty {

    private String storagePath;
}

