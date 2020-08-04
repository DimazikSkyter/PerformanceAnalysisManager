package ru.performancetool.analysis.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "data")
public class DataProperties {

    private int userApiThreadPoolSize = 4;
    private boolean storeSource = true;
}
