package com.likya.pinara.gui.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.likya.myra.jef.model.InstanceNotFoundException;
import com.likya.pinara.gui.WebManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GraphViewHandler implements HttpHandler {

	public void handle(HttpExchange httpExchange) throws IOException {

		OutputStream os;

		URI myUri = httpExchange.getRequestURI();
		
		// String uriTxt = myUri.toString();
		String uriTxt = myUri.getPath();
		
		uriTxt = uriTxt.replace("/" + WebManager.GRAPHVIEW_CTX + "/", "");
		
		byte responseBytes[] = null;
		try {
			responseBytes = GraphViewRestParser.parse(uriTxt);
		} catch (InstanceNotFoundException e) {
			responseBytes = "<message><result>NOK</result><desc>InstanceNotFoundException</desc></message>".getBytes();
		}
		
		httpExchange.sendResponseHeaders(200, responseBytes.length);
		os = httpExchange.getResponseBody();
		os.write(responseBytes);
		os.close();
	}
}
