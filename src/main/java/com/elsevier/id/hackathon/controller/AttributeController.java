package com.elsevier.id.hackathon.controller;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elsevier.id.hackathon.domain.CreateAttribute;
import com.elsevier.id.hackathon.service.AttributeService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

	private AttributeService attributeService;

	public AttributeController(AttributeService attributeService) {
		this.attributeService = attributeService;
	}

	@GetMapping(value = "/{attribute_name}/{locale}", produces = "application/json")
	public ResponseEntity<String> getAttributeValues(
			@PathVariable("attribute_name") String attributeName,
			@PathVariable("locale") String locale,
			HttpServletResponse response) {

		return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*")
				.body(new Gson().toJson(attributeService.getAttributeValues(locale, attributeName)));

	}

	@PostMapping("/{attribute_name}")
	public boolean createAttribute(
			@PathVariable("attribute_name") String attributeName,
			@RequestBody CreateAttribute createAttribute) {
		return attributeService.createAttribute(attributeName, createAttribute.getDataType(), createAttribute.getUiView());
	}

	@PutMapping("/{attribute_name}/{locale}")
	public void addOrUpdateAttributeValues(@PathVariable("attribute_name") String attributeName,
			@PathVariable("locale") String locale,
			@RequestParam("display_name") String displayName,
			@RequestBody Map<String, Object> attributeValues) {

		attributeService.addOrUpdateAttributeValues(attributeName, locale, displayName, attributeValues);
	}

}
