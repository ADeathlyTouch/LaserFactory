package com.collinriggs.laserfactory.util;

public class StringUtils {

	public static String captializeFirstLetter(String string) {
		return (string.toUpperCase().substring(0, 1) + string.toLowerCase().substring(1));
	}
	
}
