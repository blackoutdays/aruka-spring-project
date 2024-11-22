package com.mjc.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mjc.school.aruka")
public class ArukaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArukaApplication.class, args);
    }
}