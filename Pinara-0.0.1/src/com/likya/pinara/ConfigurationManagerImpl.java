package com.likya.pinara;

import java.util.HashMap;

import com.likya.pinara.comm.TcpManagementConsole;
import com.likya.pinara.gui.Authorization;
import com.likya.pinara.infobus.PinaraMailServer;
import com.likya.pinara.infobus.PinaraOutputManager;
import com.likya.pinara.infobus.PinaraSMSServer;
import com.likya.pinara.mng.PinaraAppManager;
import com.likya.pinara.model.PinaraAuthorization;
import com.likya.pinara.model.User;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument.PinaraConfig;


public class ConfigurationManagerImpl implements ConfigurationManager {

	private PinaraConfig pinaraConfig;
	
	private PinaraAuthorization pinaraAuthorization;
	
	private HashMap<String, Authorization> authorizationList;
	
	private PinaraAppManager pinaraAppManager;
	
	private PinaraOutputManager pinaraOutputManager;
	
	private PinaraSMSServer pinaraSmsServer;
	
	private PinaraMailServer pinaraMailServer;
	
	private TcpManagementConsole tcpManagementConsole;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PinaraConfig getPinaraConfig() {
		return pinaraConfig;
	}

	public void setPinaraConfig(PinaraConfig pinaraConfig) {
		this.pinaraConfig = pinaraConfig;
	}

	public void setAuthorizationList(HashMap<String, Authorization> authorizationList) {
		this.authorizationList = authorizationList;
	}

	public void setPinaraAuthorization(PinaraAuthorization pinaraAuthorization) {
		this.pinaraAuthorization = pinaraAuthorization;
	}

	public TcpManagementConsole getTcpManagementConsole() {
		return tcpManagementConsole;
	}

	public void setTcpManagementConsole(TcpManagementConsole tcpManagementConsole) {
		this.tcpManagementConsole = tcpManagementConsole;
	}

	public PinaraSMSServer getPinaraSmsServer() {
		return pinaraSmsServer;
	}

	public void setPinaraSmsServer(PinaraSMSServer pinaraSmsServer) {
		this.pinaraSmsServer = pinaraSmsServer;
	}

	public PinaraMailServer getPinaraMailServer() {
		return pinaraMailServer;
	}

	public void setPinaraMailServer(PinaraMailServer pinaraMailServer) {
		this.pinaraMailServer = pinaraMailServer;
	}

	public PinaraAppManager getPinaraAppManager() {
		return pinaraAppManager;
	}

	public void setPinaraAppManager(PinaraAppManager pinaraAppManager) {
		this.pinaraAppManager = pinaraAppManager;
	}

	public HashMap<String, Authorization> getAuthorizationList() {
		return authorizationList;
	}

	public PinaraOutputManager getPinaraOutputManager() {
		return pinaraOutputManager;
	}

	public void setPinaraOutputManager(PinaraOutputManager pinaraOutputManager) {
		this.pinaraOutputManager = pinaraOutputManager;
	}

}
