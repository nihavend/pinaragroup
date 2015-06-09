package com.likya.pinara.gui.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.likya.commons.utils.FileUtils;
import com.likya.pinara.Pinara;
import com.likya.pinara.utils.PinaraFileUtils;
import com.likya.pinara.utils.xml.mappers.LogViewResponseMapper;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class FileViewHandler implements HttpHandler {

	private final String JOBDETAIL_CTX = "jobdetail";
	private final String MSGDETAIL_CTX = "msgdetail";

	private HttpContext httpContext;
	private String query;

	public enum FileTypeInfo {

		HTML(10), NATIVEXML(20), TOXML(20);

		public int value;

		private FileTypeInfo(int value) {
			this.value = value;
		}

	}

	protected class ViewTypeInfo {

		String viewTypeText;
		String viewSubTypeText;
		String[] queryParamArr;

		public ViewTypeInfo(String viewTypeText, String viewSubTypeText, String[] queryParamArr) {
			super();
			this.viewTypeText = viewTypeText;
			this.viewSubTypeText = viewSubTypeText;
			this.queryParamArr = queryParamArr;
		}

	}

	public abstract String handleView(ViewTypeInfo viewTypeInfo);

	public void handle(HttpExchange httpExchange) throws IOException {

		OutputStream os;
		String response = null;

		httpContext = httpExchange.getHttpContext();

		query = httpExchange.getRequestURI().getQuery();

		ViewTypeInfo viewTypeInfo;

		Pinara.getLogger().warn("guery of url: [" + query + "]");

		if (query != null && query.length() > 0 && (viewTypeInfo = getViewTypeInfo(query)) != null) { // means there is no ? in url
			try {
				response = handleView(viewTypeInfo);
				if(response == null) {
					response = returnFault(-1, "File not found !");
				}
			} catch (Throwable e) {
				e.printStackTrace();
				response = returnFault(e);
			}
		} else {
			response = returnFault(-1, "Invalid url identifier : [" + httpContext.getPath() + " query:" + query + "]");
		}

		byte[] responseBytes = response.getBytes("UTF-8");

		int contentLength = responseBytes.length;

		httpExchange.sendResponseHeaders(200, contentLength);

		os = httpExchange.getResponseBody();
		os.write(responseBytes);
		os.close();

	}
	
	protected String returnResult(String txt, ArrayList<Long> fileSize, String logDescriptor, FileTypeInfo fileTypeInfo) {
		return LogViewResponseMapper.resultMapper(0, txt, fileSize, logDescriptor, fileTypeInfo);
	}

	protected String returnFault(Throwable throwable) {
		return LogViewResponseMapper.faultMapper(-99, PinaraFileUtils.stackTrace(throwable));
	}
	
	protected String returnFault(int code, String txt) {
		return LogViewResponseMapper.faultMapper(code, txt);
	}
	
	protected String returnFault() {
		return LogViewResponseMapper.faultMapper(-1, "");
	}
	
//	protected String returnFault(int code, String txt) {
//		if (txt != null && txt.length() > 0) {
//		} else {
//			txt = "Invalid url identifier : [" + httpContext.getPath() + " query:" + query + "]";
//		}
//
//		return LogViewResponseMapper.faultMapper(code, txt);
//	}
//
//	protected String returnFault(Throwable throwable) {
//		return returnFault(-1, PinaraFileUtils.stackTrace(throwable));
//	}
//
//	protected String returnFault() {
//		return returnFault(-1, "");
//	}

	protected ViewTypeInfo getViewTypeInfo(String query) {

		ViewTypeInfo viewTypeInfo = null;
		
		if(query.indexOf("_method=GET") >= 0) {
			query = query.replace("_method=GET&", "");
		}
		
		String[] queryParamArr = query.split("&");

		String viewTypeText = null;
		String viewSubTypeText = null;

		if (queryParamArr.length > 0 && queryParamArr[0].split("=").length > 1) {
			viewTypeText = queryParamArr[0].split("=")[0];
			viewSubTypeText = queryParamArr[0].split("=")[1];
			viewTypeInfo = new ViewTypeInfo(viewTypeText, viewSubTypeText, queryParamArr);
		}

		return viewTypeInfo;
	}

	public String getLimited(String[] queryParamArr, String viewFile, FileTypeInfo fileTypeInfo) throws Throwable {

		String response = null;
		
		if(!PinaraFileUtils.checkFile(viewFile)) {
			return response;
		}
		
		int beginOffset = -1;
		int endOffset = -1;

		if (queryParamArr != null) {
			if (queryParamArr.length > 1 && queryParamArr[1].split("=").length > 0) {
				String beginOffStr = queryParamArr[1].split("=")[1];
				beginOffset = Integer.parseInt(beginOffStr);
			}

			if (queryParamArr.length > 2 && queryParamArr[2].split("=").length > 0) {
				String endOffStr = queryParamArr[2].split("=")[1];
				endOffset = Integer.parseInt(endOffStr);
			}
		}

		switch (fileTypeInfo) {

		case NATIVEXML:
			response = HtmlPages.getXmlPageDataControlled(viewFile, false, null, beginOffset, endOffset, fileTypeInfo);
			break;
			
		case TOXML:
			response = HtmlPages.getXmlPageDataControlled(viewFile, false, null, beginOffset, endOffset, fileTypeInfo);
			break;

		case HTML:
			response = webPager(viewFile, null/*jobname*/, beginOffset, endOffset);
			break;

		default:
			break;
		}

		return response;
	}

	public void handleOld(HttpExchange httpExchange, FileTypeInfo fileTypeInfo) throws Throwable {

		boolean isXml = false;

		OutputStream os;
		String response = null;

		HttpContext httpContext = httpExchange.getHttpContext();
		String query = httpExchange.getRequestURI().getQuery();
		String viewFile = null;

		Pinara.getLogger().warn(query);

		if (query != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(query, "=");
			String tokenStr = stringTokenizer.nextToken();
			if (tokenStr.equals("fname")) {
				String elementText = stringTokenizer.nextElement().toString();
				if (elementText.equals("tlosConfig")) {
					// response = TlosServer.getTlosParameters().getConfigFileContent();
					isXml = true;
					// TlosParameters.setRequestedFileName(TlosServer.getConfigFileName());
				} else if (elementText.equals("tlosScenario")) {
					// response = TlosServer.getTlosParameters().getScenarioFileContent();
					// isXml = true;
					// TlosParameters.setRequestedFileName(TlosServer.getTlosParameters().getScenarioFile());
				} else if (elementText.equals("myraTrace")) {
					// viewFile = "TlosTrace.log";
					viewFile = "myraTrace.log";
					// response = webPager(viewFile);
					response = HtmlPages.getXMLPageData(viewFile, fileTypeInfo);
				} else if (elementText.equals("tlosLog")) {
					// viewFile = TlosServer.getTlosParameters().getLogFile();
					response = webPager(viewFile);
				} else if (elementText.equals("tlos.xsl")) {
					viewFile = "tlos.xsl";
					response = xslPager(viewFile);
					isXml = true;
				} else if (elementText.equals("license")) {
					try {
						response = FileUtils.readFile("lisans.htm").toString();
					} catch (Exception e) {
						response = "\"lisans.htm\"" + Pinara.getMessage("FileViewHandler.6");
					}

				} else {
					if (httpContext.getPath().equals("/" + JOBDETAIL_CTX)) {
						// TODO viewFile = TlosServer.getJobQueue().get(elementText).getJobProperties().getJobCommand();
						response = webPager(viewFile);
					} else {
						String[] paramArr = query.split("&");
						if (paramArr.length > 0 && paramArr[0].split("=").length > 0) {
							elementText = paramArr[0].split("=")[1];
						}

						// TODO viewFile = TlosServer.getJobQueue().get(elementText).getJobProperties().getLogFilePath();

						int beginOff = -1;
						int endOff = -1;

						if (paramArr.length > 1 && paramArr[1].split("=").length > 0) {
							String beginOffStr = paramArr[1].split("=")[1];
							beginOff = Integer.parseInt(beginOffStr);
						}

						if (paramArr.length > 2 && paramArr[2].split("=").length > 0) {
							String endOffStr = paramArr[2].split("=")[1];
							endOff = Integer.parseInt(endOffStr);
						}
						response = webPager(viewFile, elementText, beginOff, endOff);
					}

				}
			} else if (tokenStr.equals("jobName")) {
				//String elementText = stringTokenizer.nextElement().toString();
				if (httpContext.getPath().equals("/" + MSGDETAIL_CTX)) {
					StringBuffer bufferText = new StringBuffer();
					// TODO if(TlosServer.getJobQueue().get(elementText).getJobProperties().getMessageBuffer() != null) {
					// TODO 	bufferText.append(TlosServer.getJobQueue().get(elementText).getJobProperties().getMessageBuffer());
					// TODO } else {
					// TODO 	bufferText.append("");
					// TODO }

					response = HtmlPages.getBufferPage(bufferText);
				}
			}
		}
		byte[] responseBytes;
		if (isXml) {
			responseBytes = response.getBytes("UTF-8");
		} else {
			responseBytes = response.getBytes("UTF-8");
		}
		int contentLength = responseBytes.length;
		httpExchange.sendResponseHeaders(200, contentLength);
		os = httpExchange.getResponseBody();
		os.write(responseBytes);
		os.close();
	}

	public String webPager(String logFile, String jobName, int beginOffset, int endOffset) throws Throwable {
		return HtmlPages.getHtmlPageData(logFile, jobName, beginOffset, endOffset);
	}

	public String webPager(String logFile) {
		return HtmlPages.getHtmlPageData(logFile);
	}

	public String xmlPager(String logFile, FileTypeInfo fileTypeInfo) throws Throwable {
		return HtmlPages.getXMLPageData(logFile, fileTypeInfo);
	}

	public String xslPager(String xslFile) {
		StringBuilder buf;

		buf = new StringBuilder();

		InputStream inputStream = this.getClass().getResourceAsStream("/" + xslFile);

		if (inputStream == null) {
			return null;
		}

		StringBuffer stringBuffer = FileUtils.readXSLFile(inputStream, "");

		if (stringBuffer != null) {
			buf.append(stringBuffer);
		}

		return buf.toString();
	}

}
