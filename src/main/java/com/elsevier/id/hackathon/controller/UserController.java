package com.elsevier.id.hackathon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/{user_id}/{locale}/{attribute_name}")
	public JsonNode getAttributeByUserId(@RequestParam("user_id") String userId,
										 @RequestParam("locale") String locale,
										 @RequestParam("attribute_name") String attributeName) {

		return null;
	}
}
