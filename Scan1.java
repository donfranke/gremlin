/***************************************************************
 * Program:      Gremlin
 * Description:  Basic scanning tool
 * Written by:   Don Franke
 * Comments:     IS6953 Summer 2006, UTSA
 * 
 * Class:        Scan1
 * Comments:     Gets list of active hosts
 ***************************************************************/
package gremlin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Scan1 {
	String aryIP[];
	
	int count;
	
	public Scan1() {
		aryIP = new String[255];
		
		count = 0;
	}
	
	
	public String GetSubnet() {
		String subnet="";
		//String sError = "";
		
		/*try{
			BufferedReader in = new BufferedReader(new FileReader("subnet.txt"));
			String temp = null;
			subnet = "";
			
			while((temp = in.readLine()) != null) {
				subnet = temp;
			}
			in.close();
			return subnet;
			
		} catch (Exception e) {
			sError = "error reading subnet: " + e.toString();
			System.out.println(sError);  // debug
			return sError;
		}*/
		Config c = new Config();
		c.getConfig();
		subnet = c.subnet;
		return subnet;
	}
	
	public String GetHostList() {
		String hostlist = "";

		Config c = new Config();
		c.getConfig();
		hostlist = c.hostlist;
		return hostlist;
	}
	
	public String ScanSubnet(String inSubnet) {
		
		Config c = new Config();
		c.getConfig();
		String[] aryIP = c.aryHost;
		
		
		
		// populate aryIP with list of IP addresses to scan
		//for(int i=0;i<255;i++) {
		//	aryIP[i] = inSubnet + "." + i;
		//}
		//count = 255;

		
		// ============ debug ============
		/*aryIP[0] = inSubnet + ".1";
		aryIP[1] = inSubnet + ".100";
		aryIP[2] = inSubnet + ".101";
		aryIP[3] = inSubnet + ".102";
		aryIP[4] = inSubnet + ".103";
		count = 5;*/
		// ========= end debug ===========
			
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("scan1.txt"));
			String sIP;
			for(int i=0;i<aryIP.length;i++) {
				sIP = inSubnet + "." + aryIP[i];
				Process p = Runtime.getRuntime().exec("ping -n 1 " + sIP);
				int status = p.waitFor();
				//System.out.println(sIP + " is " + (status==0?"alive":"dead")); // debug
				out.write(sIP + " is " + (status==0?"alive":"dead") + System.getProperty("line.separator"));
			}
			out.close();
		}
		catch (Exception ex) {
			System.out.println("error: " + ex.toString());
			return "error";
		}
		
		return "complete";
	}

}


