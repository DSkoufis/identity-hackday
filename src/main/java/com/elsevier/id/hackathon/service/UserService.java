package com.elsevier.id.hackathon.service;

public interface UserService {

	String getAttribute(String userId, String locale, String attributeName);

	boolean createUser(String userId);
}
