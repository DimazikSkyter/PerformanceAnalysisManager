package ru.performance.analysis.tasks

import java.time.Duration

class TaskParameters {

    lateinit var delay : Duration
    lateinit var taskName : String
    lateinit var parameters: Map<String, String>
}