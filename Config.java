/***************************************************************
 * Program:      Gremlin
 * Description:  Basic scanning tool
 * Written by:   Don Franke
 * Comments:     IS6953 Summer 2006, UTSA
 * 
 * Class:        Config
 * Comments:     Loads configuration file for application
 ***************************************************************/

package gremlin;
import java.io.BufferedReader;
import java.io.FileReader;

public class Config {
	
	public String portlist;
	public Integer interval;
	public String subnet;
	public String hostlist;
	public String sHostlist;
	public String[] aryHost;
	
	public String getConfig() {
		String sError;
		try {
			BufferedReader in = new BufferedReader(new FileReader("config.txt"));
		
			String temp = null;
			String sInterval="86400";  // 60 seconds * 60 minutes * 24 hours = 86400
			String sSubnet;
			
			while((temp = in.readLine()) != null) {
				if(temp.indexOf("ports")>-1) {
					portlist = temp;
				}
				
				if(temp.indexOf("hosts")>-1) {
					sHostlist = temp;
					sHostlist = sHostlist.substring(6,sHostlist.length());
					hostlist = sHostlist;
					
					if(sHostlist.indexOf("|")>-1) {
						 aryHost = sHostlist.split("\\|");
					}
				
					
				}
				if(temp.indexOf("subnet")>-1) {
					sSubnet = temp;
					sSubnet = sSubnet.substring(7,sSubnet.length());
					subnet = sSubnet;	
				}
				if(temp.indexOf("interval=")>-1) {
					sInterval = temp;
					sInterval = sInterval.substring(9,sInterval.length());
					interval = Integer.valueOf(sInterval);					
				}
			}
			in.close();
			
			// if hosts is blank, use 1-254
			if(aryHost==null||aryHost.length==0) {
				aryHost = new String[254];
				for(int i=1;i<255;i++) {
					aryHost[i] = String.valueOf(i);
				}
				
			}
			
			return "success";
			
		} catch (Exception e) {
			sError = "error reading config file: " + e.toString();
			System.out.println(sError);  // debug
			return sError;
		}
		
		
		
	}

}
