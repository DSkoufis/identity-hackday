package com.elsevier.id.hackathon.repository;

public interface UserDAO {

	Object findAttributeByUser(String userId, String locale, String attributeName);

	void createUser(String userId);
}
