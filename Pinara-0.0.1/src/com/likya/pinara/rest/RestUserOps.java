package com.likya.pinara.rest;

import java.io.IOException;
import java.io.InputStream;

public class RestUserOps extends PinaraRestHandler {

	public static final String CMD_USERREAD = "userread";
	public static final String CMD_USERADD = "useradd";
	public static final String CMD_USERUPDATE = "userupdate";
	public static final String CMD_USERDELETE = "userdelete";
	
	public byte[] doWork(boolean isPost, String uriTxt, InputStream inputStream) throws IOException {

		byte responseBytes[];

		if (isPost) {
			responseBytes = parsePost(uriTxt, inputStream);
		} else {
			// responseBytes = parse(uriTxt);
			responseBytes = "NA".getBytes();
		}

		return responseBytes;
	}

	public static byte[] parsePost(String uriTxt, InputStream inputStream) throws IOException {
		
		byte responseBytes[] = new byte[0];

		String bufferString = "";
		
		String switchId = GenericRestParser.getSwitchId(bufferString, uriTxt, inputStream);
		
		if (switchId == null) {
			return responseBytes;
		}

		String retStr = "";
		
		switch (switchId) {
		
		case CMD_USERREAD:

//			if (restCommArr.length != 2 || !isInteger(restCommArr[1])) {
//				// retStr = "Job id not defined or invalid : " + uriTxt;
//				retStr = "<result>NOK : " + "Job id not defined or invalid : " + uriTxt + "</result>";
//				//				responseBytes = retStr.getBytes();
//				break;
//			}
//
//			int jobId = Integer.parseInt(restCommArr[1]);
//			// System.err.println("Job Id : " + jobId);
//			// retStr = FileUtils.readFile(xmlPath + "jobDetail.xml").toString();
//
//			try {
//				retStr = JobDetailMapper.getMapped(PinaraAppManagerImpl.getInstance().getJob("" + jobId).getAbstractJobType());
//			} catch (PinaraAuthenticationException e1) {
//				e1.printStackTrace();
//			}
			//			responseBytes = retStr.getBytes();
			break;
			
		case CMD_USERADD:
//			try {
//				extractPostInfo(bufferString, (byte) 0x01);
//				retStr = "<message><result>OK</result></message>";
//			} catch (PinaraAuthenticationException | PinaraXMLValidationException e) {
//				retStr = "<message><result>NOK</result><desc>" + e.getLocalizedMessage() + "</desc></message>";
//				e.printStackTrace();
//			}
			//			responseBytes = retStr.getBytes();
			break;

		case CMD_USERUPDATE:
//			try {
//				extractPostInfo(bufferString, (byte) 0x02);
//				retStr = "<message><result>OK</result></message>";
//			} catch (PinaraAuthenticationException | PinaraXMLValidationException e) {
//				retStr = "<message><result>NOK</result><desc>" + e.getLocalizedMessage() + "</desc></message>";
//				e.printStackTrace();
//			}
			break;

		case CMD_USERDELETE:
//			try {
//				extractPostInfo(bufferString, (byte) 0x03);
//				retStr = "<message><result>OK</result></message>";
//			} catch (PinaraAuthenticationException | PinaraXMLValidationException e) {
//				retStr = "<message><result>NOK</result><desc>" + e.getLocalizedMessage() + "</desc></message>";
//				e.printStackTrace();
//			}
			break;
			
		default:
			retStr = "<result>NOK : " + "Command not found : " + switchId + "</result>";

			break;
		}
		
		responseBytes = retStr.getBytes();

		return responseBytes;
	}
}
