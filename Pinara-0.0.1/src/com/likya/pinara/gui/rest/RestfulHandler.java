package com.likya.pinara.gui.rest;

import java.io.IOException;
import java.io.InputStream;

import com.likya.myra.jef.model.InstanceNotFoundException;
import com.likya.pinara.gui.WebManager;
import com.likya.pinara.model.User;

public class RestfulHandler extends PinaraRestHandler {

	// public static final String CONTENT_ROOT = "/flexroot";
	
	public byte [] doWork(User reqUserInfo, boolean isPost, String uriTxt, InputStream inputStream) throws IOException, InstanceNotFoundException {

		byte responseBytes[];
		
		if (isPost) {
			responseBytes = RestParser.parsePost(reqUserInfo, uriTxt, inputStream /*httpExchange.getRequestBody()*/);
		} else {
			responseBytes = RestParser.parse(reqUserInfo, uriTxt);
		}
		
		return responseBytes;
		
	}

	@Override
	public String removeBaseUrl(String uriTxt) throws IOException {
		return uriTxt.replace("/" + WebManager.RESTFUL_CTX + "/", "");
	}

//	public void handle(HttpExchange httpExchange) throws IOException {
//
//		OutputStream os;
//		
//		URI myUri = httpExchange.getRequestURI();
//		
//		// String uriTxt = myUri.toString();
//		String uriTxt = myUri.getPath();
//		
//		String query = myUri.getQuery();
//		boolean hiddenGet = false;
//		if (query != null && query.split("=").length > 1 && query.split("=")[1].equals("GET")) {
//			hiddenGet = true;
//		}
//		
//		uriTxt = uriTxt.replace("/" + WebManager.RESTFUL_CTX + "/", "");
//
//		byte responseBytes[];
//				
//		if (!hiddenGet && httpExchange.getRequestMethod().equals("POST")) {
//			responseBytes = RestParser.parsePost(uriTxt, httpExchange.getRequestBody());
//		} else {
//			responseBytes = RestParser.parse(uriTxt);
//		}
//		
//		httpExchange.sendResponseHeaders(200, responseBytes.length);
//		os = httpExchange.getResponseBody();
//		os.write(responseBytes);
//		os.close();
//		
//	}
	
}
