package com.elsevier.id.hackathon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elsevier.id.hackathon.domain.Attribute;
import com.elsevier.id.hackathon.service.AttributeService;

@Controller
@RequestMapping("/view/attr")
public class AttributeRenderController {

	private AttributeService attributeService;

	public AttributeRenderController(AttributeService attributeService) {
		this.attributeService = attributeService;
	}

	@GetMapping("/{locale}")
	public String getTemplateForAttribute(
			@PathVariable("locale") String locale,
			@RequestParam List<String> attrs,
			Model model) {

		List<Attribute> attributes = getAttrs(locale);//attributeService.getAttributes(locale, attrs);
		model.addAttribute("items", attributes);
		return "attributeTemplate";
	}

	private List<Attribute> getAttrs(String locale) {
		java.util.Map<String, Object> attrs = new java.util.HashMap<>();
		attrs.put("0", "Mr");
		attrs.put("1", "Mrs");
		Attribute one = new Attribute(locale, "Salutation", "dropdown", attrs);
		java.util.List<Attribute> attrss = new ArrayList<>();
		attrss.add(one);
		return attrss;
	}
}
