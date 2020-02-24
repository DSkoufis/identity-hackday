package com.elsevier.id.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elsevier.id.hackathon.repository.AttributesTable;

@SpringBootApplication
public class HackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);

		final AttributesTable attributesTable = new AttributesTable();

		System.out.println(attributesTable.getAttribute().get("values"));
	}

}
