package ru.performance.analysis.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class HistoryController {

    @GetMapping("/history")
    fun patchConfiguration(
        @RequestBody() body: ConfigurationRequestParams,
        model: Model
    ) {

    }
}