package com.likya.pinara.gui;

import java.net.InetSocketAddress;
import java.util.HashMap;

import com.likya.pinara.Pinara;
import com.likya.pinara.rest.FlexHandler;
import com.likya.pinara.rest.GraphViewHandler;
import com.likya.pinara.rest.LogViewHandler;
import com.likya.pinara.rest.RestfulHandler;
import com.likya.pinara.utils.PasswordService;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpServer;

public class WebManager {

	private final String FLEX_CTX = "flex";
	private final String LOGVIEW_CTX = "logview";
	public final static String GRAPHVIEW_CTX = "graphview";
	public final static String RESTFUL_CTX = "flex/restsrvc";

	private int httpPort;
	private String hostName = "localhost";

	private String sessionUserName;
	private String sessionPassWord;

	private HashMap<String, Authorization> authorizationList;

	MyAuthenticator myAuthenticator;

	public WebManager() {
		this.authorizationList = Pinara.getInstance().getConfigurationManager().getAuthorizationList();
		httpPort = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getHttpPort();
		hostName = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getServerIpAddress();
	}

	class MyAuthenticator extends BasicAuthenticator {

		public MyAuthenticator(String realm) {
			super(realm);
		}

		public boolean checkCredentials(String username, String password) {

			try {
				String encryptedPassword;
				try {
					encryptedPassword = PasswordService.encrypt(password);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				if (username != null && password != null && authorizationList.containsKey(username) && encryptedPassword.equals(authorizationList.get(username).getPassWord())) {
					sessionUserName = username;
					sessionPassWord = encryptedPassword;
					return true;
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			return false;
		}
	}

	public void initServer() throws Exception {

		HttpServer server;
		server = HttpServer.create(new InetSocketAddress(hostName, httpPort), 5);

		//HttpContext flexContext = 
				server.createContext("/" + FLEX_CTX, new FlexHandler());
		// Pinara.getLogger().info(flexContext.getPath());
 
		// HttpContext restfulContext = 
				server.createContext("/" + RESTFUL_CTX, new RestfulHandler());

		// HttpContext logDetailContext = 
				server.createContext("/" + LOGVIEW_CTX, new LogViewHandler());
		// HttpContext graphViewContext =
				server.createContext("/" + GRAPHVIEW_CTX, new GraphViewHandler());
				
//		TODO Later !!!!
//		MyAuthenticator myAuthenticator = new MyAuthenticator("pinara");
//		restfulContext.setAuthenticator(myAuthenticator);

		server.setExecutor(null); // creates a default executor
		server.start();

	}

	public int getHttpPort() {
		return httpPort;
	}

	public String getHostName() {
		return hostName;
	}

	public String getSessionUserName() {
		return sessionUserName;
	}

	public void setSessionUserName(String sessionUserName) {
		this.sessionUserName = sessionUserName;
	}

	public String getSessionPassWord() {
		return sessionPassWord;
	}

	public void setSessionPassWord(String sessionPassWord) {
		this.sessionPassWord = sessionPassWord;
	}

}
