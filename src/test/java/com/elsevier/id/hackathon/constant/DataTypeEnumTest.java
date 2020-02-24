package com.elsevier.id.hackathon.constant;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class DataTypeEnumTest {

	@Test
	void convert_number_from_string() {
		Long num = (Long)DataTypeEnum.NUMBER.convert("2");
		assertEquals(num, 2L);
	}
}