package com.likya.pinara.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.likya.myra.jef.utils.JobQueueOperations;
import com.likya.pinara.gui.WebManager;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class PinaraRestHandler implements HttpHandler {
	
	public abstract byte [] doWork(boolean isPost, String uriTxt, InputStream inputStream) throws IOException;

	final public void handle(HttpExchange httpExchange) throws IOException {
		
		OutputStream os;
		
		URI myUri = httpExchange.getRequestURI();
		
		// String uriTxt = myUri.toString();
		String uriTxt = myUri.getPath();
		
		String query = myUri.getQuery();
		boolean hiddenGet = false;
		if (query != null && query.split("=").length > 1 && query.split("=")[1].equals("GET")) {
			hiddenGet = true;
		}
		
		uriTxt = uriTxt.replace("/" + WebManager.RESTFUL_CTX + "/", "");

		byte responseBytes[];

		boolean isPost = false;
		
		if (!hiddenGet && httpExchange.getRequestMethod().equals("POST")) {
			// responseBytes = RestParser.parsePost(uriTxt, httpExchange.getRequestBody());
			isPost = true;
		} // else {
			// responseBytes = RestParser.parse(uriTxt);
		// }
		
		responseBytes = doWork(isPost, uriTxt, httpExchange.getRequestBody());
		
		httpExchange.sendResponseHeaders(200, responseBytes.length);
		os = httpExchange.getResponseBody();
		os.write(responseBytes);
		os.close();
		
	}
	
	public void handleJson(HttpExchange httpExchange) throws IOException {

		OutputStream os;
		String response = null;

		URI myUri = httpExchange.getRequestURI();

		String uriTxt = myUri.toString();

		uriTxt = uriTxt.replace("/" + WebManager.RESTFUL_CTX + "/", "");

		byte outputByteArray[] = null;

		try {
			response = evaluateAction(uriTxt);
		} catch (PinaraAuthenticationException e) {
			e.printStackTrace();
			response = e.getMessage();
		}

		outputByteArray = response.getBytes("utf-8");

		httpExchange.sendResponseHeaders(200, outputByteArray.length);
		os = httpExchange.getResponseBody();
		os.write(outputByteArray);
		os.close();
	}

	private String evaluateAction(String command) throws PinaraAuthenticationException {

		String respMsg ="Bad command or file name :)";
		boolean respBool = false;

		respMsg = new JsonParser().parse("{\"" + respBool + "\":\"" + respMsg + "\"}").toString();
		
		if (command == null || "".equals(command)) {
			return respMsg;
		}

		String cmd[] = command.split("/");

		switch (cmd[0]) {

		case RestParser.CMD_JOBLIST:
			StateName.Enum filterStates[] = { StateName.RUNNING, StateName.CANCELLED, StateName.FAILED, StateName.FINISHED, StateName.PENDING };
			Collection<AbstractJobType> jobList = PinaraAppManagerImpl.getInstance().getJobList(filterStates);
			try {
				respMsg = new Gson().toJson(JobQueueOperations.getKeyList(jobList));
				respMsg = new JsonParser().parse("{\"result\" : \"" + respBool + "\", \"message\":" + respMsg + "}").toString();
				respBool = true;
			} catch (Throwable t) {
				t.printStackTrace();
				respMsg = t.getMessage();
			}
			break;
			
		case RestParser.CMD_JOBDISABLE:
			if (cmd.length == 2) {
				PinaraAppManagerImpl.getInstance().disableJob(cmd[1]);
				respMsg = "ok";
				respBool = true;
				respMsg = new JsonParser().parse("{\"" + respBool + "\":\"" + respMsg + "\"}").toString();
			}
			break;

		default:
			break;
		}
		
		return respMsg;
	}
}
