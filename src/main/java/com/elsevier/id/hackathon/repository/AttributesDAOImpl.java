package com.elsevier.id.hackathon.repository;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

@Repository
public class AttributesDAOImpl implements AttributesDAO {

	private AmazonDynamoDB client;
	private DynamoDB dynamoDB;

	public AttributesDAOImpl() {
		client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
		dynamoDB = new DynamoDB(client);
	}

	@Override
	public Item getAttributeByValue(String value) {
		return getTable().getItem("attribute_name", value);
	}

	private Table getTable() {
		return dynamoDB.getTable("attribute");
	}
}
