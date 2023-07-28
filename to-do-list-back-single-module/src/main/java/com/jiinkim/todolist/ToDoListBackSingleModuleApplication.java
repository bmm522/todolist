package com.jiinkim.todolist;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
public class ToDoListBackSingleModuleApplication  {


    public static void main(String[] args) {
        SpringApplication.run(ToDoListBackSingleModuleApplication.class, args);
    }



}
