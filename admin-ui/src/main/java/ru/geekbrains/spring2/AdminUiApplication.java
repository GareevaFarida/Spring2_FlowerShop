package ru.geekbrains.spring2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminUiApplication.class, args);
        System.out.println("hi!");
    }

}
