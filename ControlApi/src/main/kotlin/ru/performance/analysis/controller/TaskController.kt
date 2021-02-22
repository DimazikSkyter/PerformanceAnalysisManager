package ru.performance.analysis.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ru.performance.analysis.service.TaskService
import ru.performance.analysis.tasks.TaskParameters

@Controller("/tasks")
class TaskController (
    val taskService : TaskService
        ) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/run")
    fun runTask(
        name : String,
        parameters : TaskParameters,
        model : Model
    ): String {
        log.info("Starting method run task with name {} and parameters {}.", name, parameters)
        taskService.runTask(name, parameters)
        model.addAttribute("list", mapOf("timeout1" to "timeoutProperties.timeout1", "timeout2" to "timeoutProperties.timeout2"))
        return "configuration"
    }

    @GetMapping("/stop")
    fun stopTask(
        id : String,
        model : Model
    ): String {
        log.info("Starting method stop task with id {}.", id)
        taskService.stopTask(id)
        model.addAttribute("list", mapOf("timeout1" to "timeoutProperties.timeout1", "timeout2" to "timeoutProperties.timeout2"))
        return "configuration"
    }

    @GetMapping("/task_status")
    fun getTaskStatus(
        id : String,
        model : Model
    ): String {
        log.info("Starting method get task status with id {}.", id)
        taskService.getTaskStatus(id)
        model.addAttribute("list", mapOf("timeout1" to "timeoutProperties.timeout1", "timeout2" to "timeoutProperties.timeout2"))
        return "configuration"
    }
}