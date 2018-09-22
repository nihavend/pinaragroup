package com.likya.pinara.test.clientlicense;


import com.likya.pinara.utils.license.LicenseClientUtil;

public class ClientLicenseTester {
	
	public static void main(String[] args) throws Exception {

		LicenseClientUtil.serialize("01012015");
		
		String desSerStr = LicenseClientUtil.deserialize();
		
		System.out.println(desSerStr);
		
	}

}
