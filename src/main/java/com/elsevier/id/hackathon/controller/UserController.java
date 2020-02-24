package com.elsevier.id.hackathon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elsevier.id.hackathon.service.UserService;

@Controller
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
		return userService.getAttribute(userId, locale, attributeName);
	}
}
