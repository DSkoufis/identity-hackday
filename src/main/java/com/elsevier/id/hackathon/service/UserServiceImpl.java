package com.elsevier.id.hackathon.service;

import org.springframework.stereotype.Service;

import com.elsevier.id.hackathon.repository.AttributesDAO;
import com.elsevier.id.hackathon.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	private AttributesDAO attributesDAO;
	private UserDAO       userDAO;
	private AttributeTypeService attributeTypeService;

	public UserServiceImpl(AttributesDAO attributesDAO, UserDAO userDAO, AttributeTypeService attributeTypeService) {
		this.attributesDAO = attributesDAO;
		this.userDAO = userDAO;
		this.attributeTypeService = attributeTypeService;
	}

	@Override
	public String getAttribute(String userId, String locale, String attributeName) {
		return userDAO.findAttributeByUser(userId, locale, attributeName).toString();
	}

	@Override
	public boolean createUser(String userId) {
		try {
			userDAO.createUser(userId);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override public String addOrUpdateAttribute(String userId, String locale, String attributeName, Object attributeValue) {
		return null;
	}
}
