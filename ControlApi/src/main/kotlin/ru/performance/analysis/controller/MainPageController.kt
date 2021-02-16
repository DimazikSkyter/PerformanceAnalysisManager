package ru.performance.analysis.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class MainPageController (){

    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/greeting")
    fun greeting(
        @RequestParam(name = "name", required = false, defaultValue = "World") name: String?,
        model: Model
    ): String {
        log.info("zz")
        model.addAttribute("name", name)
        return "greeting"
    }
}