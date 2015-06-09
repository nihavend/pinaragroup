package com.likya.pinara.gui.rest;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.likya.pinara.Pinara;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FlexHandler implements HttpHandler {

	public static final String CONTENT_ROOT = "flexroot";

	public Map<String, String> parseRequest(HttpExchange arg0) throws IOException {
		HashMap<String, String> result = new HashMap<String, String>();
		String query = arg0.getRequestURI().getQuery();
		//parse request
		String[] request = query.split("&");

		for (int i = 0; i < request.length; i++) {
			String[] pieces = request[i].split("=");
			if (pieces.length == 2) {
				result.put(pieces[0], java.net.URLDecoder.decode(pieces[1], "UTF-8"));
			} else {
				// System.err.println(pieces);
				Pinara.getLogger().debug(pieces);
			}
		}
		return result;
	}

	public void handle(HttpExchange httpExchange) throws IOException {

		// InputStream inputStream;
		OutputStream os;
		// ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
		String response = null;

		//		httpExchange.getPrincipal();
		//		
		//		Map<String,String> request = parseRequest(httpExchange);
		//        if(request.containsKey("session")) {
		//        	System.err.println("Session : " + request.get("session"));
		//        }

		// Principal principal = request.getUserPrincipal();

		URI myUri = httpExchange.getRequestURI();
		// inputStream = httpExchange.getRequestBody();
		// String query = httpExchange.getRequestURI().getQuery();

		String fileName = "index.template.html";

		if (myUri.getPath().length() != myUri.getPath().lastIndexOf('/') + 1) {
			fileName = myUri.getPath().substring(myUri.getPath().lastIndexOf('/') + 1);
		}

		Pinara.getLogger().debug("uri : " + httpExchange.getRequestURI());
		// System.err.println("uri : " + httpExchange.getRequestURI());
		Pinara.getLogger().debug("Dosya adı : " + fileName);
		// System.err.println("Dosya adı : " + fileName);

		String ipAddress = null;

		if (httpExchange.getRemoteAddress() != null && httpExchange.getRemoteAddress().getAddress() != null) {
			ipAddress = httpExchange.getRemoteAddress().getAddress().getHostAddress();
			Pinara.getLogger().info(Pinara.getMessage("ViewHandler.0") + ipAddress); //$NON-NLS-1$
		} else {
			Pinara.getLogger().info(Pinara.getMessage("ViewHandler.1")); //$NON-NLS-1$
		}

		byte outputByteArray[] = null;

		if (response == null) {
			try {
				String extension = "";
				int i = fileName.lastIndexOf('.');
				if (i > 0) {
					extension = fileName.substring(i + 1);
				}
				if ("swf".equals(extension.toLowerCase())) {
					
					InputStream fis = null;
					String tmpPath = CONTENT_ROOT + "/" + fileName;
					if(FlexHandler.class.getResource("/" + tmpPath) == null) {
						Pinara.getLogger().info("Trying from : " + tmpPath);
						fis = new FileInputStream(tmpPath);
					} else {
						Pinara.getLogger().info("Loading from : " + FlexHandler.class.getResource("/" + tmpPath).getPath());
						fis = FlexHandler.class.getResourceAsStream("/" + tmpPath);
					}
					
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					int data;
					while ((data = fis.read()) != -1) {
						byteArrayOutputStream.write(data);
					}
					try {
						fis.close();
					} catch (IOException e) {
					}
					outputByteArray = byteArrayOutputStream.toByteArray();
				} else {
					response = buildWebPage(fileName);
					outputByteArray = response.getBytes("utf-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		httpExchange.sendResponseHeaders(200, outputByteArray.length);
		os = httpExchange.getResponseBody();
		os.write(outputByteArray);
		os.close();
	}

	public String buildWebPage(String fileName) throws Exception {
		fileName = CONTENT_ROOT + "/" + fileName;
		return getSection(fileName);
	}

	public static String getSection(String fileName) {

		InputStream fis = null;
		StringBuffer outputBuffer = new StringBuffer();

		try {
			if(FlexHandler.class.getResource(fileName) == null) {
				Pinara.getLogger().info("Trying from : " + fileName);
				fis = new FileInputStream(fileName);
			} else {
				Pinara.getLogger().info("Loading from : " + FlexHandler.class.getResource(fileName).getPath());
				fis = FlexHandler.class.getResourceAsStream(fileName);
			}
			
			InputStreamReader inputStreamReader = new InputStreamReader(fis);
			
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String bufferString = null;

			while ((bufferString = bufferedReader.readLine()) != null) {
				outputBuffer.append(bufferString);
				outputBuffer.append("\n"); //$NON-NLS-1$
			}
			fis.close();
		} catch (FileNotFoundException fnfex) {
			fnfex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputBuffer.toString();
	}

	public String getCssLink(String fileName) {
		return "<link rel=\"stylesheet\" href=\"" + fileName + "\" />\n";
	}

	public String getJsLink(String fileName) {
		return "<script type=\"text/javascript\" src=\"" + fileName + "\"></script>\n";
	}

	public String getJsContent() {

		String str = "<!--BEGIN jsContent ***************************************************************************************** -->\n"; //$NON-NLS-1$
		str += getSection(CONTENT_ROOT + "/jsContent.js"); //$NON-NLS-1$
		str += "<!--END jsContent ***************************************************************************************** -->\n"; //$NON-NLS-1$

		return str;
	}

	public String getCss(String fileName) {

		String str = "<!--BEGIN " + fileName + Pinara.getMessage("HtmlFileReaderV2.0"); //$NON-NLS-1$ //$NON-NLS-2$
		str += "<style type=\"text/css\">\n"; //$NON-NLS-1$
		str += getSection(fileName); //$NON-NLS-1$
		str += "</style>\n"; //$NON-NLS-1$
		str += "<!--END " + fileName + Pinara.getMessage("HtmlFileReaderV2.0"); //$NON-NLS-1$ //$NON-NLS-2$

		return str;
	}

	public String getJs(String fileName, String comment) {

		String str = "<!--BEGIN " + fileName + Pinara.getMessage("HtmlFileReaderV2.0"); //$NON-NLS-1$ //$NON-NLS-2$
		str += "<script type=\"text/javascript\">\n"; //$NON-NLS-1$
		if (comment != null) {
			str += comment;
		}
		str += getSection(fileName); //$NON-NLS-1$
		str += "</script>\n"; //$NON-NLS-1$
		str += "<!--END " + fileName + Pinara.getMessage("HtmlFileReaderV2.0"); //$NON-NLS-1$ //$NON-NLS-2$

		return str;
	}

	public String getJs(String fileName) {
		return getJs(fileName, null);
	}
}
