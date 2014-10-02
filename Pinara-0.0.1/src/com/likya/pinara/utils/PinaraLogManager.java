package com.likya.pinara.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.varia.LevelRangeFilter;

public class PinaraLogManager {

	public static final String PINARA_CONSOLE = "pinaraconsole";
	public static final String PINARA_SUMMARYFILE = "pinarasummaryfile";
	public static final String PINARA_TRACEFILE = "pinaratracefile";

	public static final String MYRA_CONSOLE = "myraconsole";
	public static final String MYRA_SUMMARYFILE = "myrasummaryfile";
	public static final String MYRA_TRACEFILE = "myratracefile";

	public enum PinaraAppender {

		PINARA_CONSOLE(PinaraLogManager.PINARA_CONSOLE), PINARA_SUMMARYFILE(PinaraLogManager.PINARA_SUMMARYFILE), PINARA_TRACEFILE(PinaraLogManager.PINARA_TRACEFILE), MYRA_CONSOLE(PinaraLogManager.MYRA_CONSOLE), MYRA_SUMMARYFILE(PinaraLogManager.MYRA_SUMMARYFILE), MYRA_TRACEFILE(PinaraLogManager.MYRA_TRACEFILE);

		private String value;

		private PinaraAppender(String value) {
			this.value = value;
		}
	}

	public static void setLogLevelMax(PinaraAppender appnderName, Level logLevel) {
		if (logLevel != null) {
			AppenderSkeleton appndr = (AppenderSkeleton) Logger.getRootLogger().getAppender(appnderName.value);
			LevelRangeFilter levelRangeFilter = (LevelRangeFilter) appndr.getFilter();
			levelRangeFilter.setLevelMax(logLevel);
			appndr.activateOptions();
		}
	}

	public static void setLogLevelMin(PinaraAppender appnderName, Level logLevel) {
		if (logLevel != null) {
			AppenderSkeleton appndr = (AppenderSkeleton) Logger.getRootLogger().getAppender(appnderName.value);
			if (appndr != null) {
				LevelRangeFilter levelRangeFilter = (LevelRangeFilter) appndr.getFilter();
				levelRangeFilter.setLevelMin(logLevel);
				appndr.activateOptions();
			}
		}
	}

	public static void setLogFileName(PinaraAppender appnderName, String fileNameAndPath) {
		if (fileNameAndPath != null) {
			RollingFileAppender appndr = (RollingFileAppender) Logger.getRootLogger().getAppender(appnderName.value);
			if (appndr != null) {
				appndr.setFile(fileNameAndPath);
				appndr.activateOptions();
			}
		}
	}

}
