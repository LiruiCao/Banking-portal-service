package com.mycomany.bankingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingserviceApplication.class, args);
		System.out.println("Application is running");	//todo: only to confirm it runs, can be deleted when do not need it.
	}

}
