package ru.performance.analysis.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.performance.analysis.log.HistoryLog
import ru.performance.analysis.service.ConfigurationService
import ru.performance.analysis.service.ConfigurationServiceImpl

@Configuration
open class ControlApiConfig {

    @Bean
    open fun historyLog()  : HistoryLog {
        return HistoryLog()
    }

    @Bean
    open fun configurationService() : ConfigurationService {
        return ConfigurationServiceImpl()
    }
}