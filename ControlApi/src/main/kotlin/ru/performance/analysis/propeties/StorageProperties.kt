package ru.performance.analysis.propeties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "storage")
class StorageProperties {

    lateinit var pathToStorage : String
}