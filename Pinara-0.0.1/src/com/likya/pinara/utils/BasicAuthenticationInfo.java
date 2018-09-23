package com.likya.pinara.utils;

import org.apache.commons.codec.binary.Base64;

import com.sun.net.httpserver.Headers;
//import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;

public class BasicAuthenticationInfo {

	public static String[] resolve(HttpExchange t) {
		
		//HttpContext context = t.getHttpContext();
		
		Headers rmap = (Headers) t.getRequestHeaders();
		/*
		 * look for auth token
		 */
		
		String auth = rmap.getFirst("Authorization");
		
		if (auth == null) {
			//Headers map = (Headers) t.getResponseHeaders();
			// map.set("WWW-Authenticate", "Basic realm=" + "\"" + realm + "\"");
			return null; // new Authenticator.Retry(401);
		}
		int sp = auth.indexOf(' ');
		if (sp == -1 || !auth.substring(0, sp).equals("Basic")) {
			return null; //new Authenticator.Failure(401);
		}
		byte[] b = Base64.decodeBase64(auth.substring(sp + 1));
		String userpass = new String(b);
		int colon = userpass.indexOf(':');
		String uname = userpass.substring(0, colon);
		String pass = userpass.substring(colon + 1);

		return new String[] { uname, pass };
	}
}
