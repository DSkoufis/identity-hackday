package com.elsevier.id.hackathon.repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;

public class AttributesTable {
	private AmazonDynamoDB client;
	private DynamoDB dynamoDB;

	public AttributesTable() {
		client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
		dynamoDB = new DynamoDB(client);
	}

	public Item getAttribute() {
		return dynamoDB.getTable("attribute").getItem("ui_view", "dropdown");
	}

}
