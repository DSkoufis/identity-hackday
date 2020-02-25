package com.elsevier.id.hackathon.service;

import java.util.Map;

public interface AttributeService {
	Map<String, Object> getAttributeValues(String locale, String attributeName);

	boolean createAttribute(String attributeName, String dataType, String uiView);

	void addOrUpdateAttributeValues(String attributeName, String locale, String displayName, Map<String, Object> attributeValues);
}
