package com.elsevier.id.hackathon.repository;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.document.Item;

public interface AttributesDAO {

	Item getAttributeByValue(String value);
}
