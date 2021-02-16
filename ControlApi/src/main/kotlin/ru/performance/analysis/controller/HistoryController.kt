package ru.performance.analysis.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.performance.analysis.log.HistoryLog


@Controller
class HistoryController(
    val historyLog: HistoryLog
) {


    @GetMapping("/history")
    fun patchConfiguration(
        @RequestParam(name = "isForward", defaultValue = "false") isForward: Boolean,
        @RequestParam(name = "rows", defaultValue = "10") rows: Integer,
        model: Model
    ) : String {
        model.addAttribute("list", historyLog.getLines(isForward, rows))
        return "history"
    }
}