package com.likya.pinara.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;

import com.likya.commons.utils.FileUtils;
import com.likya.pinara.Pinara;
import com.likya.xsd.myra.model.joblist.AbstractJobType;

public class PinaraFileUtils extends FileUtils {

	public static StringBuffer readTextFile(ArrayList<Long> fileSize, long beginPos, long endPos, String fileName, String coloredLineIndicator, boolean cleanEscapeChars, int numOfPages, boolean hasLineNums) throws Throwable {

		final int CR = 0x0D;
		final int LF = 0x0A;

		StringBuffer outputBuffer = new StringBuffer();

		//try {

			File file = new File(fileName);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			fileSize.add(new Long(randomAccessFile.length()));
			//System.err.println("File Size : " + fileSize);

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			if (beginPos == -1 || endPos == -1) {
				// TODO TlosServer.getTlosParameters().getLogPageSize()
				if (numOfPages == -1) {
					beginPos = 0;
				} else {
					beginPos = Math.max(0, randomAccessFile.length() - numOfPages * 450);
				}
				endPos = randomAccessFile.length();
			}

			if (endPos > randomAccessFile.length() || endPos < 0) {
				endPos = randomAccessFile.length();
			}

			if ((endPos - beginPos) > 1000000) {
				beginPos = endPos - 1000000;
			}

			randomAccessFile.seek(beginPos);

			long counter = 0;

			while (randomAccessFile.getFilePointer() < endPos) {

				byte readByte = randomAccessFile.readByte();
				// Byte to string !
				// System.out.print(new String(new byte[] {readByte}));
				if (readByte == -1) {
					break;
				}

				byteArrayOutputStream.write(readByte);

				if (readByte == CR) {
					// System.out.print("CR");
				}

				if (readByte == LF) {
					// System.out.print("LF");
				}

				if (readByte == CR || readByte == LF) {

					if (randomAccessFile.getFilePointer() < endPos) {
						long tmpPointer = randomAccessFile.getFilePointer();
						byte readTmpByte = randomAccessFile.readByte();

						if (readTmpByte != -1) {
							if (readByte == CR && readTmpByte == LF) {
								// System.out.print("LF");
								byteArrayOutputStream.write(readTmpByte);
							} else {
								randomAccessFile.seek(tmpPointer);
							}
						} else {
							randomAccessFile.seek(tmpPointer);
							continue;
						}
					}

					if (hasLineNums) {
						outputBuffer.append(++counter + ":\t" + byteArrayOutputStream.toString(/*"UTF8"*/));
					} else {
						outputBuffer.append(byteArrayOutputStream.toString(/*"UTF8"*/));
					}

					//System.out.print("byteArrayOutputStream : " + byteArrayOutputStream.toString(/*"UTF8"*/));

					byteArrayOutputStream.reset();

				}

			}

			if (/*outputBuffer.length() == 0 && */byteArrayOutputStream.toString(/*"UTF8"*/).length() != 0) {
				// Tek satırlık ve satır sonunda CR-LF olmayan loglar için
				outputBuffer.append(byteArrayOutputStream.toString(/*"UTF8"*/));
				byteArrayOutputStream.reset();
			}

			try {
				randomAccessFile.close();
			} catch (IOException e) {
			}

		//} catch (FileNotFoundException fnfex) {
		//	return new StringBuffer(stackTrace(fnfex));
		//} catch (Throwable t) {
		//	if (t instanceof java.lang.OutOfMemoryError) {
		//		Pinara.println("Dosya boyutu kapasitenin üstünde, açılamıyor !");
		//	} else {
		//		t.printStackTrace();
		//	}
		//	return null;
		//}

		if (cleanEscapeChars) {
			return new StringBuffer(StringEscapeUtils.escapeHtml(outputBuffer.toString()));
		}

		return outputBuffer;
	}

	public static StringBuffer readXMLFile(ArrayList<Long> fileSize, long beginPos, long endPos, String fileName, String coloredLineIndicator, boolean cleanEscapeChars, int numOfPages) throws Throwable {

		final String propertyDescriptor = "<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">";

		StringBuffer stringBuffer = PinaraFileUtils.readTextFile(fileSize, beginPos, endPos, fileName, coloredLineIndicator, cleanEscapeChars, numOfPages, false);

		int pos = -1;
		if (stringBuffer != null && (pos = stringBuffer.indexOf(propertyDescriptor)) != -1) {
			stringBuffer.replace(pos, propertyDescriptor.length() + pos, "");
		}

		return stringBuffer;
	}

