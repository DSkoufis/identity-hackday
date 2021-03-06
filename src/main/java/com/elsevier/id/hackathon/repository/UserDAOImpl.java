package com.elsevier.id.hackathon.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

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
		Map<String, ExpectedAttributeValue> params = new HashMap<>();
		params.put("user_id", new ExpectedAttributeValue(false));

		Map<String, AttributeValue> item = new HashMap<>();
		item.put("user_id", new AttributeValue(userId));

		PutItemRequest putItemRequest = new PutItemRequest()
				.withTableName("user")
				.withItem(item)
				.withExpected(params);
		client.putItem(putItemRequest);
	}

	@Override public void addOrUpdateAttribute(String userId, String locale, String attributeName, Object attributeValue) {
		final boolean localeExists = getTable().getItem("user_id", userId).isPresent(locale);

		Map<String, Object> localeMap = new HashMap<>();
		if (localeExists) {
			localeMap = getTable().getItem("user_id", userId).getMap(locale);
		}

		localeMap.put(attributeName, attributeValue);

		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("user_id", userId)
				.withUpdateExpression("set " + locale + " = :lm")
				.withValueMap(Collections.singletonMap(":lm", localeMap))
				.withReturnValues(ReturnValue.UPDATED_NEW);
		getTable().updateItem(updateItemSpec);
	}

	private Table getTable() {
		return dynamoDB.getTable("user");
	}
}
