package ru.performance.analysis.propeties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "timeout")
class TimeoutProperties {

    var timeout1 : Int = 0
    var timeout2 : Int = 0
}