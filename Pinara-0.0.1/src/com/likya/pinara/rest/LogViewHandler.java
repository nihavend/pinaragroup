package com.likya.pinara.rest;

import java.io.File;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.pinara.Pinara;
import com.likya.pinara.mng.PinaraAppManagerImpl;

public class LogViewHandler extends FileViewHandler {

	public String handleView(ViewTypeInfo viewTypeInfo) {

		String response = returnFault();

		String viewFile = null;

		switch (viewTypeInfo.viewTypeText) {

		case "infotag":

			switch (viewTypeInfo.viewSubTypeText) {

			case "pinaraConfig":
				viewFile = "pinaraConfig.xml";
				try {
					response = getLimited(viewTypeInfo.queryParamArr, viewFile, FileTypeInfo.NATIVEXML);
				} catch (Throwable t) {
					t.printStackTrace();
					response = returnFault(t);
				}
				break;
				
			case "myraConfig":
				// response = TlosServer.getTlosParameters().getConfigFileContent();
				// TlosParameters.setRequestedFileName(TlosServer.getConfigFileName());
				viewFile = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getMyraConfigFile();
				try {
					response = getLimited(viewTypeInfo.queryParamArr, viewFile, FileTypeInfo.NATIVEXML);
				} catch (Throwable t) {
					t.printStackTrace();
					response = returnFault(t);
				}
				break;

			case "myraSenaryo":
				// response = TlosServer.getTlosParameters().getScenarioFileContent();
				// isXml = true;
				// TlosParameters.setRequestedFileName(TlosServer.getTlosParameters().getScenarioFile());
				viewFile = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getSenaryoDosyasi();
				try {
					response = getLimited(viewTypeInfo.queryParamArr, viewFile, FileTypeInfo.NATIVEXML);
				} catch (Throwable t) {
					t.printStackTrace();
					response = returnFault(t);
				}
				break;
				
			case "myraLog":
				viewFile = "myraTrace.log";
				try {
					response = getLimited(viewTypeInfo.queryParamArr, viewFile, FileTypeInfo.TOXML);
				} catch (Throwable t) {
					t.printStackTrace();
					response = returnFault(t);
				}

				break;

			case "pinaraLog":
				viewFile = "pinaraTrace.log";
				try {
					response = getLimited(viewTypeInfo.queryParamArr, viewFile, FileTypeInfo.TOXML);
				} catch (Throwable t) {
					t.printStackTrace();
					response = returnFault(t);
				}
				
				break;
				
			case "pinaraEkran":
				viewFile = "pinaraEkran.log";
				try {
					response = getLimited(viewTypeInfo.queryParamArr, viewFile, FileTypeInfo.TOXML);
				} catch (Throwable t) {
					t.printStackTrace();
					response = returnFault(t);
				}

				break;
				
			case "tlos.xsl":
				viewFile = "tlos.xsl";
				response = xslPager(viewFile);
				break;

			case "license":
				try {
					response = FileUtils.readFile("lisans.htm").toString();
				} catch (Exception e) {
					response = "\"lisans.htm\"" + Pinara.getMessage("FileViewHandler.6");
				}
				break;

			default:
				break;
			}

			break;

		case "joblog":
			try {

				JobImpl jobImpl = PinaraAppManagerImpl.getInstance().getJobQueue().get(viewTypeInfo.viewSubTypeText);

				if (jobImpl != null) {
					String logfilePath = jobImpl.getAbstractJobType().getBaseJobInfos().getJobLogPath();
					viewFile = jobImpl.getAbstractJobType().getBaseJobInfos().getJobLogFile();
					if (logfilePath.endsWith(File.separator) || viewFile.startsWith(File.separator)) {
						viewFile = logfilePath + viewFile;
					} else {
						viewFile = logfilePath + File.separator + viewFile;
					}

					response = getLimited(viewTypeInfo.queryParamArr, viewFile, FileTypeInfo.TOXML);
				} else {
					response = returnFault(-1, "Job does not exist for id " + viewTypeInfo.viewSubTypeText);
				}
			} catch (Throwable t) {
				t.printStackTrace();
				response = returnFault(t);
			}

			break;

		case "jobcontent":

			try {

				JobImpl jobImpl = PinaraAppManagerImpl.getInstance().getJobQueue().get(viewTypeInfo.viewSubTypeText);

				if (jobImpl != null) {
					String jobfilePath = jobImpl.getAbstractJobType().getBaseJobInfos().getJobTypeDetails().getJobPath();
					viewFile = jobImpl.getAbstractJobType().getBaseJobInfos().getJobTypeDetails().getJobCommand();
					if (jobfilePath.endsWith(File.separator) || viewFile.startsWith(File.separator)) {
						viewFile = jobfilePath + viewFile;
					} else {
						viewFile = jobfilePath + File.separator + viewFile;
					}
					response = getLimited(viewTypeInfo.queryParamArr, viewFile, FileTypeInfo.TOXML);
				} else {
					response = returnFault(-1, "Job does not exist for id " + viewTypeInfo.viewSubTypeText);
				}

			} catch (Throwable t) {
				t.printStackTrace();
				response = returnFault(t);
			}

			break;

		case "jobbuff":

			try {

				JobImpl jobImpl = PinaraAppManagerImpl.getInstance().getJobQueue().get(viewTypeInfo.viewSubTypeText);

				if (jobImpl != null) {
					StringBuffer bufferText = new StringBuffer();
					if (jobImpl.getJobRuntimeProperties().getMessageBuffer() != null) {
						bufferText.append(jobImpl.getJobRuntimeProperties().getMessageBuffer());
					} else {
						bufferText.append("");
					}
					// response = HtmlPages.getBufferPage(bufferText);
					response = returnResult(bufferText.toString(), null, viewTypeInfo.viewTypeText, null);
				} else {
					response = returnFault(-1, "Job does not exist for id " + viewTypeInfo.viewSubTypeText);
				}

			} catch (Throwable t) {
				t.printStackTrace();
				response = returnFault(t);
			}

			break;

		default:
			break;
		}

		return response;

	}
}
