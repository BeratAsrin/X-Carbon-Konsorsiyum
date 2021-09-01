package com.project.xcarbon;

import com.project.hibernate.HibernateOperations;
import com.project.information.Certificate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.project"})
public class XCarbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(XCarbonApplication.class, args);
		System.out.println("Program is running!!!");
	}

}
