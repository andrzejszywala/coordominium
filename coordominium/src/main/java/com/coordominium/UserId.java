package com.coordominium;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

public class UserId {
	public static String get(HttpServletRequest request) {
		Principal userPrincipal = request.getUserPrincipal();
		if (userPrincipal != null) {
			return userPrincipal.getName();
		} else if (System.getProperty("developmentUser") != null) {
			return System.getProperty("developmentUser");
		}
		return "edf09f18-347d-4336-bc6a-9224d31a5e09";
	}
}
