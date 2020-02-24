package com.elsevier.id.hackathon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
@RequestMapping("/attribute")
public class AttributeController {
	@GetMapping("/{locale}/{attribute_name}")
	public JsonNode getAttributeValues(
			@RequestParam("locale") String locale,
			@RequestParam("attribute_name") String attributeName) {

		return null;
	}
}
