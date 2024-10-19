package com.example.HomeLoanApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class HomeLoanAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeLoanAppApplication.class, args);
		LocalDate localDate = LocalDate.of(2016,2,29);
		localDate = localDate.withYear(205);
		System.out.println("loca"+localDate);

	}
}
