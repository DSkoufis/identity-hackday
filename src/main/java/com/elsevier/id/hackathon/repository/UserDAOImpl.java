package com.elsevier.id.hackathon.repository;

import org.springframework.stereotype.Repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

@Repository
public class UserDAOImpl implements UserDAO {

	private AmazonDynamoDB client;
	private DynamoDB       dynamoDB;

	public UserDAOImpl() {
		client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
		dynamoDB = new DynamoDB(client);
	}

	@Override public Object findAttributeByUser(String userId, String locale, String attributeName) {
		return getTable().getItem("user_id", userId).getMap(locale).get(attributeName);
	}

	private Table getTable() {
		return dynamoDB.getTable("user");
	}
}
