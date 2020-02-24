package com.elsevier.id.hackathon.service;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.elsevier.id.hackathon.repository.AttributesDAO;
import com.elsevier.id.hackathon.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	private AttributesDAO attributesDAO;
	private UserDAO       userDAO;

	public UserServiceImpl(AttributesDAO attributesDAO, UserDAO userDAO) {
		this.attributesDAO = attributesDAO;
		this.userDAO = userDAO;
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
		} catch (AmazonServiceException e) {
			return false;
		}
	}
}
