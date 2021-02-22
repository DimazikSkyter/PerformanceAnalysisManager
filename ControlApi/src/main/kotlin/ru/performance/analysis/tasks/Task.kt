package ru.performance.analysis.tasks

abstract class Task (
    parameters: TaskParameters
        ) {

    abstract fun runTask()

    abstract fun stopTask()

    abstract fun getStatus() : TaskInfo
}