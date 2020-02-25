package com.elsevier.id.hackathon.domain;

import java.util.Collections;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;

public class Attribute {

	private final String              locale;
	private final String              name;
	private final String              uiView;
	private final Map<String, Object> localeAttributes;

	public Attribute(Item item, String locale) {
		this.name = item.getString("attribute_name");
		this.uiView = item.getString("ui_view");
		this.locale = locale;

		Map<String, Object> localeValues = Collections.emptyMap();
		if (item.isPresent(locale)) {
			localeValues = item.getMap(locale);
		}
		this.localeAttributes = localeValues;
	}

	public Attribute(String locale, String name, String view, Map<String, Object> atts) {
		this.locale = locale;
		this.name = name;
		this.uiView = view;
		this.localeAttributes = atts;
	}

	public Map<String, Object> getAttributes() {
		return localeAttributes;
	}

	public String getLocale() {
		return locale;
	}

	public String getName() {
		return name;
	}

	public String getUiView() {
		return uiView;
	}
}
