package ru.performance.analysis.log

import ru.performance.analysis.storage.Storage
import java.util.logging.Level

class HistoryLog (
    var activeStorage : Storage,
    var archiveStorage : Storage
        ) {

    fun getLines(isForward : Boolean, rows : Integer) : List<String> {
        return emptyList()
    }

    fun addEvent(event: Event) {
        if(filter(event)) {
            activeStorage.store(event)
        }
    }

    fun storeToArchive(event : Event) {
        archiveStorage.store(event)
    }

    fun filter(event : Event) : Boolean {
        return true
    }

    companion object {
        var level : Level = Level.ALL
    }
}