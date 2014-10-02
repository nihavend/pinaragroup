package com.likya.pinara;

import java.util.HashMap;

import com.likya.pinara.comm.TcpManagementConsole;
import com.likya.pinara.gui.Authorization;
import com.likya.pinara.infobus.PinaraMailServer;
import com.likya.pinara.infobus.PinaraOutputManager;
import com.likya.pinara.infobus.PinaraSMSServer;
import com.likya.pinara.model.PinaraAuthorization;
import com.likya.pinara.model.User;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument.PinaraConfig;

public interface ConfigurationManager {
	
	public PinaraConfig getPinaraConfig();
	public void setPinaraConfig(PinaraConfig pinaraConfig);
	
	public User getUser();
	
	public void setAuthorizationList(HashMap<String, Authorization> authorizationList);
	public void setPinaraAuthorization(PinaraAuthorization pinaraAuthorization);
	
	public PinaraOutputManager getPinaraOutputManager();
	public void setPinaraOutputManager(PinaraOutputManager pinaraOutputManager);
	
	public PinaraSMSServer getPinaraSmsServer();
	public void setPinaraSmsServer(PinaraSMSServer pinaraSmsServer);
	
	public PinaraMailServer getPinaraMailServer();
	public void setPinaraMailServer(PinaraMailServer pinaraMailServer);
	
	public TcpManagementConsole getTcpManagementConsole();
	public void setTcpManagementConsole(TcpManagementConsole tcpManagementConsole);
	
	public HashMap<String, Authorization> getAuthorizationList();
}
