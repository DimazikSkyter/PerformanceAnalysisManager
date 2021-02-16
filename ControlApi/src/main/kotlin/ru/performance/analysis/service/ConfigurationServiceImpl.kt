package ru.performance.analysis.service

import org.springframework.stereotype.Service

@Service
class ConfigurationServiceImpl : ConfigurationService {
    override fun getFullConfiguration(): String {
        return "Test"
    }

    override fun getConfiguration(type: String): String {
        return "Test"
    }

    override fun changeConfiguration(newConfigData: Map<String, List<String>>) {
    }
}