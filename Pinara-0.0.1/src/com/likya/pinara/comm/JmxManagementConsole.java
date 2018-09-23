package com.likya.pinara.comm;

//import java.io.IOException;
//
//import javax.management.MBeanServer;
//import javax.management.MBeanServerFactory;
//import javax.management.ObjectName;
//import javax.management.remote.JMXConnectorServer;
//import javax.management.remote.JMXConnectorServerFactory;
//import javax.management.remote.JMXServiceURL;
//
//import com.likya.pinara.Pinara;

public class JmxManagementConsole {

//	private static JMXConnectorServer jmxConnectorServer;
//	private static MBeanServer mBeanServer;
//	
//	private static int PORT = 0; // 3030;
//	private static String IPADDRESS = null;
//	
//	public static void initialize(int port, String ipAddress) {
//		
//		JmxManagementConsole.PORT = port;
//		JmxManagementConsole.IPADDRESS = ipAddress;
//		
//        try {
//            // Instantiate the MBean server
//            //
//        	
////            System.out.println(Tlos.getMessage("JmxManagementConsole.0"));
//            mBeanServer = MBeanServerFactory.createMBeanServer();
//
//            // Create a JMXMP connector server
//            //
//            
//            mBeanServer.createMBean("com.likya.pinara.comm.FlexAdminConsole", new ObjectName("MBeans:type=flex"), null, null);
//			
//            System.out.println(Pinara.getMessage("JmxManagementConsole.3"));
//            JMXServiceURL url = new JMXServiceURL("jmxmp", JmxManagementConsole.IPADDRESS, JmxManagementConsole.PORT);
//            System.out.println(Pinara.getMessage("JmxManagementConsole.5") + url.getHost());// + Tlos.getMessage("JmxManagementConsole.6") + InetAddress.getLocalHost().getHostAddress());
//            jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
//            
//            // Start the JMXMP connector server
//            //
//            
////            System.out.println(Tlos.getMessage("JmxManagementConsole.7")); 
//            jmxConnectorServer.addNotificationListener(new JmxConnectionListener(), null, null);
//            jmxConnectorServer.start();
//            System.out.println(Pinara.getMessage("JmxManagementConsole.8")); 
////            System.out.println(Tlos.getMessage("JmxManagementConsole.9")); 
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//	}
//	
//	public static void disconnect() {
//		try {
//			
//			System.out.println(Pinara.getMessage("JmxManagementConsole.10"));	 
//			
//			String [] connIdList = jmxConnectorServer.getConnectionIds();
//			
//			System.out.println(Pinara.getMessage("JmxManagementConsole.11") + connIdList.length); 
//			System.out.println(Pinara.getMessage("JmxManagementConsole.12")); 
//			
//			int counter = 0;
//			
//			while(true) {
//				if(jmxConnectorServer.getConnectionIds().length == 0 || counter++ == 20) {
//					break;
//				}
//				try {
//					Thread.sleep(1000);
//					System.out.print("."); 
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			if(counter == 21) {
//				System.out.println(Pinara.getMessage("JmxManagementConsole.14")); 
//			} else {
//				System.out.println(Pinara.getMessage("JmxManagementConsole.15")); 
//			}
//			
//			jmxConnectorServer.stop();
//			
//			System.out.println(Pinara.getMessage("JmxManagementConsole.16")); 
//			System.out.println(Pinara.getMessage("JmxManagementConsole.17"));	 
//			
//			MBeanServerFactory.releaseMBeanServer(mBeanServer);
//			
//			System.out.println(Pinara.getMessage("JmxManagementConsole.18")); 
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
