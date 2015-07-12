package com.likya.pinara.gui;

import java.net.InetSocketAddress;

import com.likya.pinara.Pinara;
import com.likya.pinara.gui.rest.FlexHandler;
import com.likya.pinara.gui.rest.GraphViewHandler;
import com.likya.pinara.gui.rest.LogViewHandler;
import com.likya.pinara.gui.rest.RestUserOps;
import com.likya.pinara.gui.rest.RestfulHandler;
import com.likya.pinara.model.PinaraAuthorization;
import com.likya.pinara.model.User.StatuInfo;
import com.likya.pinara.utils.PasswordService;
import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class WebManager {

	private final String FLEX_CTX = "flex";
	private final String LOGVIEW_CTX = "logview";
	public final static String GRAPHVIEW_CTX = "graphview";
	public final static String RESTFUL_CTX = "flex/restsrvc";
	public final static String RESTUSEROPS_CTX = "flex/restsrv/userops";
	
	private int httpPort;
	private String hostName = "localhost";

//	private String sessionUserName;
//	private String sessionPassWord;

	private PinaraAuthorization pinaraAuthorization;

	MyAuthenticator myAuthenticator;

	public WebManager() {
		this.pinaraAuthorization = Pinara.getInstance().getConfigurationManager().getPinaraAuthorization();
		httpPort = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getHttpPort();
		hostName = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getServerIpAddress();
	}

	class MyAuthenticator extends BasicAuthenticator {

		public MyAuthenticator(String realm) {
			super(realm);
		}

//		public Result authenticate(HttpExchange t) {
//			HttpContext context = t.getHttpContext();
//			Headers rmap = (Headers) t.getRequestHeaders();
//			/*
//			 * look for auth token
//			 */
//			String auth = rmap.getFirst("Authorization");
//			if (auth == null) {
//				Headers map = (Headers) t.getResponseHeaders();
//				map.set("WWW-Authenticate", "Basic realm=" + "\"" + realm + "\"");
//				return new Authenticator.Retry(401);
//			}
//			int sp = auth.indexOf(' ');
//			if (sp == -1 || !auth.substring(0, sp).equals("Basic")) {
//				return new Authenticator.Failure(401);
//			}
//			byte[] b = Base64.base64ToByteArray(auth.substring(sp + 1));
//			String userpass = new String(b);
//			int colon = userpass.indexOf(':');
//			String uname = userpass.substring(0, colon);
//			String pass = userpass.substring(colon + 1);
//
//			if (checkCredentials(uname, pass)) {
//				return new Authenticator.Success(new HttpPrincipal(uname, realm));
//			} else {
//				/* reject the request again with 401 */
//
//				Headers map = (Headers) t.getResponseHeaders();
//				map.set("WWW-Authenticate", "Basic realm=" + "\"" + realm + "\"");
//				return new Authenticator.Failure(401);
//			}
//		}

		public Result authenticate(HttpExchange arg0) {
			// Aşağıdakiler debug için
			// Headers rmap = (Headers) arg0.getRequestHeaders();
			// String auth = rmap.getFirst("Authorization");
			// System.out.println(auth);
			
			Result superResult = super.authenticate(arg0);

			if (!(superResult instanceof Authenticator.Failure)) {
				return superResult;
			}

			return new Authenticator.Retry(400);
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
				if (username != null && password != null && (pinaraAuthorization.readUser(username) != null) && pinaraAuthorization.readUser(username).getStatuInfo() == StatuInfo.ACTIVE && encryptedPassword.equals(pinaraAuthorization.readUser(username).getPassword())) {
//					sessionUserName = username;
//					sessionPassWord = encryptedPassword;
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

		HttpContext flexContext = server.createContext("/" + FLEX_CTX, new FlexHandler());
		Pinara.getLogger().info(flexContext.getPath());

		HttpContext restfulContext = server.createContext("/" + RESTFUL_CTX, new RestfulHandler());

		HttpContext logDetailContext = server.createContext("/" + LOGVIEW_CTX, new LogViewHandler());
		HttpContext graphViewContext = server.createContext("/" + GRAPHVIEW_CTX, new GraphViewHandler());
		
		HttpContext restuseropsContext = server.createContext("/" + RESTUSEROPS_CTX, new RestUserOps());

		MyAuthenticator myAuthenticator = new MyAuthenticator("pinara");

		//flexContext.setAuthenticator(myAuthenticator);
		restfulContext.setAuthenticator(myAuthenticator);
		logDetailContext.setAuthenticator(myAuthenticator);
		graphViewContext.setAuthenticator(myAuthenticator);
		
		restuseropsContext.setAuthenticator(myAuthenticator);

		server.setExecutor(null); // creates a default executor
		server.start();

	}

	public int getHttpPort() {
		return httpPort;
	}

	public String getHostName() {
		return hostName;
	}

//	public String getSessionUserName() {
//		return sessionUserName;
//	}
//
//	public void setSessionUserName(String sessionUserName) {
//		this.sessionUserName = sessionUserName;
//	}
//
//	public String getSessionPassWord() {
//		return sessionPassWord;
//	}
//
//	public void setSessionPassWord(String sessionPassWord) {
//		this.sessionPassWord = sessionPassWord;
//	}

}
