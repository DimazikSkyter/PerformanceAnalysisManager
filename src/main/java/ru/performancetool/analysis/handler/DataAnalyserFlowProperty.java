package ru.performancetool.analysis.handler;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class DataAnalyserFlowProperty {

    private String slaFunctionFileName;
}
