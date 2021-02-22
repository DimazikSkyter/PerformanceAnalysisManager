package ru.performance.analysis.service

import ru.performance.analysis.tasks.TaskInfo
import ru.performance.analysis.tasks.TaskParameters

interface TaskService {

    fun runTask(name : String, parameters: TaskParameters)
    fun stopTask(id : String)
    fun getTaskStatus(id : String) : TaskInfo

}