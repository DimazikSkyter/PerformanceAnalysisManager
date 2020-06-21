package ru.performancetool.analysis;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import ru.performancetool.analysis.data.DataProperties;

@ConfigurationPropertiesScan
@SpringBootApplication
public class Analysis {

    public static void main(String[] args) {
        SpringApplication.run(Analysis.class, args);
    }
    //Открытие результатов
    //Обработка
    //Вывод

    //Сохранение выводов

    @Bean
    public DataProperties dataProperties(){return  new DataProperties();}

    @Bean
    public JSONPObject metricSchema(){return null;}
}
