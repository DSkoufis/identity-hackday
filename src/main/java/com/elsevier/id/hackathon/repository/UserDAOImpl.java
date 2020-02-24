package com.elsevier.id.hackathon.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;

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

	@Override public void createUser(String userId) {
		Item user = new Item();
		user.withPrimaryKey(new PrimaryKey("user_id", userId));
		PutItemResult res = getTable().putItem(user).getPutItemResult();
		String a = "";
	}

	@Override public void addOrUpdateAttribute(String userId, String locale, String attributeName, Object attributeValue) {
		final boolean localeExists = getTable().getItem("user_id", userId).isPresent(locale);

		if(!localeExists) {
			//create locale
		}

		final Map<String, Object> localeMap = getTable().getItem("user_id", userId).getMap(locale);

		localeMap.put(attributeName, attributeValue);

		getTable().putItem()
	}

	private Table getTable() {
		return dynamoDB.getTable("user");
	}
}
