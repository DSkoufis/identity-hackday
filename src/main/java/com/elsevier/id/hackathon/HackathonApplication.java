package com.elsevier.id.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elsevier.id.hackathon.repository.AttributesDAOImpl;

@SpringBootApplication
public class HackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
		System.out.println("Woo dimitris it is up.");
	}

}
