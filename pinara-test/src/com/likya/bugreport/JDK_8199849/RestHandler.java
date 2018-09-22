package com.likya.bugreport.JDK_8199849;

import java.io.IOException;
import java.io.OutputStream;

import com.likya.pinara.utils.BasicAuthenticationInfo;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RestHandler implements HttpHandler {
	

	final public void handle(HttpExchange httpExchange) throws IOException {
		
		OutputStream os;
		
		String [] loginInfo = BasicAuthenticationInfo.resolve(httpExchange);
		
		byte responseBytes[] = "<message><result>OK</result><desc>EveryThingIsOk</desc></message>".getBytes();
		
		httpExchange.sendResponseHeaders(200, responseBytes.length);
		os = httpExchange.getResponseBody();
		os.write(responseBytes);
		os.close();
		
	}
	
}
