package com.elka.coloringedges;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan(basePackages = {"com.elka.coloringedges.controller", "com.elka.coloringedges.service"})
public class Application {

    public static String ROOT = "upload-dir";

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return (String args[]) -> {
            new File(ROOT).mkdir();
        };
    }

}
