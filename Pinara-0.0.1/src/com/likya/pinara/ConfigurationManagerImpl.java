package com.likya.pinara;

import com.likya.pinara.comm.TcpManagementConsole;
import com.likya.pinara.infobus.PinaraMailServer;
import com.likya.pinara.infobus.PinaraOutputManager;
import com.likya.pinara.infobus.PinaraSMSServer;
import com.likya.pinara.mng.PinaraAppManager;
import com.likya.pinara.model.PinaraAuthorization;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument.PinaraConfig;


public class ConfigurationManagerImpl implements ConfigurationManager {

	private PinaraConfig pinaraConfig;
	
	private PinaraAuthorization pinaraAuthorization;
	
	private PinaraAppManager pinaraAppManager;
	
	private PinaraOutputManager pinaraOutputManager;
	
	private PinaraSMSServer pinaraSmsServer;
	
	private PinaraMailServer pinaraMailServer;
	
	private TcpManagementConsole tcpManagementConsole;
	
	// private User sessionUser;

	public PinaraConfig getPinaraConfig() {
		return pinaraConfig;
	}

	public void setPinaraConfig(PinaraConfig pinaraConfig) {
		this.pinaraConfig = pinaraConfig;
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

	public PinaraOutputManager getPinaraOutputManager() {
		return pinaraOutputManager;
	}

	public void setPinaraOutputManager(PinaraOutputManager pinaraOutputManager) {
		this.pinaraOutputManager = pinaraOutputManager;
	}

//	public User getSessionUser() {
//		return sessionUser;
//	}
//
//	public void setSessionUser(User sessionUser) {
//		this.sessionUser = sessionUser;
//	}

	public PinaraAuthorization getPinaraAuthorization() {
		return pinaraAuthorization;
	}

	public void setPinaraAuthorization(PinaraAuthorization pinaraAuthorization) {
		this.pinaraAuthorization = pinaraAuthorization;
	}

}
