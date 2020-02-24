package com.elsevier.id.hackathon.service;

import org.springframework.stereotype.Service;

public interface UserService {

	String getAttribute(String userId, String locale, String attributeName);
}
