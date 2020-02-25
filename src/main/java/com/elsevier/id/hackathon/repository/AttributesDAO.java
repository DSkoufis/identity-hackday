package com.elsevier.id.hackathon.repository;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;

public interface AttributesDAO {

	Item getAttribute(String value);

	List<Item> getAttributes(List<String> values);

	void createAttribute(String attributeName, String dataType, String uiView);

	void addOrUpdateAttributeValues(String attributeName, String locale, String displayName, Map<String, Object> attributeValues);

}
