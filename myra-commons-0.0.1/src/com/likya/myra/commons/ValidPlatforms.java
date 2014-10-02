/*
 * TlosFaz2
 * com.likya.tlos.core.spc.helpers : ValidPlatforms.java
 * @author Serkan Taş
 * Tarih : 09.Kas.2008 23:40:05
 */

package com.likya.myra.commons;


public class ValidPlatforms {
	
	public static final String OS_WINDOWS = "Windows"; 
	private static final String OS_WINDOWS7 = OS_WINDOWS + " " + "7";  
	private static final String OS_WINDOWS_VISTA = OS_WINDOWS + " " + "Vista";  
	private static final String OS_WINDOWS_2003 = OS_WINDOWS + " " + "2003";  
	private static final String OS_WINDOWS_NT = OS_WINDOWS + " " + "NT";  
	private static final String OS_WINDOWS_95 = OS_WINDOWS + " " + "95";  
	private static final String OS_WINDOWS_XP = OS_WINDOWS + " " + "XP";  
	private static final String OS_HP_UX = "HP-UX"; 
	private static final String OS_AIX = "AIX"; 
	private static final String OS_LINUX = "Linux"; 
	private static final String OS_SUNOS = "SunOS"; 
	private static final String MAC_OS_X = "Mac OS X"; 
	
	private static boolean osValid = true;
	
	public static boolean isOSValid() {
//		if (getCommand("") != null) {
//			return true;
//		}
//		return false;
		return osValid;
	}

	public static String getOSName() {
		return System.getProperty("os.name"); 
	}
	
	// TODO Bu kısım değişmeli!
	/**
	 * Aslında aşağıdaki gibi bir liste olmamalı. Sadece deploy edilecek sisteme
	 * ait lisans olmalı
	 */
	public static String[] getCommand(String jobCommand) {
		
//		if(TlosServer.getTlosParameters().getJobCommand() != null && TlosServer.getTlosParameters().getJobCommand().length != 0) {
//			TlosServer.println(LocaleMessages.getString("TlosServer.71") + " " + TlosServer.getTlosParameters().getJobCommand()); 
//			return TlosServer.getTlosParameters().getJobCommand();
//		}

		String osName = System.getProperty("os.name"); 
		String[] cmd;

		if (osName.equals(OS_WINDOWS7)) {
			String[] tmpCmd = new String[3];
			tmpCmd[0] = jobCommand;
			tmpCmd[1] = ""; 
			tmpCmd[2] = ""; 
			cmd = tmpCmd;
		} else if (osName.equals(OS_WINDOWS_VISTA)) {
			String[] tmpCmd = new String[3];
			tmpCmd[0] = jobCommand;
			tmpCmd[1] = ""; 
			tmpCmd[2] = ""; 
			cmd = tmpCmd;
		} else if (osName.equals(OS_WINDOWS_2003)) {
			String[] tmpCmd = new String[3];
			tmpCmd[0] = jobCommand;
			tmpCmd[1] = ""; 
			tmpCmd[2] = ""; 
			cmd = tmpCmd;
		} else if (osName.equals(OS_WINDOWS_NT)) {
			String[] tmpCmd = new String[3];
			tmpCmd[0] = "cmd.exe"; 
			tmpCmd[1] = "/C"; 
			tmpCmd[2] = jobCommand;
			cmd = tmpCmd;
		} else if (osName.equals(OS_WINDOWS_95)) {
			String[] tmpCmd = new String[3];
			tmpCmd[0] = "command.com"; 
			tmpCmd[1] = "/C"; 
			tmpCmd[2] = jobCommand;
			cmd = tmpCmd;
		} else if (osName.equals(OS_WINDOWS_XP)) {
			String[] tmpCmd = new String[3];
			tmpCmd[0] = "cmd.exe"; 
			tmpCmd[1] = "/C"; 
			tmpCmd[2] = jobCommand;
			cmd = tmpCmd;
		} else if (osName.equals(OS_HP_UX)) {
			String[] tmpCmd = new String[2];
			tmpCmd[0] = "/bin/sh"; 
			tmpCmd[1] = jobCommand;
			cmd = tmpCmd;
		} else if (osName.equals(OS_AIX)) {
			String[] tmpCmd = new String[2];
			tmpCmd[0] = "/bin/sh"; 
			tmpCmd[1] = jobCommand;
			cmd = tmpCmd;
		} else if (osName.equals(OS_LINUX)) {
			String[] tmpCmd = new String[2];
			tmpCmd[0] = "/bin/sh"; 
			tmpCmd[1] = jobCommand;
			cmd = tmpCmd;
		}  else if (osName.equals(OS_SUNOS)) {
			String[] tmpCmd = new String[2];
			tmpCmd[0] = "/bin/sh"; 
			tmpCmd[1] = jobCommand;
			cmd = tmpCmd;
		}  else if (osName.equals(MAC_OS_X)) {
			String[] tmpCmd = new String[2];
			tmpCmd[0] = "/bin/sh"; 
			tmpCmd[1] = jobCommand;
			cmd = tmpCmd;
		}  else {
			if (osName.indexOf("Windows") != -1) { 
				String[] tmpCmd = new String[3];
				tmpCmd[0] = "cmd.exe"; 
				tmpCmd[1] = "/C"; 
				tmpCmd[2] = jobCommand;
				cmd = tmpCmd;
			} else {
				String[] tmpCmd = new String[2];
				tmpCmd[0] = "/bin/sh"; 
				tmpCmd[1] = jobCommand;
				cmd = tmpCmd;
			}
			osValid = false;
		}
		
		if(!osValid) { 
			// TlosServer.println(osName + LocaleMessages.getString("ValidPlatforms.0")); 
		} else {
			// TlosServer.println(LocaleMessages.getString("LicenseManager.19") + " " + osName); 
		}

		return cmd;
	}

}

