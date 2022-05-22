package com.example.triangle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
public class TriangleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TriangleApplication.class, args);
    }

}
