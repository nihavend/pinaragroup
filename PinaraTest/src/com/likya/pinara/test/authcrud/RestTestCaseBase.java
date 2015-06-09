package com.likya.pinara.test.authcrud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

import junit.framework.TestCase;


public abstract class RestTestCaseBase extends TestCase {
	
	protected final static String pinaraUrl = "http://127.0.0.1:3000";
	
//	private final String FLEX_CTX = "flex";
//	private final String LOGVIEW_CTX = "logview";
//	public final static String GRAPHVIEW_CTX = "graphview";
//	public final static String RESTFUL_CTX = "flex/restsrvc";

	protected String httpPost(String requestUrl, String urlParameters) {
		
		// String urlParameters  = "param1=a&param2=b&param3=c";
		// String request        = "http://example.com/index.php";
		
		StringBuffer retStr = new StringBuffer();
		
		try {
			
			String name = "pinara";
			String password = "pinara";

			String authString = name + ":" + password;
			// System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			// System.out.println("Base64 encoded auth string: " + authStringEnc);
			
			URL url = new URL(requestUrl);
		    URLConnection urlConnection = url.openConnection();
		    urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
		    urlConnection.setDoOutput(true);
		    OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());

		    writer.write(urlParameters);
		    writer.flush();
		    String line;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		    
		    while ((line = reader.readLine()) != null) {
		      // System.out.println(line);
		    	retStr.append(line);
		    }
		    writer.close();
		    reader.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retStr.toString();
	}
}
