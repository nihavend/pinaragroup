package com.likya.bugreport.JDK_8199849;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings({ "restriction", "unused" })
public class BugReport_JDK_8199849 {

	public static void main(String[] args) {

		
		HttpServer server = null;
		
		try {
			server = HttpServer.create(new InetSocketAddress("localhost", 8080), 5);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpContext restfulContext = server.createContext("/testbug", new RestHandler());


		// com.sun.net.httpserver.BasicAuthenticator myAuthenticator = new com.sun.net.httpserver.BasicAuthenticator("testbug") {
		BasicAuthenticator myAuthenticator = new BasicAuthenticator("testbug") {
			
			@Override
			public boolean checkCredentials(String username, String password) {
				
				try {
					String encryptedPassword;
					try {
						encryptedPassword = PasswordService.encrypt(password);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
					System.out.println("BugReport_JDK_8199849: username " + username);
					System.out.println("BugReport_JDK_8199849: password " + password);
					if (username != null && password != null && "pınara".equals(username)) {
//						sessionUserName = username;
//						sessionPassWord = encryptedPassword;
						return true;
					}
				} catch (Throwable t) {
					t.printStackTrace();
				}
				
				return false;
			}
		};

		restfulContext.setAuthenticator(myAuthenticator);
		server.setExecutor(null); // creates a default executor
		server.start();
		
	}

}
