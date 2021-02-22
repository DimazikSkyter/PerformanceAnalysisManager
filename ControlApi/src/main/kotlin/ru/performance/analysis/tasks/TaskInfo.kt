package ru.performance.analysis.tasks

import java.time.Instant

class TaskInfo {

    lateinit var id : String
    lateinit var name : String
    lateinit var startTime : Instant
    var finishTime : Instant? = null
}