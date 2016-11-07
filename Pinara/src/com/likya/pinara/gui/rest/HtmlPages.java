package com.likya.pinara.gui.rest;

import java.util.ArrayList;

import com.likya.pinara.Pinara;
import com.likya.pinara.gui.rest.FileViewHandler.FileTypeInfo;
import com.likya.pinara.utils.PinaraFileUtils;
import com.likya.pinara.utils.xml.mappers.LogViewResponseMapper;

public class HtmlPages {

	public static StringBuilder documentHeader() {
		return documentHeader(null);
	}

	public static StringBuilder documentHeader(String title) {

		StringBuilder documentHeader = new StringBuilder();

		if (title == null) {
			title = "Tlos Scheduler";
		}
		documentHeader.append("<html>");
		documentHeader.append("<head>");
		documentHeader.append("<title>" + title + "</title>");
		documentHeader.append("<META HTTP-EQUIV=\"CACHE-CONTROL\" CONTENT=\"NO-CACHE\">");
		documentHeader.append("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">\n");
		documentHeader.append("<style type=\"text/css\">");
		documentHeader.append("<!--");
		documentHeader.append(".style11 {font-size: 14mm;font-family: Georgia, \"Times New Roman\", Times, serif;}");
		documentHeader.append("-->");
		documentHeader.append("</style>");
		documentHeader.append("</head>");

		return documentHeader;
	}

	public static StringBuilder pageFooter() {

		StringBuilder footerValue = new StringBuilder();
		footerValue.append("<br/><br/>");

		footerValue.append("<HR  size=\"1\" COLOR=\"black\" WIDTH=\"80%\">");
		footerValue.append("<h6 align=\"center\">Her Hakki Saklidir (c) 2008 Likya Bilgi Teknolojileri ve Ilet. Hiz. Ltd. Sti.<br>");
		footerValue.append("<a href=\"http://www.likyateknoloji.com\" title=\"www.likyateknoloji.com\">www.likyateknoloji.com</a>");
		footerValue.append("&nbsp;&nbsp;<a href=\"mailto:bilgi@likyateknoloji.com\">bilgi@likyateknoloji.com</a></h6>");

		return footerValue;
	}

	public static String getHtmlPageData(String logFile) {
		return getHtmlPageData(logFile, true);
	}

	public static String getHtmlPageData(String logFile, String jobName, int beginOffset, int endOffset) throws Throwable {
		return getHtmlPageDataControlled(logFile, true, jobName, beginOffset, endOffset);
	}

	public static String getHtmlPageData(String logFile, boolean cleanEscapeChars) {

		StringBuilder buf;

		buf = new StringBuilder();
		buf.append(documentHeader());
		buf.append("<body>");
		buf.append("<p><pre>");

		StringBuffer stringBuffer = PinaraFileUtils.readTxtFile(logFile, "ERROR", cleanEscapeChars);
		if (stringBuffer != null) {
			String str = "<br/><font color=\"red\">" + Pinara.getMessage("FileViewHandler.0") + "</font>" + '\n';
			buf.append(str);
			buf.append(stringBuffer.toString());
			str = "<font color=\"red\">" + Pinara.getMessage("FileViewHandler.1") + "</font>" + '\n';
			buf.append(str);
		}

		buf.append(pageFooter());

		buf.append("</pre></p>");
		buf.append("</body></html>\n");

		buf.append("</form>");

		return buf.toString();
	}

	public static String getHtmlPageDataControlled(String logFile, boolean cleanEscapeChars, String jobName, int beginOffset, int endOffset) throws Throwable {

		StringBuilder buf;

		buf = new StringBuilder();
		buf.append(documentHeader());
		buf.append("<body>");
		buf.append("<p><pre>");

		buf.append("<form action=\"logdetail\">");

		ArrayList<Long> fileSize = new ArrayList<Long>();

		StringBuffer stringBuffer = getEndOfLog(fileSize, logFile, cleanEscapeChars, beginOffset, endOffset);

		if (stringBuffer != null) {

			buf.append("<input type=\"hidden\" name=\"fname\" value='" + jobName + "'><br>");
			buf.append("Dosya boyu : " + fileSize.get(0).toString() + " bytes<br>");
			buf.append("Başlangıç offset: <input type=\"number\" name=\"beginoff\" value='" + beginOffset + "'><br>");
			buf.append("Bitiş offset: <input type=\"number\" name=\"endoff\"  value='" + endOffset + "'><br>");
			buf.append("<input type=\"submit\" value=\"Güncelle\">");
			buf.append("<textarea id='limitedLogView' rows=\"30\" cols=\"120\">");
			buf.append(stringBuffer.toString());
			buf.append("</textarea>");

		}

		buf.append(pageFooter());

		buf.append("<script>");
		buf.append("var textarea = document.getElementById('limitedLogView');");
		buf.append("textarea.scrollTop = textarea.scrollHeight");
		buf.append("</script>");

		buf.append("</form>");

		buf.append("</pre></p>");
		buf.append("</body></html>\n");

		return buf.toString();
	}

	public static String getBufferPage(StringBuffer bufferText) {

		StringBuilder buf;

		buf = new StringBuilder();
		buf.append(documentHeader());
		buf.append("<body>");
		buf.append("<p><pre>");

		buf.append(bufferText);

		buf.append("</pre></p>");
		buf.append("</body></html>\n");

		return buf.toString();
	}

	public static String getXmlPageDataControlled(String logDescriptor, boolean cleanEscapeChars, String jobName, int beginOffset, int endOffset, FileTypeInfo fileTypeInfo) throws Throwable {

		int numOfPages = -1; // TODO Pinara.getInstance().getConfigurationManager().getPinaraConfig().get

		ArrayList<Long> fileSize = new ArrayList<Long>();

		StringBuffer stringBuffer = PinaraFileUtils.readXMLFile(fileSize, beginOffset, endOffset, logDescriptor, null, cleanEscapeChars, numOfPages);

		String buff = LogViewResponseMapper.resultMapper(0, stringBuffer.toString(), fileSize, logDescriptor, fileTypeInfo);

		return buff;

	}

	public static String getXMLPageData(String logFile, FileTypeInfo fileTypeInfo) throws Throwable {

		String buff = getXmlPageDataControlled(logFile, false, null, -1, -1, fileTypeInfo);

		return buff;
	}

	private static StringBuffer getEndOfLog(ArrayList<Long> fileSize, String logFile, boolean cleanEscapeChars, int beginOffset, int endOffset) throws Throwable {

		StringBuffer stringBuffer = null;

		if (!PinaraFileUtils.checkFile(logFile)) {
			stringBuffer = PinaraFileUtils.readTextFile(fileSize, beginOffset, endOffset, logFile, "ERROR", cleanEscapeChars, -1, false);
		}
		
		return stringBuffer;
	}

}
