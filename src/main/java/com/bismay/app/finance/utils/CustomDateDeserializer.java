package com.bismay.app.finance.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomDateDeserializer extends StdDeserializer<Date> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomDateDeserializer() {
		this(null);
	}

	public CustomDateDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
		String date = jsonparser.getText();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
	}
}
