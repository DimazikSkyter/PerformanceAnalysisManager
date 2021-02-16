package ru.performance.analysis.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import ru.performance.analysis.propeties.TimeoutProperties

@Controller
class ConfigurationController(
    private val timeoutProperties: TimeoutProperties
) {

    private val log = LoggerFactory.getLogger(this.javaClass)


    @GetMapping("/fullConfiguration")
    fun getFullConfiguration(
        model: Model
    ): String {
        log.info("Starting method configurations with parameters {}.")
        model.addAttribute("list", mapOf("timeout1" to timeoutProperties.timeout1, "timeout2" to timeoutProperties.timeout2))
        return "configuration"
    }

    @GetMapping("/configuration")
    fun getConfiguration(
        @RequestParam(name = "type", required = false, defaultValue = "timeouts") type: String?,
        model: Model
    ): String {
        log.info("Starting method configurations with parameters {}.", type)
        model.addAttribute("list", mapOf("timeout1" to timeoutProperties.timeout1, "timeout2" to timeoutProperties.timeout2))
        return "configuration"
    }

    @PatchMapping("/configuration")
    fun patchConfiguration(
        @RequestBody() body: ConfigurationRequestParams,
        model: Model
    ) {

    }
}