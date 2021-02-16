package ru.performance.analysis.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.ui.ExtendedModelMap
import org.springframework.ui.Model
import ru.performance.analysis.service.ConfigurationService
import ru.performance.analysis.service.ConfigurationServiceImpl


internal class ConfigurationControllerTest {

    lateinit var configurationController : ConfigurationController
    lateinit var model: Model
    @MockBean
    lateinit var configurationService : ConfigurationService

    @BeforeEach
    fun before() {
        configurationService = ConfigurationServiceImpl()
        model = ExtendedModelMap()
        configurationController = ConfigurationController(configurationService)
    }

    @Test
    fun getFullConfiguration() {
        configurationController.getFullConfiguration(model)
    }

    @Test
    fun getConfiguration() {
        configurationController.getConfiguration("timeout", model)
    }

    @Test
    fun patchConfiguration() {
        configurationService.changeConfiguration(emptyMap())
    }
}