package com.likya.pinara.utils.license;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.likya.commons.utils.DateUtils;
import com.likya.pinara.Pinara;
import com.likya.pinara.model.LicenseInfo;

public class LicenseManager {

	private static boolean expired = false;

	public static boolean isExpired() {
		return expired;
	}

	public static boolean isLicenseValid(LicenseInfo licenseInfo) throws Exception {

		try {

			String licenceTrail = ValidateLicense.getLicenseStringBuffer();
			licenseInfo.init(licenceTrail);
			if(!Pinara.getVersion().equals(licenseInfo.getVersionId())) {
				Pinara.println(Pinara.getMessage("LicenseManager.0") + Pinara.getVersion() + Pinara.getMessage("LicenseManager.1") + licenseInfo.getVersionId());
				return false;
			}
			Pinara.println(Pinara.getMessage("LicenseManager.2") + licenseInfo.getCopyRight());
			Pinara.println(Pinara.getMessage("LicenseManager.2") + licenseInfo.getOwnerInfo());
			Pinara.println(Pinara.getMessage("LicenseManager.2") + licenseInfo.getCounty() + ", " + licenseInfo.getCity() + ", " + licenseInfo.getCountry());
			Pinara.println(Pinara.getMessage("LicenseManager.2") + licenseInfo.getProductName() + " " + licenseInfo.getVersionId());
			Pinara.println(Pinara.getMessage("LicenseManager.9") + licenseInfo.getClientName() + Pinara.getMessage("LicenseManager.10") + licenseInfo.getClientId());

			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			Date expireDate = formatter.parse(licenseInfo.getExpireDate());
			if (!licenseInfo.getLicenseType().equals("" + 12) && expireDate.before(Calendar.getInstance().getTime())) {
				expired = true;
				return false;
			}
			
			long timeToExpire = DateUtils.daysBetween(Calendar.getInstance().getTime(), expireDate);
			if(!licenseInfo.getLicenseType().equals("" + 12) && (++timeToExpire < 15)){
				Pinara.println(Pinara.getMessage("LicenseManager.14") + timeToExpire + Pinara.getMessage("LicenseManager.15"));
			}

			String osName = licenseInfo.getOperatingSystem();
			if (!osName.equals(System.getProperty("os.name"))) {
				Pinara.println(Pinara.getMessage("LicenseManager.14") + osName + Pinara.getMessage("LicenseManager.18"));
				Pinara.println(Pinara.getMessage("LicenseManager.3") + osName);
				Pinara.println(Pinara.getMessage("LicenseManager.4") + System.getProperty("os.name"));
				return false;
			} else {
				Pinara.println(Pinara.getMessage("LicenseManager.19") + osName);
			}

			String licenseCmd = licenseInfo.getCommand();
			String licenseKey = licenseInfo.getKey();

			try {
				// String cmdArr[] = ValidPlatforms.getCommand(licenseCmd);
				Process process = Runtime.getRuntime().exec(licenseCmd);
				LicenseGrabber outputGobbler = new LicenseGrabber(process.getInputStream());
				outputGobbler.setName("License.OutputGobbler.id." + outputGobbler.getId());

				//LicenseGrabber errorGobbler = new LicenseGrabber(process.getInputStream());
				// errorGobbler.setName("License.ErrorGobbler.id." + errorGobbler.getId());

				outputGobbler.start();
				// errorGobbler.start();
				
				process.waitFor();
				int processExitValue = process.exitValue();
				int timeOut = 0;
				while(true && timeOut < 20) {
					if(!outputGobbler.getMessage().equals("")) {
						break;
					}
					Thread.sleep(100);
					timeOut++;
					
				}
				if (processExitValue == 0) {
					String outputBuffer = outputGobbler.getMessage();
					if (outputBuffer.indexOf(licenseKey) < 0) {
						Pinara.println(Pinara.getMessage("LicenseManager.22"));
						return false;
					}
				} else {
					Pinara.println(Pinara.getMessage("LicenseManager.23"));
					return false;
				}
				
				outputGobbler = null;
				// errorGobbler = null;
				
			} catch (Exception e) {
				Pinara.println(Pinara.getMessage("LicenseManager.24"));
				return false;
			}

			if (!licenseInfo.getLicenseType().equals("" + 12)) {
				Pinara.println(Pinara.getMessage("LicenseManager.2") + Pinara.getMessage("LicenseManager.27") + licenseInfo.getExpireDate());
			}
			
		} catch (FileNotFoundException fnfe) {

			Pinara.println(fnfe.getLocalizedMessage());
			Pinara.println(Pinara.getMessage("LicenseManager.28"));

			return false;
		}

		return true;
	}
}
