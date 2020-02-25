package com.elsevier.id.hackathon.service;

import java.util.List;
import java.util.Map;

import com.elsevier.id.hackathon.domain.Attribute;

public interface AttributeService {
	Map<String, Object> getAttributeValues(String locale, String attributeName);

	List<Attribute> getAttributes(String locale, List<String> attributes);

	boolean createAttribute(String attributeName, String dataType, String uiView);

	void addOrUpdateAttributeValues(String attributeName, String locale, String displayName, Map<String, Object> attributeValues);
}
