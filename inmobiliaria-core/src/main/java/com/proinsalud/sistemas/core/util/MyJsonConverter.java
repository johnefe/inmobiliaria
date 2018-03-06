package com.proinsalud.sistemas.core.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Andres Santacruz
 * @datetime 31/01/2018 - 7:16:54 a. m.
 *
 */
public class MyJsonConverter extends ObjectMapper {

	private static final long serialVersionUID = 3013896337280562731L;

	public MyJsonConverter() {
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//		// Ignore null values when writing json.
//		configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
//		setSerializationInclusion(Include.NON_NULL);
//
//		// Write times as a String instead of a Long so its human readable.
//		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		
		
		setSerializationInclusion(Include.NON_EMPTY); 
		setSerializationInclusion(Include.NON_NULL);
	}
}
