package com.elsevier.id.hackathon.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.elsevier.id.hackathon.constant.DataTypeEnum;
import com.elsevier.id.hackathon.repository.AttributesDAO;

@Service
public class AttributeTypeServiceImpl implements AttributeTypeService {

	private AttributesDAO attributesDAO;
	private static final Map<String, DataTypeEnum> ATTRIBUTE_DATA_TYPE = new HashMap<>();

	public AttributeTypeServiceImpl(final AttributesDAO attributesDAO) {
		this.attributesDAO = attributesDAO;
	}

	@Override public DataTypeEnum getAttributeType(String attributeName) {
		if(!ATTRIBUTE_DATA_TYPE.containsKey(attributeName))  {
			final String dataTypeString = attributesDAO.getAttribute(attributeName).getString("data_type");
			final DataTypeEnum dataTypeEnum = DataTypeEnum.valueOf(dataTypeString);
			ATTRIBUTE_DATA_TYPE.put(attributeName, dataTypeEnum);
		}
		return ATTRIBUTE_DATA_TYPE.get(attributeName);
	}
}
