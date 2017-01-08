package com.likya.myra.commons.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.likya.commons.utils.LimitedArrayList;
import com.likya.commons.utils.ReverseLineInputStream;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.wlagen.ActionDocument.Action;
import com.likya.xsd.myra.model.wlagen.DirectionType;
import com.likya.xsd.myra.model.wlagen.EventDocument.Event;
import com.likya.xsd.myra.model.wlagen.LogAnalysisDocument.LogAnalysis;
import com.likya.xsd.myra.model.wlagen.ModeType;

public class LogAnalyser {
	
	public LogAnalyser() {
		super();
	}

	private LogAnalysis logAnalysis;
	
	private String defaultLogContent = "";
	
	private LimitedArrayList<Object> limitedArrayList = new LimitedArrayList<Object>();
	private int logLineNumBack = 0;
	private int logLineNumForward = 0;
	
	public LiveStateInfo evaluate(AbstractJobType abstractJobType, StringBuffer logContent) {

		LiveStateInfo liveStateInfo = null;
		
		logAnalysis = abstractJobType.getLogAnalysis();
		
		Action logAction = logAnalysis.getAction();
				
		if (logAction.getThencase() != null) {
			parseEvent(logAction.getThencase().getEvent());
		} else if (logAction.getElsecase() != null) {
			parseEvent(logAction.getElsecase().getEvent());
		}

		if(logAction.getThencase() != null || logAction.getElsecase() != null) {
			
			boolean result = false;

			try {
				// Evaluate log analyzing procedures.

				String filePath = abstractJobType.getBaseJobInfos().getJobLogPath();
				String fileName = abstractJobType.getBaseJobInfos().getJobLogFile();

				File sourceFile = new File(filePath + File.separator + fileName);

				int direction = logAnalysis.getFindWhat().getDirection().intValue();

				boolean matcWholeWordOnly = logAnalysis.getFindWhat().getMatchWholeWordOnly();
				boolean isCaseSensitive = logAnalysis.getFindWhat().getMatchCase();

				String searchString = logAnalysis.getFindWhat().getStringValue();

				int modeType = logAnalysis.getFindWhat().getMode().intValue();

				if (matcWholeWordOnly) {
					result = matcWholeWordOnly(sourceFile, limitedArrayList, logLineNumForward, searchString, isCaseSensitive, direction, modeType);
				} else {
					result = matcWord(sourceFile, limitedArrayList, logLineNumForward, searchString, isCaseSensitive, direction, modeType);
				}

			} catch (UnsupportedOperationException uoe) {
				uoe.printStackTrace();
			}
			
			for(Object text : limitedArrayList.toArray()) {
				defaultLogContent += text.toString() + "\n";
			}
			
			logContent.append(defaultLogContent);
			
			if(result) {
				liveStateInfo = logAction.getThencase().getForcedResult().getLiveStateInfo();
			} else {
				liveStateInfo = logAction.getElsecase().getForcedResult().getLiveStateInfo();
			}
			
		}
		
		return liveStateInfo;

	}
	
	private void parseEvent(Event myEvent) {

		if (myEvent != null && myEvent.getContent() != null) {
			defaultLogContent = myEvent.getContent().getStringValue() + "\n";
			if (myEvent.getContent().getLogLineNumBack() != null) {
				logLineNumBack = myEvent.getContent().getLogLineNumBack().intValue();
			}
			if (myEvent.getContent().getLogLineNumForward() != null) {
				logLineNumForward = myEvent.getContent().getLogLineNumForward().intValue();
			}
		}

		limitedArrayList.setMaxLength(logLineNumBack + logLineNumForward + 1);
	}

	private static boolean matcWord(File sourceFile, AbstractCollection<Object> collection, int logLineNumForward, String searchString, boolean isCaseSensitive, int direction, int modeType) {

		boolean retValue = false;

		switch (direction) {

		case DirectionType.INT_DOWN:
			retValue = find(sourceFile, collection, logLineNumForward, searchString, isCaseSensitive, modeType);
			break;

		case DirectionType.INT_UP:
			retValue = reverseFind(sourceFile, collection, logLineNumForward, " " + searchString + " ", isCaseSensitive, modeType);
			break;

		default:
			throw new UnsupportedOperationException();
		}

		return retValue;

	}

	private static boolean matcWholeWordOnly(File sourceFile, AbstractCollection<Object> collection, int logLineNumForward, String searchString, boolean isCaseSensitive, int direction, int modeType) {

		boolean retValue = false;

		switch (direction) {

		case DirectionType.INT_DOWN:
			retValue = find(sourceFile, collection, logLineNumForward, " " + searchString + " ", isCaseSensitive, modeType);
			break;

		case DirectionType.INT_UP:
			retValue = reverseFind(sourceFile, collection, logLineNumForward, " " + searchString + " ", isCaseSensitive, modeType);
			break;

		default:
			throw new UnsupportedOperationException();
		}

		return retValue;
	}

	public static boolean find(File f, AbstractCollection<Object> collection, int logLineNumForward, String searchString, boolean isCaseSensitive, int modeType) {

		boolean result = false;

		Scanner in = null;

		try {

			in = new Scanner(new FileReader(f));

			int fwCounter = 0;
			while (in.hasNextLine() && (fwCounter < logLineNumForward)) {

				String myLine = in.nextLine();

				if (!result) {
					switch (modeType) {

					case ModeType.INT_NORMAL:
						result = searchNormal(myLine, searchString, isCaseSensitive);
						break;
					case ModeType.INT_REG_EX:
						result = searchRegEx(myLine, searchString, isCaseSensitive);
						break;
					default:
						throw new UnsupportedOperationException();
					}
				} else {
					fwCounter++;
				}

				collection.add(myLine);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) { /* ignore */
			}
		}

		return result;

	}

	public static boolean reverseFind(File f, AbstractCollection<Object> collection, int logLineNumForward, String searchString, boolean isCaseSensitive, int modeType) {

		boolean result = false;

		Scanner in = null;

		try {

			ReverseLineInputStream reverseLineInputStream = new ReverseLineInputStream(f);

			in = new Scanner(reverseLineInputStream);

			int fwCounter = 0;
			while (in.hasNextLine() && (fwCounter < logLineNumForward)) {

				String myLine = in.nextLine();

				if (!result) {
					switch (modeType) {

					case ModeType.INT_NORMAL:
						result = searchNormal(myLine, searchString, isCaseSensitive);
						break;
					case ModeType.INT_REG_EX:
						result = searchRegEx(myLine, searchString, isCaseSensitive);
						break;
					default:
						throw new UnsupportedOperationException();
					}

				} else {
					fwCounter++;
				}

				collection.add(myLine);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) { /* ignore */
			}
		}

		return result;

	}

	private static boolean searchNormal(String source, String key, boolean isCaseSensitive) {

		boolean result = false;

		if (isCaseSensitive) {
			result = source.indexOf(key) >= 0;
		} else {
			result = source.indexOf(key.toUpperCase()) >= 0;
		}

		return result;
	}

	private static boolean searchRegEx(String source, String key, boolean isCaseSensitive) {

		boolean result = false;
		Pattern pattern = null;

		if (isCaseSensitive) {
			pattern = Pattern.compile(key);
		} else {
			pattern = Pattern.compile(key, Pattern.CASE_INSENSITIVE);
		}

		Matcher matcher = pattern.matcher(source);

		result = matcher.find();

		return result;
	}
}
