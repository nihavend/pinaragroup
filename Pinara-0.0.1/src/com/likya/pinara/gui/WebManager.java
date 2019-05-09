package com.likya.pinara.gui;

import java.net.InetSocketAddress;

import com.likya.pinara.Pinara;
import com.likya.pinara.gui.rest.FlexHandler;
import com.likya.pinara.gui.rest.GraphViewHandler;
import com.likya.pinara.gui.rest.LogViewHandler;
import com.likya.pinara.gui.rest.RestUserOps;
import com.likya.pinara.gui.rest.RestfulHandler;
import com.likya.pinara.gui.rest.RoyaleHandler;
import com.likya.pinara.model.PinaraAuthorization;
import com.likya.pinara.model.User.StatuInfo;
import com.likya.pinara.utils.PasswordService;
import com.sun.net.httpserver.Authenticator;
// Kaynak kodundan alıp paket altına taşıdım :
// http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/com/sun/net/httpserver
// import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class WebManager {

	
	private final String LOGVIEW_CTX = "logview";
	public final static String GRAPHVIEW_CTX = "graphview";

	private final static String FLEX_CTX = "flex";
	private final static String ROYALE_CTX = "PInara";
	
	// private final static String ACTIVE_CTX = FLEX_CTX;
	private final static String ACTIVE_CTX = ROYALE_CTX;

	// public final static String RESTFUL_CTX = "flex/restsrvc";
	// public final static String RESTUSEROPS_CTX = "flex/restsrv/userops";
	// public final static String RESTFUL_CTX = "PInara/restsrvc";
	// public final static String RESTUSEROPS_CTX = "PInara/restsrv/userops";

	public final static String RESTFUL_CTX = ACTIVE_CTX + "/restsrvc";
	public final static String RESTUSEROPS_CTX = ACTIVE_CTX + "/restsrv/userops";
	
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

		/*
		 * Aşağıdaki metod, internalleri ile test etmek amaçklı bırakıldı
		 * (non-Javadoc)
		 * @see com.sun.net.httpserver.BasicAuthenticator#authenticate(com.sun.net.httpserver.HttpExchange)
		 */
		/*
		public Result authenticate(HttpExchange t) {
			
			// HttpContext context = t.getHttpContext();
			com.sun.net.httpserver.Headers rmap = (com.sun.net.httpserver.Headers) t.getRequestHeaders();
			
			// look for auth token
			
			String auth = rmap.getFirst("Authorization");
			if (auth == null) {
				com.sun.net.httpserver.Headers map = (com.sun.net.httpserver.Headers) t.getResponseHeaders();
				map.set("WWW-Authenticate", "Basic realm=" + "\"" + realm + "\"");
				return new Authenticator.Retry(401);
			}
			int sp = auth.indexOf(' ');
			if (sp == -1 || !auth.substring(0, sp).equals("Basic")) {
				return new Authenticator.Failure(401);
			}
			// Çalışmadı aşağıdakini denedim : byte[] b = Base64.base64ToByteArray(auth.substring(sp + 1));
			byte[] b = null;
			try {
				b = Base64.getDecoder().decode(new String(auth.substring(sp + 1)).getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String userpass = new String(b);
			int colon = userpass.indexOf(':');
			String uname = userpass.substring(0, colon);
			String pass = userpass.substring(colon + 1);

			if (checkCredentials(uname, pass)) {
				return new Authenticator.Success(new com.sun.net.httpserver.HttpPrincipal(uname, realm));
			} else {
				// reject the request again with 401

				com.sun.net.httpserver.Headers map = (com.sun.net.httpserver.Headers) t.getResponseHeaders();
				map.set("WWW-Authenticate", "Basic realm=" + "\"" + realm + "\"");
				return new Authenticator.Failure(401);
			}
		}
		*/
		
		public Result authenticate(HttpExchange arg0) {

			// Aşağıdakiler debug için
			// com.sun.net.httpserver.Headers rmap = (com.sun.net.httpserver.Headers) arg0.getRequestHeaders();
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

		HttpContext royaleContext = server.createContext("/" + ROYALE_CTX, new RoyaleHandler());
		Pinara.getLogger().info(royaleContext.getPath());

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
