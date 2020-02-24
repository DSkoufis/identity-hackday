package com.elsevier.id.hackathon.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.elsevier.id.hackathon.domain.Attribute;
import com.elsevier.id.hackathon.repository.AttributesDAO;

@Service
public class AttributeServiceImpl implements AttributeService {

	private AttributesDAO attributesDAO;

	public AttributeServiceImpl(AttributesDAO attributesDAO) {
		this.attributesDAO = attributesDAO;
	}

	@Override public Object getAttributeValues(String locale, String attributeName) {
		return null;
	}

	@Override public boolean createAttribute(String attributeName, String dataType, String uiView) {
		try {
			attributesDAO.createAttribute(attributeName, dataType, uiView);
			return true;
		} catch (AmazonServiceException e) {
			return false;
		}	}

	@Override public void addOrUpdateAttributeValues(String attributeName, String locale, Map<Long, Object> attributeValues) {

	}
}
