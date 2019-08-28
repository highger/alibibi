package com.mgy.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        System.out.println("项目成立日:2019年8月28日");
        SpringApplication.run(ProjectApplication.class, args);
    }

}
