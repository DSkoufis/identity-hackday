package com.elsevier.id.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elsevier.id.hackathon.db.AttributesTable;

@SpringBootApplication
public class HackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);

		final AttributesTable attributesTable = new AttributesTable();

		System.out.println(attributesTable.getAttribute().get("values"));
	}

}
