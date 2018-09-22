package com.likya.myra.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestReadConsole {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuffer strBuff = new StringBuffer();

		strBuff.append(br.readLine());


		boolean innerLoop = true;

		while (innerLoop) {

			strBuff.append(br.readLine());

			if(strBuff.toString().contains("</myra:genericJob>") && strBuff.toString().contains("</myra:jobList>")) {
				System.out.println("İş alındı doğrulanıyor...");
				break;
			}
			
			if(strBuff.toString().contains("İPTAL")) {
				System.out.println("İptal edildi bir üst menuye çıkıyor...");
				break;
			}
		}

		System.out.println();
		System.out.println(strBuff);
	}

}
