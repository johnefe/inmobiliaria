package com.proinsalud.sistemas.web.digital_turn;

import java.util.HashMap;

/**
 * @author Andres Santacruz
 * @datetime 20/02/2018 - 9:36:05 a. m.
 *
 */
public class Refresh {
	public static HashMap<Long, Long> hashMap = new HashMap<>();
	public static String text;

	public static void setValue(Long id, Long date) {
		hashMap.put(id, date);
	}

	public static Long getValue(Long id) {
		return (Long) hashMap.get(id);
	}

	public static String getText() {
		return text;
	}

	public static void setText(String text) {
		Refresh.text = text;
	}

}
