package com.elsevier.id.hackathon.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchGetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableKeysAndAttributes;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

@Repository
public class AttributesDAOImpl implements AttributesDAO {

	private AmazonDynamoDB client;
	private DynamoDB       dynamoDB;

	public AttributesDAOImpl() {
		client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
		dynamoDB = new DynamoDB(client);
	}

	@Override
	public Item getAttribute(String value) {
		return getTable().getItem("attribute_name", value);
	}

	@Override public List<Item> getAttributes(List<String> values) {
		TableKeysAndAttributes attributesQuery = new TableKeysAndAttributes("attribute");
		attributesQuery.addHashOnlyPrimaryKeys("attribute_name", values.toArray());

		return dynamoDB.batchGetItem(attributesQuery).getTableItems().values().stream().flatMap(Collection::stream).collect(Collectors.toList());
	}

	@Override public void createAttribute(String attributeName, String dataType, String uiView) {
		Map<String, ExpectedAttributeValue> params = new HashMap<>();
		params.put("attribute_name", new ExpectedAttributeValue(false));

		Map<String, AttributeValue> item = new HashMap<>();
		item.put("attribute_name", new AttributeValue(attributeName));
		item.put("data_type", new AttributeValue(dataType));
		item.put("ui_view", new AttributeValue(uiView));

		PutItemRequest putItemRequest = new PutItemRequest()
				.withTableName("attribute")
				.withItem(item)
				.withExpected(params);
		client.putItem(putItemRequest);
	}

	@Override public void addOrUpdateAttributeValues(String attributeName, String locale, Map<String, Object> attributeValues) {

		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("attribute_name", attributeName)
				.withUpdateExpression("set " + locale + " = :lm")
				.withValueMap(Collections.singletonMap(":lm", attributeValues))
				.withReturnValues(ReturnValue.UPDATED_NEW);
		getTable().updateItem(updateItemSpec);
	}

	private Table getTable() {
		return dynamoDB.getTable("attribute");
	}
}
