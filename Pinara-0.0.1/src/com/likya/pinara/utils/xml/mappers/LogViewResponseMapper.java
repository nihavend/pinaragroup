package com.likya.pinara.utils.xml.mappers;

import java.util.ArrayList;

import com.likya.pinara.rest.FileViewHandler.FileTypeInfo;

public class LogViewResponseMapper {

	public static String faultMapper(int code, String txt) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<return><code>" + code + "</code><message>Exception : [<![CDATA[" + txt + "]]>]</message></return>";
	}

	public static String resultMapper(int code, String txt, ArrayList<Long> fileSize, String logDescriptor, FileTypeInfo fileTypeInfo) {

		StringBuilder buff = new StringBuilder();

		//buff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		//buff.append("<?xml-stylesheet type=\"text/xsl\" href=\"logdetail?fname=tlos.xsl\"?>");
		buff.append("<data>");
		buff.append("	<meta>");
		buff.append("		<size>");
		if (fileSize != null && fileSize.size() > 0) {
			buff.append("			" + fileSize.get(0));
		}
		buff.append("		</size>");
		buff.append("		<name>");
		buff.append("			" + logDescriptor);
		buff.append("		</name>");
		buff.append("	</meta>");
		buff.append("	<content>");
		if(fileTypeInfo == FileTypeInfo.NATIVEXML) {
			buff.append("		" + txt);
		} else {
			buff.append("		<![CDATA[" + txt);
			buff.append("		]]>");
		}
		buff.append("	</content>");
		buff.append("</data>");

		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<return><code>" + code + "</code><message>" + buff.toString() + "</message></return>";

	}
}
