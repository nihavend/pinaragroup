package com.likya.pinara.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.likya.pinara.gui.WebManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GraphViewHandler implements HttpHandler {

	public void handle(HttpExchange httpExchange) throws IOException {

		OutputStream os;

		URI myUri = httpExchange.getRequestURI();
		
		String uriTxt = myUri.toString();
		
		uriTxt = uriTxt.replace("/" + WebManager.GRAPHVIEW_CTX + "/", "");
		
		byte responseBytes[] = GraphViewRestParser.parse(uriTxt);
		
		httpExchange.sendResponseHeaders(200, responseBytes.length);
		os = httpExchange.getResponseBody();
		os.write(responseBytes);
		os.close();
	}
}
