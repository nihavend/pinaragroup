package com.likya.pinara.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

import com.likya.myra.jef.jobs.JobImpl;
import com.likya.pinara.Pinara;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.wlagen.TimeManagementDocument.TimeManagement;

public class MailContentHelper {
	
	public static final String CONTENT_PATH = "/com/likya/pinara/resources/";
	public static final String NO_MESSAGE = "no message";
	
	public enum MailSubjectType {
		STARTUP, BEGINOFCYCLE, ENDOFCYCLE, STATECHANGE, LOGANALYZE, SHUTDOWNINFO;
	}

	public static String getCss(String fileName) {
		String str = "<!--BEGIN " + fileName + " -->"; 
		str += "<style type=\"text/css\">\n"; //$NON-NLS-1$
		str += getSection(fileName); //$NON-NLS-1$
		str += "</style>\n"; //$NON-NLS-1$
		str += "<!--END " + fileName + " -->"; 
		
		return str;
	}
	
	public static String getSection(String fileName) {
		InputStream fis = null;
		StringBuffer outputBuffer = new StringBuffer();

		try {
			fis = MailContentHelper.class.getResourceAsStream(CONTENT_PATH + fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(fis);
			
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String bufferString = null;

			while ((bufferString = bufferedReader.readLine()) != null) {
				outputBuffer.append(bufferString);
				outputBuffer.append("\n"); //$NON-NLS-1$
			}
			fis.close();
		} catch(FileNotFoundException fnfex) {
			fnfex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return outputBuffer.toString();
	}
	
	public static String getHTMLMailForStartup(MailSubjectType mailSubjectType, HashMap<String, JobImpl> jobQueue) {
		return getHTMLMailForStartup(mailSubjectType, jobQueue, NO_MESSAGE);
	}
	
	public static String getHTMLMailForStartup(MailSubjectType mailSubjectType, HashMap<String, JobImpl> jobQueue, String headerMessage) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/tr/html4/loose.dtd\">");
		stringBuilder.append("<html  class=\"likya\">");
		stringBuilder.append("    <head>");
		stringBuilder.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		stringBuilder.append("        <title>Pınara Scheduler</title>");
		stringBuilder.append(		 getCss("css/mail.css"));
		stringBuilder.append("    </head>");
		stringBuilder.append("    <body style=\"background-color: rgb(239, 241, 244);\">");
		stringBuilder.append("    	<div class=\"likya-main\" style=\"display: block;\">");
		stringBuilder.append("    		<div class=\"likya-right\">");
		stringBuilder.append("    			<div class=\"likya-placeholder\">");
		stringBuilder.append("    				<ul id=\"likya-preview\">");
		stringBuilder.append(						getUpperFrameModule());
		stringBuilder.append(						getHeaderModule(mailSubjectType, headerMessage));
		stringBuilder.append(						getMainAreaModule(mailSubjectType, jobQueue, NO_MESSAGE));
		stringBuilder.append(						getFooterMessageModule());
		//stringBuilder.append(						getGreySeperatorModule());
		stringBuilder.append(						getFooterModule());
		stringBuilder.append("    				</ul>");
		stringBuilder.append("    			</div>");
		stringBuilder.append("    		</div>");
		stringBuilder.append("      </div>");
		stringBuilder.append("    </body>");
		stringBuilder.append("</html>");
		
		return stringBuilder.toString();
	}
	
	public static String getHTMLMailForStateChange(String messageList) {
		return getHTMLMailForStateChange(messageList, NO_MESSAGE);
	}
	
