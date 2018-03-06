package com.proinsalud.sistemas.core.util;

import java.io.Serializable;

/**
 * @author Andres Santacruz
 * @datetime 13/02/2018 - 9:26:55 a. m.
 *
 */
public class Validation implements Serializable{

	private static final long serialVersionUID = 5639113429370144941L;
	
	public static boolean valOk(String arg) {
		return arg != null && !arg.isEmpty();
	}

}
