package ru.performance.analysis.service


interface ConfigurationService {

    fun getFullConfiguration() : String
    fun getConfiguration(type : String) : String
    fun changeConfiguration(newConfigData : Map<String, List<String>>)
}