	public static String getHTMLMailForStateChange(String messageList, String headerMessage) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/tr/html4/loose.dtd\">");
		stringBuilder.append("<html  class=\"likya\">");
		stringBuilder.append("    <head>");
		stringBuilder.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		stringBuilder.append("        <title>Pınara Scheduler</title>");
		stringBuilder.append(		 getCss("css/mail.css"));
		stringBuilder.append("    </head>");
		stringBuilder.append("    <body style=\"background-color: rgb(239, 241, 244);\">");
		stringBuilder.append("    	<div class=\"likya-main\" style=\"display: block;\">");
		stringBuilder.append("    		<div class=\"likya-right-small\">");
		stringBuilder.append("    			<div class=\"likya-placeholder\">");
		stringBuilder.append("    				<ul id=\"likya-preview\">");
		stringBuilder.append(						getUpperFrameModule());
		stringBuilder.append(						getHeaderModule(MailSubjectType.STATECHANGE, headerMessage));
		stringBuilder.append(						getMainAreaModule(MailSubjectType.STATECHANGE, null, messageList));
		stringBuilder.append(						getFooterMessageModule());
		stringBuilder.append(						getFooterModule());
		stringBuilder.append("    				</ul>");
		stringBuilder.append("    			</div>");
		stringBuilder.append("    		</div>");
		stringBuilder.append("      </div>");
		stringBuilder.append("    </body>");
		stringBuilder.append("</html>");
		
		return stringBuilder.toString();
	}
	
	public static String getHTMLMailForInfo(MailSubjectType mailSubjectType, String mainMessage) {
		return getHTMLMailForInfo(mailSubjectType, NO_MESSAGE, mainMessage);
	}
	
	public static String getHTMLMailForInfo(MailSubjectType mailSubjectType, String headerMessage, String mainMessage) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/tr/html4/loose.dtd\">");
		stringBuilder.append("<html  class=\"likya\">");
		stringBuilder.append("    <head>");
		stringBuilder.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		stringBuilder.append("        <title>Pınara Scheduler</title>");
		stringBuilder.append(		 getCss("css/mail.css"));
		stringBuilder.append("    </head>");
		stringBuilder.append("    <body style=\"background-color: rgb(239, 241, 244);\">");
		stringBuilder.append("    	<div class=\"likya-main\" style=\"display: block;\">");
		stringBuilder.append("    		<div class=\"likya-right-small\">");
		stringBuilder.append("    			<div class=\"likya-placeholder\">");
		stringBuilder.append("    				<ul id=\"likya-preview\">");
		stringBuilder.append(						getUpperFrameModule());
		stringBuilder.append(						getHeaderModule(mailSubjectType, headerMessage));
		stringBuilder.append(						getMainAreaModule(mailSubjectType, null, mainMessage));
		stringBuilder.append(						getFooterMessageModule());
		//stringBuilder.append(						getGreySeperatorModule());
		stringBuilder.append(						getFooterModule());
		stringBuilder.append("    				</ul>");
		stringBuilder.append("    			</div>");
		stringBuilder.append("    		</div>");
		stringBuilder.append("      </div>");
		stringBuilder.append("    </body>");
		stringBuilder.append("</html>");
		
		return stringBuilder.toString();
	}
	
	private static String getUpperFrameModule() {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<li module=\"Upper Frame\">");
		stringBuilder.append("	<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f8f8f8\" style=\"padding: 0; margin: 0;\">");
		stringBuilder.append("		<tbody>");
		stringBuilder.append("			<tr>");
		stringBuilder.append("				<td valign=\"top\" align=\"center\">");
		stringBuilder.append("					<table  width=\"90%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("						<tbody>");
		stringBuilder.append("							<!-- START OF VERTICAL SPACER-->");
		stringBuilder.append("							<tr>");
		stringBuilder.append("								<td height=\"40\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("									&nbsp;</td>");
		stringBuilder.append("							</tr>");
		stringBuilder.append("							<!-- END OF VERTICAL SPACER-->");
		stringBuilder.append("						</tbody>");
		stringBuilder.append("					</table>");
		stringBuilder.append("				</td>");
		stringBuilder.append("			</tr>");
		stringBuilder.append("		</tbody>");
		stringBuilder.append("	</table>");
		stringBuilder.append("</li>");
		
		return stringBuilder.toString();
	}
	
	private static String getHeaderModule(MailSubjectType mailSubjectType, String headerMessage) {

		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("<li module=\"Header\">");
		stringBuilder.append("	<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f8f8f8\" style=\"padding: 0; margin: 0;\">");
		stringBuilder.append("		<tbody>");
		stringBuilder.append("			<tr>");
		stringBuilder.append("				<td valign=\"top\" align=\"center\">");
		stringBuilder.append("					<table bgcolor=\"#ffffff\" width=\"90%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("						<tbody>");
		stringBuilder.append("							<tr>");
		stringBuilder.append("								<td>");
		stringBuilder.append("									<table  width=\"90%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("										<!-- START OF VERTICAL SPACER-->");
		stringBuilder.append("										<tbody>");
		stringBuilder.append("											<tr>");
		stringBuilder.append("												<td height=\"30\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("													&nbsp;</td>");
		stringBuilder.append("											</tr>");
		stringBuilder.append("											<!-- END OF VERTICAL SPACER-->");
		stringBuilder.append("											<tr>");
		stringBuilder.append("												<td valign=\"top\" style=\"padding: 0px;\">");
		stringBuilder.append("													<!-- START OF LOGO-->");
		stringBuilder.append("													<table width=\"255\" border=\"0\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("														<tbody>");
		stringBuilder.append("															<tr>");
		stringBuilder.append("																<td align=\"right\" style=\"padding: 0px; text-transform: uppercase; font-family: Lucida Sans Unicode; color: #666666; font-size: 24px; line-height: 10px;\">");
		stringBuilder.append("																	<span>");
		stringBuilder.append("																		<a href=\"http://www.likyateknoloji.com\" style=\"color: #ffc526;\"> <img src=\"cid:likyajpg10976@likyateknoloji.com\" editable=\"true\" alt=\"logo\" width=\"144\" height=\"40\" border=\"0\" style=\"display: inline;\"></a>");
		stringBuilder.append("																		<br>");
		String ipAddress = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getServerIpAddress();
		int port = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getHttpPort();
		stringBuilder.append("                      													<a href=\"http://" + ipAddress + ":" + port + "/\" target=\"_blank\" title=\"" + Pinara.getMessage("JobQueueOperations.54") + "\"style=\"color: #666666; font-size: 6px;\">" + Pinara.getMessage("JobQueueOperations.56") + "</a>");
		stringBuilder.append("																	</span>");
		stringBuilder.append("																</td>");
		stringBuilder.append("															</tr>");
		stringBuilder.append("														</tbody>");
		stringBuilder.append("													</table>");
		stringBuilder.append("													<!-- END OF LOGO-->");
		stringBuilder.append("												</td>");
		stringBuilder.append("											</tr>");
		stringBuilder.append("											<tr>");
		stringBuilder.append("												<td valign=\"top\" style=\"padding: 0px;\">");
		stringBuilder.append("													<table width=\"255\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("														<tbody>");
		stringBuilder.append("															<tr>");
		stringBuilder.append("																<td class=\"center\" style=\"padding: 0px; text-transform: uppercase; font-family: Lucida Sans Unicode; font-size: 24px; line-height: 40px;\">");
		stringBuilder.append("																	<span>Pınara Scheduler</span>");
		stringBuilder.append("																</td>");
		stringBuilder.append("															</tr>");
		stringBuilder.append("														</tbody>");
		stringBuilder.append("													</table>");
		stringBuilder.append("												</td>");
		stringBuilder.append("											</tr>");
		
		if(!NO_MESSAGE.equals(headerMessage)) {
			stringBuilder.append("											<tr>");
			stringBuilder.append("												<td align=\"center\" style=\"margin: 0; font-size: 14px; font-family: Helvetica, Arial, sans-serif; line-height: 18px;\">");
			stringBuilder.append("													<multiline><span>");
			stringBuilder.append(														headerMessage);
			stringBuilder.append("														<img src=\"cid:dividerpng10977@likyateknoloji.com\"");
			stringBuilder.append("														alt=\"divider image\" width=\"100%\" height=\"1\" border=\"0\" style=\"display: block;\">");
			stringBuilder.append("													</span></multiline>");
			stringBuilder.append("												</td>");
			stringBuilder.append("											</tr>");
			stringBuilder.append("											<tr>");
			stringBuilder.append("												<td height=\"30\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
			stringBuilder.append("													&nbsp;</td>");
			stringBuilder.append("											</tr>");
		}
		
		stringBuilder.append("										</tbody>");
		stringBuilder.append("									</table>");
		stringBuilder.append("								</td>");
		stringBuilder.append("							</tr>");
		stringBuilder.append("						</tbody>");
		stringBuilder.append("					</table>");
		stringBuilder.append("				</td>");
		stringBuilder.append("			</tr>");
		stringBuilder.append("		</tbody>");
		stringBuilder.append("	</table>");
		stringBuilder.append("</li>");
		
		return stringBuilder.toString();
	}	
	
	private static String getMainAreaModule(MailSubjectType mailSubjectType, HashMap<String, JobImpl> jobQueue, String messageList) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<li module=\"Main Area\" class=\"\">");
		stringBuilder.append("	<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f8f8f8\" style=\"padding: 0; margin: 0;\">");
		stringBuilder.append("		<tbody>");
		stringBuilder.append("			<tr>");
		stringBuilder.append("				<td valign=\"top\" align=\"center\">");
		stringBuilder.append("					<table width=\"90%\" bgcolor=\"#ffc526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"white\" height=\"30\">");
		stringBuilder.append("						<tbody>");
		stringBuilder.append("							<tr>");
		stringBuilder.append("								<td>&nbsp;</td>");
		stringBuilder.append("							</tr>");
		stringBuilder.append("						</tbody>");
		stringBuilder.append("					</table>");
		stringBuilder.append("				</td>");
		stringBuilder.append("			</tr>");
		stringBuilder.append(			getMainAreaCentralPart(mailSubjectType, jobQueue, messageList));
		stringBuilder.append("			<tr>");
		stringBuilder.append("				<td valign=\"top\" align=\"center\">");
		stringBuilder.append("					<table width=\"90%\" bgcolor=\"#ffc526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"white\" height=\"30\">");
		stringBuilder.append("						<tbody>");
		stringBuilder.append("							<tr>");
		stringBuilder.append("								<td>&nbsp;</td>");
		stringBuilder.append("							</tr>");
		stringBuilder.append("						</tbody>");
		stringBuilder.append("					</table>");
		stringBuilder.append("				</td>");
		stringBuilder.append("			</tr>");
		stringBuilder.append("		</tbody>");
		stringBuilder.append("	</table>");
		stringBuilder.append("</li>");
		
		return stringBuilder.toString();
	}
	
	private static String getMainAreaCentralPart(MailSubjectType mailSubjectType, HashMap<String, JobImpl> jobQueue, String messageList) {
		
		StringBuilder stringBuilder = new StringBuilder();
		String messageArray[] = messageList.split(";");
		
		if(mailSubjectType.equals(MailSubjectType.STARTUP) || mailSubjectType.equals(MailSubjectType.BEGINOFCYCLE) || mailSubjectType.equals(MailSubjectType.ENDOFCYCLE)) {
			stringBuilder.append("			<tr>");
			stringBuilder.append("				<td valign=\"top\" align=\"center\">");
			stringBuilder.append("					<table width=\"90%\" bgcolor=\"#ffc526\" border=\"1\" align=\"center\" cellpadding=\"0\" bordercolor=\"white\" cellspacing=\"0\" style=\"color: #666666; font-size: 9px;\">");
			stringBuilder.append("						<tbody>");
			stringBuilder.append("							<tr>");
			stringBuilder.append("								<td colspan=\"5\" align=\"center\" class=\"gray\">" + Pinara.getMessage("JobQueueOperations.75") + "</td>");
			stringBuilder.append("							</tr>");
			stringBuilder.append("							<tr>");
			stringBuilder.append("				        			<th>" + Pinara.getMessage("JobQueueOperations.82") + "</th>");
			stringBuilder.append("				        			<th>" + Pinara.getMessage("JobQueueOperations.84") + "</th>");
			stringBuilder.append("				        			<th>" + Pinara.getMessage("JobQueueOperations.86") + "</th>");
			stringBuilder.append("				        			<th>" + Pinara.getMessage("JobQueueOperations.88") + "</th>");
			stringBuilder.append("				        			<th>" + Pinara.getMessage("JobQueueOperations.90") + "</th>");
			stringBuilder.append("							</tr>");
			
			Iterator<JobImpl> jobsIterator = jobQueue.values().iterator();

			while (jobsIterator.hasNext()) {
				JobImpl scheduledJob = jobsIterator.next();
				AbstractJobType abstractJobType = scheduledJob.getAbstractJobType();
				stringBuilder.append("				        <tr>");
				stringBuilder.append("				        		<td>" + abstractJobType.getBaseJobInfos().getJsName() + "</td>");
				stringBuilder.append("				        		<td>" + abstractJobType.getBaseJobInfos().getJobTypeDetails().getJobCommand() + "</td>");
				stringBuilder.append("				        		<td>" + abstractJobType.getBaseJobInfos().getJobLogPath() + "</td>");

				TimeManagement timeManagement = abstractJobType.getManagement().getTimeManagement();
				
				if(timeManagement != null) {
					stringBuilder.append("				        <td>" + abstractJobType.getManagement().getTimeManagement().getJsActualTime().getStartTime() + "</td>");
					stringBuilder.append("				        <td>" + (abstractJobType.getManagement().getTimeManagement().getJsRecordedTime() == null ? "-":abstractJobType.getManagement().getTimeManagement().getJsRecordedTime()) + "</td>");
				} else {
					stringBuilder.append("				        <td>-</td>");
					stringBuilder.append("				        <td>-</td>");
				}
				stringBuilder.append("				        </tr>");
			}
			
			stringBuilder.append("						</tbody>");
			stringBuilder.append("					</table>");
			stringBuilder.append("				</td>");
			stringBuilder.append("			</tr>");

		} else if(mailSubjectType.equals(MailSubjectType.STATECHANGE)) {
			
			stringBuilder.append("			<tr>");
			stringBuilder.append("				<td valign=\"top\" align=\"center\">");
			stringBuilder.append("					<table width=\"90%\" bgcolor=\"#ffc526\" border=\"0\" align=\"center\" cellpadding=\"0\" bordercolor=\"white\" cellspacing=\"0\" style=\"color: #666666; font-size: 12px;\">");
			stringBuilder.append("						<tbody>");
			for(int i = 0 ; i < messageArray.length ; i = i+2) {
				stringBuilder.append("							<tr>");
				stringBuilder.append("								<td align=\"center\" style=\"margin: 0; padding:0px; margin:0; font-size:12px ; color:#ffffff; font-family: Helvetica, Arial, sans-serif; line-height: 28px;mso-line-height-rule: exactly;\">");
				stringBuilder.append("									<multiline><span>");
				stringBuilder.append("										<font color=\"#666666\">" + messageArray[i] + ":</font>");
				stringBuilder.append(										"  " + messageArray[i+1]);
				stringBuilder.append("									</span></multiline>");
				stringBuilder.append("								</td>");
				stringBuilder.append("							</tr>");
			}
			stringBuilder.append("						</tbody>");
			stringBuilder.append("					</table>");
			stringBuilder.append("				</td>");
			stringBuilder.append("			</tr>");
			
		} else if(mailSubjectType.equals(MailSubjectType.SHUTDOWNINFO)) {
			
			stringBuilder.append("			<tr>");
			stringBuilder.append("				<td valign=\"top\" align=\"center\">");
			stringBuilder.append("					<table width=\"90%\" bgcolor=\"#ffc526\" border=\"0\" align=\"center\" cellpadding=\"0\" bordercolor=\"white\" cellspacing=\"0\" style=\"color: #666666; font-size: 12px;\">");
			stringBuilder.append("						<tbody>");
			stringBuilder.append("							<tr>");
			stringBuilder.append("								<td align=\"center\" style=\"margin: 0; padding:0px; margin:0; font-size:18px ; color:#ffffff; font-family: Helvetica, Arial, sans-serif; line-height: 28px;mso-line-height-rule: exactly;\">");
			stringBuilder.append("									<multiline><span>");
			stringBuilder.append(										messageArray[0]);
			stringBuilder.append("									</span></multiline>");
			stringBuilder.append("								</td>");
			stringBuilder.append("							</tr>");
			stringBuilder.append("						</tbody>");
			stringBuilder.append("					</table>");
			stringBuilder.append("				</td>");
			stringBuilder.append("			</tr>");

		}  else if(mailSubjectType.equals(MailSubjectType.LOGANALYZE)) {
			
			stringBuilder.append("			<tr>");
			stringBuilder.append("				<td valign=\"top\" align=\"center\">");
			stringBuilder.append("					<table width=\"90%\" bgcolor=\"#ffc526\" border=\"0\" align=\"center\" cellpadding=\"0\" bordercolor=\"white\" cellspacing=\"0\" style=\"color: #666666; font-size: 12px;\">");
			stringBuilder.append("						<tbody>");
			stringBuilder.append("							<tr>");
			stringBuilder.append("								<td align=\"center\" style=\"margin: 0; padding:0px; margin:0; font-size:18px ; color:#ffffff; font-family: Helvetica, Arial, sans-serif; line-height: 28px;mso-line-height-rule: exactly;\">");
			stringBuilder.append("									<multiline><span>");
			stringBuilder.append(										messageList);
			stringBuilder.append("									</span></multiline>");
			stringBuilder.append("								</td>");
			stringBuilder.append("							</tr>");
			stringBuilder.append("						</tbody>");
			stringBuilder.append("					</table>");
			stringBuilder.append("				</td>");
			stringBuilder.append("			</tr>");

		}
		
		return stringBuilder.toString();
	}
	
	private static String getFooterMessageModule() {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<li module=\"Message Area\">");
		stringBuilder.append("	<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f8f8f8\" style=\"padding: 0; margin: 0;\">");
		stringBuilder.append("		<tbody>");
		stringBuilder.append("			<tr>");
		stringBuilder.append("				<td valign=\"top\" align=\"center\">");
		stringBuilder.append("					<table bgcolor=\"#ffffff\" width=\"90%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("						<!-- START OF VERTICAL SPACER-->");
		stringBuilder.append("						<tbody>");
		if (!Pinara.isLicensed()) {
			stringBuilder.append("							<tr>");
			stringBuilder.append("								<td align=\"center\" style=\"margin: 0; font-size: 14px; color: #666666; font-family: Helvetica, Arial, sans-serif; line-height: 18px;\">");
			stringBuilder.append("									<multiline><span>");
			stringBuilder.append(										Pinara.getMessage("JobQueueOperations.121"));
			stringBuilder.append("										<img src=\"cid:dividerpng10977@likyateknoloji.com\"");
			stringBuilder.append("										alt=\"divider image\" width=\"60%\" height=\"1\" border=\"0\" style=\"display: block;\">");
			stringBuilder.append("									</span></multiline>");
			stringBuilder.append("								</td>");
			stringBuilder.append("							</tr>");
		}
		stringBuilder.append("							<tr>");
		stringBuilder.append("								<td height=\"40\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("									&nbsp;");
		stringBuilder.append("								</td>");
		stringBuilder.append("							</tr>");
		stringBuilder.append("						</tbody>");
		stringBuilder.append("					</table>");
		stringBuilder.append("				</td>");
		stringBuilder.append("			</tr>");
		stringBuilder.append("		</tbody>");
		stringBuilder.append("	</table>");
		stringBuilder.append("</li>");
		
		return stringBuilder.toString();
	}
	
	private static String getFooterModule() {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<li module=\"Footer\">");
		stringBuilder.append("	<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f8f8f8\" style=\"padding: 0; margin: 0;\">");
		stringBuilder.append("		<tbody>");
		stringBuilder.append("			<tr>");
		stringBuilder.append("				<td valign=\"top\" align=\"center\">");
		stringBuilder.append("					<table bgcolor=\"#666666\" width=\"90%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("						<tbody>");
		stringBuilder.append("							<tr>");
		stringBuilder.append("								<td>");
		stringBuilder.append("									<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("										<tbody>");
		stringBuilder.append("											<tr>");
		stringBuilder.append("												<td valign=\"top\" style=\"padding-top: 0; padding-bottom: 0\">");
		stringBuilder.append("													<table width=\"100%\" border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("														<!-- START OF VERTICAL SPACER-->");
		stringBuilder.append("														<tbody>");
		stringBuilder.append("															<tr>");
		stringBuilder.append("																<td height=\"30\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("																	&nbsp;</td>");
		stringBuilder.append("															</tr>");
		stringBuilder.append("															<!-- END OF VERTICAL SPACER-->");
		stringBuilder.append("															<tr>");
		stringBuilder.append("																<td align=\"center\" style=\"margin: 0; font-size: 12px; color: #ededed; font-family: Helvetica, Arial, sans-serif; line-height: 18px;\">");
		stringBuilder.append("																	<multiline> <span> V " + Pinara.getVersion() + "<br>");
		stringBuilder.append("																			Likya Bilgi Teknolojileri ve Ilet. Hiz. Ltd. Sti. Tuzla, İstanbul, Türkiye");
		stringBuilder.append("																	 </span> <br>");
		stringBuilder.append("																	<span style=\"color: #ffc526;\">");
		stringBuilder.append("																		  <a href=\"http://www.likyateknoloji.com\" style=\"color: #ffc526;\">www.likyateknoloji.com</a>");
		stringBuilder.append("																		| <a href=\"mailto:bilgi@likyateknoloji.com\" style=\"color: #ffc526;\">bilgi@likyateknoloji.com</a>");
		stringBuilder.append("																	</span></multiline>");
		stringBuilder.append("																</td>");
		stringBuilder.append("															</tr>");
		stringBuilder.append("															<!-- START OF VERTICAL SPACER-->");
		stringBuilder.append("															<tr>");
		stringBuilder.append("																<td height=\"20\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("																	&nbsp;</td>");
		stringBuilder.append("															</tr>");
		stringBuilder.append("															<!-- END OF VERTICAL SPACER-->");
		stringBuilder.append("														</tbody>");
		stringBuilder.append("													</table>");
		stringBuilder.append("												</td>");
		stringBuilder.append("											</tr>");
		stringBuilder.append("										</tbody>");
		stringBuilder.append("									</table>");
		stringBuilder.append("								</td>");
		stringBuilder.append("							</tr>");
		stringBuilder.append("						</tbody>");
		stringBuilder.append("					</table>");
		stringBuilder.append("					<table bgcolor=\"#666666\" width=\"90%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-top: 1px solid #767373;\">");
		stringBuilder.append("						<tbody>");
		stringBuilder.append("							<tr>");
		stringBuilder.append("								<td>");
		stringBuilder.append("									<table class=\"table-inner\" width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("										<tbody>");
		stringBuilder.append("											<tr>");
		stringBuilder.append("												<td valign=\"top\" style=\"padding: 0px;\">");
		stringBuilder.append("													<table width=\"100%\" border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("														<!-- START OF VERTICAL SPACER-->");
		stringBuilder.append("														<tbody>");
		stringBuilder.append("															<tr>");
		stringBuilder.append("																<td height=\"20\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("																	&nbsp;</td>");
		stringBuilder.append("															</tr>");
		stringBuilder.append("															<!-- END OF VERTICAL SPACER-->");
		stringBuilder.append("															<tr>");
		stringBuilder.append("																<td align=\"center\" style=\"margin: 0; font-size: 12px; color: #ededed; font-family: Helvetica, Arial, sans-serif; line-height: 18px;\">");
		stringBuilder.append("																	<multiline> <span>");
		stringBuilder.append("																	    Copyright (c) 2008 <a href=\"http://www.likyateknoloji.com\" style=\"color: #ffc526;\">www.likyateknoloji.com</a> all right reserved.");
		stringBuilder.append("																	</span></multiline>");
		stringBuilder.append("																</td>");
		stringBuilder.append("															</tr>");
		stringBuilder.append("															<!-- START OF VERTICAL SPACER-->");
		stringBuilder.append("															<tr>");
		stringBuilder.append("																<td height=\"20\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("																	&nbsp;</td>");
		stringBuilder.append("															</tr>");
		stringBuilder.append("															<!-- END OF VERTICAL SPACER-->");
		stringBuilder.append("														</tbody>");
		stringBuilder.append("													</table>");
		stringBuilder.append("												</td>");
		stringBuilder.append("											</tr>");
		stringBuilder.append("										</tbody>");
		stringBuilder.append("									</table>");
		stringBuilder.append("								</td>");
		stringBuilder.append("							</tr>");
		stringBuilder.append("						</tbody>");
		stringBuilder.append("					</table>");
		stringBuilder.append("				</td>");
		stringBuilder.append("			</tr>");
		stringBuilder.append("			<!-- START OF VERTICAL SPACER-->");
		stringBuilder.append("			<tr>");
		stringBuilder.append("				<td height=\"40\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("					&nbsp;</td>");
		stringBuilder.append("			</tr>");
		stringBuilder.append("			<!-- END OF VERTICAL SPACER-->");
		stringBuilder.append("		</tbody>");
		stringBuilder.append("	</table>");
		stringBuilder.append("</li>");
		
		return stringBuilder.toString();
	}
	
	@SuppressWarnings("unused")
	private static String getGreySeperatorModule() {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<li module=\"Grey Seperator\" class=\"\">");
		stringBuilder.append("	<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f8f8f8\" style=\"padding: 0; margin: 0;\">");
		stringBuilder.append("		<tbody>");
		stringBuilder.append("			<tr>");
		stringBuilder.append("				<td valign=\"top\" align=\"center\">");
		stringBuilder.append("					<table bgcolor=\"#dddddd\" width=\"90%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("						<tbody>");
		stringBuilder.append("							<tr>");
		stringBuilder.append("								<td>");
		stringBuilder.append("									<table class=\"table-inner\" width=\"540\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		stringBuilder.append("										<tbody>");
		stringBuilder.append("											<tr>");
		stringBuilder.append("												<td height=\"30\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("													&nbsp;");
		stringBuilder.append("												</td>");
		stringBuilder.append("											</tr>");
		stringBuilder.append("											<tr>");
		stringBuilder.append("												<td height=\"30\" style=\"padding: 0; line-height: 0; font-size: 0px;\">");
		stringBuilder.append("													&nbsp;");
		stringBuilder.append("												</td>");
		stringBuilder.append("											</tr>");
		stringBuilder.append("										</tbody>");
		stringBuilder.append("									</table>");
		stringBuilder.append("								</td>");
		stringBuilder.append("							</tr>");
		stringBuilder.append("						</tbody>");
		stringBuilder.append("					</table>");
		stringBuilder.append("				</td>");
		stringBuilder.append("			</tr>");
		stringBuilder.append("		</tbody>");
		stringBuilder.append("	</table>");
		stringBuilder.append("</li>");
		
		return stringBuilder.toString();
	}

	
	//****************************

	/**
	 * @deprecated
	 */
	public static String getSimpleFormattedJobProperties(HashMap<String, JobImpl> jobQueue) {

		StringBuilder sb = new StringBuilder();

		Iterator<JobImpl> jobsIterator = jobQueue.values().iterator();

		while (jobsIterator.hasNext()) {

			JobImpl scheduledJob = jobsIterator.next();
			AbstractJobType abstractJobType = scheduledJob.getAbstractJobType();

			// TR
			sb.append(Pinara.getMessage("JobQueueOperations.18") + abstractJobType.getId() + " => ");
			sb.append(Pinara.getMessage("JobQueueOperations.20") + abstractJobType.getBaseJobInfos().getJobTypeDetails().getJobCommand() + "]");
			sb.append(Pinara.getMessage("JobQueueOperations.22") + abstractJobType.getBaseJobInfos().getJobLogPath() + "]");
			// TODO sb.append(Pinara.getMessage("JobQueueOperations.24") + abstractJobType.getBaseJobInfos().getPreviousTime());
			
			TimeManagement timeManagement = abstractJobType.getManagement().getTimeManagement();
			
			if(timeManagement != null) {
				sb.append(Pinara.getMessage("JobQueueOperations.25") + timeManagement.getJsActualTime());
				sb.append(Pinara.getMessage("JobQueueOperations.26") + timeManagement.getJsRecordedTime());
			} else {
				sb.append(Pinara.getMessage("JobQueueOperations.25"));
				sb.append(Pinara.getMessage("JobQueueOperations.26"));
			}
			// TODO sb.append(Pinara.getMessage("JobQueueOperations.27") + (jobProperties.getTime() == null ? "-" : DateUtils.getDate(jobProperties.getTime())) + "]");
			// TODO sb.append(Pinara.getMessage("JobQueueOperations.29") + jobProperties.getWorkDuration());
			// TODO sb.append(Pinara.getMessage("JobQueueOperations.30") + (jobProperties.getTime() == null ? "-" : DateUtils.getDate(jobProperties.getTime())));
			sb.append(Pinara.getMessage("JobQueueOperations.31") + Pinara.getMessage("JobQueueOperations.32"));
			// TODO sb.append(Pinara.getMessage("JobQueueOperations.33") + extractList(jobProperties.getJobDependencyInfoList()) + "]");

		}

		return sb.toString();
	}

	/**
	 * @deprecated
	 */
	public static String getHTMLFormattedJobProperties(HashMap<String, JobImpl> jobQueue) {
		return getHTMLFormattedJobProperties(jobQueue, null);
	}

	/**
	 * @deprecated
	 */
	public static String getHTMLFormattedJobProperties(HashMap<String, JobImpl> jobQueue, String localizedMessage) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/tr/html4/loose.dtd\">");
		stringBuilder.append("<html>");
		stringBuilder.append("    <head>");
		stringBuilder.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
		stringBuilder.append("        <title>Pınara Scheduler</title>");
		stringBuilder.append("    </head>");
		stringBuilder.append("    <body>");
		stringBuilder.append("        <table border=\"0\" summary=\"" + Pinara.getMessage("JobQueueOperations.44") + "\" align=\"center\">"); //$NON-NLS-3$
		stringBuilder.append("            <tr>");
		stringBuilder.append("               <td>");
		stringBuilder.append("                    <img src=\"cid:likyajpg10976@likyateknoloji.com\" align=\"right\" width=\"100\" height=\"35\" alt=\"\"/>");
		stringBuilder.append("                </td>");
		stringBuilder.append("            </tr>");
		stringBuilder.append("            <tr align=\"right\">");
		stringBuilder.append("                <td>");
		String ipAddress = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getServerIpAddress();
		int port = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getHttpPort();
		stringBuilder.append("                      <a href=\"http://" + ipAddress + ":" + port + "/\" target=\"_blank\" title=\"" + Pinara.getMessage("JobQueueOperations.54") + "\">" + Pinara.getMessage("JobQueueOperations.56") + "</a>");
		stringBuilder.append("                </td>");
		stringBuilder.append("            </tr>");

		stringBuilder.append("            <tr>");
		stringBuilder.append("                <td align=\"center\">");

		if (localizedMessage == null) {
			stringBuilder.append("                    <h1>Pınara Scheduler</h1>" + Pinara.getMessage("JobQueueOperations.64"));
		} else {
			stringBuilder.append("                    <h1>Pınara Scheduler</h1>" + localizedMessage);
		}

		stringBuilder.append("                </td>");
		stringBuilder.append("            </tr>");
		stringBuilder.append("            <tr>");
		stringBuilder.append("                <td colspan=\"2\">");
		stringBuilder.append("                <hr/>");
		stringBuilder.append("                </td>");
		stringBuilder.append("            </tr>");
		stringBuilder.append("            <tr>");
		stringBuilder.append("                <td colspan=\"2\">");
		stringBuilder.append("                    <table border=\"1\" summary=\"" + Pinara.getMessage("JobQueueOperations.75") + "\" align=\"center\">"); //$NON-NLS-3$
		stringBuilder.append("                        <caption>");
		stringBuilder.append("                            <em>" + Pinara.getMessage("JobQueueOperations.78") + "</em>"); //$NON-NLS-3$
		stringBuilder.append("                        </caption>");
		stringBuilder.append("				        <tr>");
		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.82") + "</th>");
		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.84") + "</th>");
		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.86") + "</th>");
		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.88") + "</th>");
		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.90") + "</th>");
//		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.92") + "</th>");
//		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.94") + "</th>");
//		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.96") + "</th>");
//		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.98") + "</th>");
//		stringBuilder.append("				        	<th>" + Pinara.getMessage("JobQueueOperations.100") + "</th>");
		stringBuilder.append("			 			</tr>");


		Iterator<JobImpl> jobsIterator = jobQueue.values().iterator();

		while (jobsIterator.hasNext()) {

			JobImpl scheduledJob = jobsIterator.next();
			AbstractJobType abstractJobType = scheduledJob.getAbstractJobType();
			
			stringBuilder.append("				        <tr>");
			stringBuilder.append("				        	<td>" + abstractJobType.getBaseJobInfos().getJsName() + "</td>");
			stringBuilder.append("				        	<td>" + abstractJobType.getBaseJobInfos().getJobTypeDetails().getJobCommand() + "</td>");
			stringBuilder.append("				        	<td>" + abstractJobType.getBaseJobInfos().getJobLogPath() + "</td>");
			// TODO stringBuilder.append("				        <td>" + jobProperties.getPreviousTime());

			TimeManagement timeManagement = abstractJobType.getManagement().getTimeManagement();
			
			if(timeManagement != null) {
				stringBuilder.append("				        <td>" + abstractJobType.getManagement().getTimeManagement().getJsActualTime().getStartTime() + "</td>");
				stringBuilder.append("				        <td>" + (abstractJobType.getManagement().getTimeManagement().getJsRecordedTime() == null ? "-":abstractJobType.getManagement().getTimeManagement().getJsRecordedTime()) + "</td>");
			} else {
				stringBuilder.append("				        <td>-</td>");
				stringBuilder.append("				        <td>-</td>");
			}
			
			stringBuilder.append("				        <tr>");
			
		}

		stringBuilder.append("                    </table>");
		stringBuilder.append("                </td>");
		stringBuilder.append("            </tr>");
		stringBuilder.append("        </table>");

		stringBuilder.append(pageFooter());

		stringBuilder.append("    </body>");
		stringBuilder.append("</html>");

		return stringBuilder.toString();

	}
	
	/**
	 * @deprecated
	 */
	private static StringBuilder pageFooter() {

		StringBuilder footerValue = new StringBuilder();

		footerValue.append("        <br/>"); //$NON-NLS-1$
		footerValue.append("        <br/>"); //$NON-NLS-1$

		if (!Pinara.isLicensed()) {
			footerValue.append("        <h4 align=\"center\">" + Pinara.getMessage("JobQueueOperations.121") + "</h4>");
		}

		footerValue.append("        <hr size=\"1\" color=\"black\" width=\"80%\"/>");
		footerValue.append("        <h6 align=\"center\">" + Pinara.getVersion());
		footerValue.append("            <br/>" + Pinara.getMessage("JobQueueOperations.126"));
		footerValue.append("            <br/>Tuzla, İstanbul, Türkiye");
		footerValue.append("            <br/>");
		footerValue.append("            <a href=\"http://www.likyateknoloji.com\" title=\"www.likyateknoloji.com\">www.likyateknoloji.com</a>&nbsp;&nbsp;");
		footerValue.append("            <a href=\"mailto:bilgi@likyateknoloji.com\">bilgi@likyateknoloji.com</a>");
		footerValue.append("        </h6>");

		return footerValue;
	}
}
