/***************************************************************
 * Program:      Gremlin
 * Description:  Basic scanning tool
 * Written by:   Don Franke
 * Comments:     IS6953 Summer 2006, UTSA
 * 
 * Class:        Scan2
 * Comments:     Gets list of ports based on list of active hosts
 ***************************************************************/
package gremlin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Timer;
import java.util.TimerTask;

public class Scan2 {

	String aryIP[];
	
	public String ScanForPorts() {
		String sError = "";
		aryIP = new String[255];
		String temp = null;
		int i = 0;
		int count = 0;
		Timer timer;
			
		try {
			BufferedReader in = new BufferedReader(new FileReader("scan1.txt"));
			i = 0;
			while((temp = in.readLine()) != null) {
				if(temp.indexOf("alive")>-1) {  // skip localhost, takes forever
					aryIP[i] = temp.substring(0,temp.indexOf(" is alive"));
					i++;
				}
			}
			in.close();
			count = i;
			
			// build file for input to nmap scan
			BufferedWriter out = new BufferedWriter(new FileWriter("scan1b.txt"));
			for(i = 0;i<count;i++) {
				out.write(aryIP[i] + System.getProperty("line.separator"));				
			}
			out.close();
			
			// loop thru IP addresses nmap 'em
			//Process p = Runtime.getRuntime().exec("nmap -P0 -p 1-1023 -iL scan1b.txt -oG scan2.txt");
			//System.out.println(p.toString());
			String sCommand;
			sCommand = "nmap -P0 -p 1-1023 -iL scan1b.txt -oG scan2.txt";
			GoodWindowsExec gwe = new GoodWindowsExec();
			gwe.Execute(sCommand);
			//System.out.print("Running nmap...");			

			//while(true) {
			i = 0;
			
			//System.out.println(p.exitValue());
			//int status = p.waitFor();
			//System.out.println("nmap scan complete"); // debug
						
			return "complete";
			
		}
		catch (Exception ex) {
			System.out.println("error: " + ex.toString());
			return "error";
		}
		
		
		
	}
	

	

}
