package ru.performance.analysis



import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan


@ConfigurationPropertiesScan
@SpringBootApplication
open class ControlApiApp {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ControlApiApp::class.java, *args)
        }
    }
}