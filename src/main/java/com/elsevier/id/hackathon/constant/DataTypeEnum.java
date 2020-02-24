package com.elsevier.id.hackathon.constant;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public enum DataTypeEnum {

	LIST {
		@Override public Object convert(String value) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(value, List.class);
			} catch (JsonProcessingException e) {
				return Collections.emptyList();
			}
		}
	},
	STRING {
		@Override public Object convert(String value) {
			return value;
		}
	},
	DATE {
		@Override public Object convert(String value) {
			return new Gson().fromJson(value, Instant.class);
		}
	},
	MAP {
		@Override public Object convert(String value) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(value, Map.class);
			} catch (JsonProcessingException e) {
				return Collections.emptyList();
			}
		}
	},
	BOOLEAN {
		@Override public Object convert(String value) {
			return Boolean.valueOf(value);
		}
	},
	NUMBER {
		@Override public Object convert(String value) {
			return new Gson().fromJson(value, Long.class);
		}
	};

	public abstract Object convert(String value);
}