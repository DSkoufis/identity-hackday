package com.elsevier.id.hackathon.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.elsevier.id.hackathon.domain.Attribute;

public interface AttributesDAO {

	Item getAttribute(String value);

	void createAttribute(String attributeName, String dataType, String uiView);

	void addOrUpdateAttributeValues(String attributeName, String locale, Map<Long, Object> attributeValues);

}
