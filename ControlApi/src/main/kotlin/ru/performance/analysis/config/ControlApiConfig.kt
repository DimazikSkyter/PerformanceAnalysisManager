package ru.performance.analysis.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.performance.analysis.log.HistoryLog
import ru.performance.analysis.service.ConfigurationService
import ru.performance.analysis.service.ConfigurationServiceImpl
import ru.performance.analysis.service.TaskService
import ru.performance.analysis.service.TaskServiceImpl
import ru.performance.analysis.storage.Storage

@Configuration
open class ControlApiConfig {

    @Bean
    open fun activeStorage() : Storage {
        return Storage()
    }

    @Bean
    open fun archiveStorage() : Storage {
        return Storage()
    }

    @Bean
    open fun historyLog(activeStorage : Storage, archiveStorage : Storage)  : HistoryLog {
        return HistoryLog(activeStorage, archiveStorage)
    }

    @Bean
    open fun taskService(historyLog: HistoryLog) : TaskService {
        return TaskServiceImpl(historyLog)
    }

    @Bean
    open fun configurationService() : ConfigurationService {
        return ConfigurationServiceImpl()
    }
}