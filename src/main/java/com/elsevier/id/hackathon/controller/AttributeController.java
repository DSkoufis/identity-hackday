package com.elsevier.id.hackathon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elsevier.id.hackathon.domain.CreateAttribute;
import com.elsevier.id.hackathon.service.AttributeService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

	private AttributeService attributeService;

	public AttributeController(AttributeService attributeService) {
		this.attributeService = attributeService;
	}

	@GetMapping("/{locale}/{attribute_name}")
	public JsonNode getAttributeValues(
			@RequestParam("locale") String locale,
			@RequestParam("attribute_name") String attributeName) {

		return null;
	}

	@PostMapping("/{attribute_name}")
	public boolean createAttribute(
			@PathVariable("attribute_name") String attributeName,
			@RequestBody CreateAttribute createAttribute) {
		return attributeService.createAttribute(attributeName, createAttribute.getDataType(), createAttribute.getUiView());
	}

}
