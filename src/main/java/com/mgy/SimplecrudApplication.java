package com.mgy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimplecrudApplication {

	public static void main(String[] args) {
        System.out.println("项目启动后访问路径：http://localhost:8080/customers");
	    SpringApplication.run(SimplecrudApplication.class, args);
	}
}
