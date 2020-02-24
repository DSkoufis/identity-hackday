package com.elsevier.id.hackathon.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elsevier.id.hackathon.service.UserService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/{user_id}/{locale}/{attribute_name}", produces = "application/json")
	public String getAttributeByUserId(
			@PathVariable("user_id") String userId,
			@PathVariable("locale") String locale,
			@PathVariable("attribute_name") String attributeName) {
		return new Gson().toJson(userService.getAttribute(userId, locale, attributeName));
	}

	@PutMapping(value = "/{user_id}/{locale}/{attribute_name}")
	public void addOrUpdateAttribute(
			@PathVariable("user_id") String userId,
			@PathVariable("locale") String locale,
			@PathVariable("attribute_name") String attributeName,
			@RequestBody String attributeValue) {

		userService.addOrUpdateAttribute(userId, locale, attributeName, attributeValue);
	}


	@PostMapping(value = "/{user_id}")
	public String createNewUser(@PathVariable("user_id") String userId) {
		boolean result = userService.createUser(userId);
		return new Gson().toJson(result);
	}
}
