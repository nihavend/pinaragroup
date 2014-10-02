package com.likya.pinara.comm;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.likya.myra.jef.core.CoreFactory;
import com.likya.pinara.Pinara;
import com.likya.pinara.mng.PinaraAppManager;

public class TcpManagementConsole implements Runnable {

	private int PORT = 0; // 3030;
	private String IPADDRESS = null;
	
	private PinaraAppManager pinaraManager;
	// private ServerSocketChannel serverChannel; 
	private ServerSocket serverSocket;
	
	boolean loopPermit = true;
	
	public static TcpManagementConsole initComm(PinaraAppManager pinaraManager, int port, String ipAddress) throws SocketException {
		return new TcpManagementConsole(pinaraManager, port, ipAddress);
	}
	
	private TcpManagementConsole(PinaraAppManager pinaraManager, int port, String ipAddress) throws SocketException {
		
		this.PORT = port;
		this.IPADDRESS = ipAddress;
		this.pinaraManager = pinaraManager;

		try {
//			serverChannel = ServerSocketChannel.open();
//			serverChannel.configureBlocking(true);
//			serverChannel.socket().bind(new InetSocketAddress(IPADDRESS, PORT));
			serverSocket = new ServerSocket(PORT, 0, InetAddress.getByName(IPADDRESS));
		} catch (RuntimeException re) {
			re.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}

		CoreFactory.getLogger().debug(Pinara.getMessage("ManagementConsole.0")); //$NON-NLS-1$

	}
	
	public void terminate(boolean forcedTerminate) {
		synchronized (this) {
			this.loopPermit = false;
		}
	}

	public void run() {
		Thread.currentThread().setName("ManagementConsole"); //$NON-NLS-1$
				

//		SocketChannel client = null;
		
		try {

			while (loopPermit) {
				Socket client = serverSocket.accept();
				new Thread(new ServerSocketHandler(client, pinaraManager)).start();
			}

		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
