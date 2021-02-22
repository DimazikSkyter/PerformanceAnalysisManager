package ru.performance.analysis.service

import ru.performance.analysis.log.Event
import ru.performance.analysis.log.HistoryLog
import ru.performance.analysis.tasks.SimpleEventTask
import ru.performance.analysis.tasks.Task
import ru.performance.analysis.tasks.TaskInfo
import ru.performance.analysis.tasks.TaskParameters
import java.util.*
import kotlin.collections.HashMap

class TaskServiceImpl (
        var historyLog: HistoryLog
        ) : TaskService  {

    private var tasks : MutableMap <String, Task> = HashMap()

    override fun runTask(name : String, parameters: TaskParameters) {
        val uid : String = UUID.randomUUID().toString()
        tasks[uid] = taskOf(name, parameters)
    }

    override fun stopTask(id : String) {
        tasks[id]?.stopTask()
        tasks.remove(id)
        historyLog.addEvent(Event())
    }

    override fun getTaskStatus(id :String): TaskInfo {
        val taskInfo = tasks[id]?.getStatus()
        with(taskInfo) {
            if(this == null) {
                return TaskInfo()
            }
            return taskInfo!!
        }
    }

    private fun taskOf(name : String, parameters: TaskParameters) : Task {
        when (name) {
            "SimpleEventTask" -> return SimpleEventTask(parameters)
            else -> throw Exception("Failed to start task with name $name")
        }
    }

}