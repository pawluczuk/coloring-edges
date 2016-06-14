package com.elka.coloringedges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static String ROOT = "./upload-dir";

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

}