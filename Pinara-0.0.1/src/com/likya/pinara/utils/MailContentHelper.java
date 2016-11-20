package com.likya.pinara.utils;

import java.util.HashMap;
import java.util.Iterator;

import com.likya.myra.jef.jobs.JobImpl;
import com.likya.pinara.Pinara;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.wlagen.TimeManagementDocument.TimeManagement;

public class MailContentHelper {

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

	public static String getHTMLFormattedJobProperties(HashMap<String, JobImpl> jobQueue) {
		return getHTMLFormattedJobProperties(jobQueue, null);
	}

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
