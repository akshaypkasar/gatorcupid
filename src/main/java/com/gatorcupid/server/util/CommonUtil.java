package com.gatorcupid.server.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CommonUtil {
	
	/*
	 * Method which converts a string into a MD5 hash.
	 */
	public static String getMD5Hash(String inputString) {
		return DigestUtils.md5Hex(inputString);
	}

}
