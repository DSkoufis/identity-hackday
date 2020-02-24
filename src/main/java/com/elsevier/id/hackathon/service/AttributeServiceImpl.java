package com.elsevier.id.hackathon.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.elsevier.id.hackathon.repository.AttributesDAO;

@Service
public class AttributeServiceImpl implements AttributeService {

	private AttributesDAO attributesDAO;

	public AttributeServiceImpl(AttributesDAO attributesDAO) {
		this.attributesDAO = attributesDAO;
	}

	@Override public Map<String, Object> getAttributeValues(String locale, String attributeName) {
		Item attribute = attributesDAO.getAttribute(attributeName);

		Map<String, Object> localeValues = Collections.emptyMap();
		if (attribute.isPresent(locale)) {
			localeValues = attribute.getMap(locale);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("attribute_name", attribute.getString("attribute_name"));
		response.put("data_type", attribute.getString("data_type"));
		response.put("ui_view", attribute.getString("ui_view"));
		response.put("values", localeValues);

		return response;
	}

	@Override public boolean createAttribute(String attributeName, String dataType, String uiView) {
		try {
			attributesDAO.createAttribute(attributeName, dataType, uiView);
			return true;
		} catch (AmazonServiceException e) {
			return false;
		}
	}

	@Override public void addOrUpdateAttributeValues(String attributeName, String locale, Map<Long, Object> attributeValues) {

	}
}