	public static StringBuffer readTxtFile(String fileName, String coloredLineIndicator, boolean cleanEscapeChars) {

		FileInputStream fis = null;
		BufferedReader bufferedReader = null;
		StringBuffer outputBuffer = new StringBuffer();

		try {
			fis = new FileInputStream(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(fis);
			bufferedReader = new BufferedReader(inputStreamReader);
			String bufferString = ""; //$NON-NLS-1$

			while ((bufferString = bufferedReader.readLine()) != null) {

				if (cleanEscapeChars) {
					bufferString = StringEscapeUtils.escapeHtml(bufferString);
				}

				if (bufferString.toUpperCase().indexOf(coloredLineIndicator.toUpperCase()) >= 0) {
					outputBuffer.append("<font color=\"red\">"); //$NON-NLS-1$
					outputBuffer.append(bufferString);
					outputBuffer.append("</font>" + '\n'); //$NON-NLS-1$
				} else {
					outputBuffer.append(bufferString + '\n');
				}
			}
		} catch (FileNotFoundException fnfex) {
			return null;
		} catch (Throwable t) {
			if (t instanceof java.lang.OutOfMemoryError) {
				Pinara.println("Dosya boyutu kapasitenin üstünde, açılamıyor !");
			} else {
				t.printStackTrace();
			}
			return null;
		}

		try {
			fis.close();
		} catch (IOException e) {
		}

		return outputBuffer;
	}

	public static String stackTrace(Throwable throwable) {

		String retValue = null;

		if (throwable != null) {
			StringWriter errors = new StringWriter();
			throwable.printStackTrace(new PrintWriter(errors));
			retValue = errors.toString();
		}

		return retValue;
	}

	public static boolean checkLogFile(AbstractJobType abstractJobType) {
		return checkFile(abstractJobType, true);
	}
	
	public static boolean checkJobFile(AbstractJobType abstractJobType) {
		return checkFile(abstractJobType, false);
	}
	
	public static boolean checkFile(AbstractJobType abstractJobType, boolean isLog) {
		
		String jobWorkDir = null;
		String fileName = null;
		
		if(isLog) {
			jobWorkDir = abstractJobType.getBaseJobInfos().getJobLogPath();
			fileName = abstractJobType.getBaseJobInfos().getJobLogFile();
		} else {
			jobWorkDir = abstractJobType.getBaseJobInfos().getJobTypeDetails().getJobWorkDir();
			fileName = abstractJobType.getBaseJobInfos().getJobTypeDetails().getJobCommand();
		}
		
		if (jobWorkDir.endsWith(File.separator) || fileName.startsWith(File.separator)) {
			fileName = jobWorkDir + fileName;
		} else {
			fileName = jobWorkDir + File.separator + fileName;
		}
		
		return checkFile(fileName);
	}
	
	public static StringBuffer readFile(String fileName) {

		FileInputStream fis = null;
		BufferedReader bufferedReader = null;
		StringBuffer outputBuffer = new StringBuffer();

		try {
			fis = new FileInputStream(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String bufferString = null;

			while ((bufferString = bufferedReader.readLine()) != null) {
				outputBuffer.append(bufferString + '\n');
			}
		} catch (FileNotFoundException fnfex) {
			fnfex.printStackTrace();
			return null;
		} catch (Throwable t) {
			if(t instanceof  java.lang.OutOfMemoryError) {
				System.err.println("Dosya boyutu kapasitenin üstünde, açılamıyor !");
			} else {
				t.printStackTrace();
			}
			return null;
		}

		try {
			fis.close();
		} catch (IOException e) {}
		
		return outputBuffer;
	}

	public static String readVersionInfo(String versionFile) {
		StringBuffer versionBuff = FileUtils.readFile(versionFile);
		String versionInfo = (versionBuff != null && versionBuff.indexOf("=") != -1) ? versionBuff.substring(versionBuff.indexOf("=") + 1, versionBuff.length() - 1) : versionBuff+"";
		
		return versionInfo;
	}
}
