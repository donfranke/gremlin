/***************************************************************
 * Program:      Gremlin
 * Description:  Basic scanning tool
 * Written by:   Don Franke
 * Comments:     IS6953 Summer 2006, UTSA
 * 
 * Class:        Probe139
 * Comments:     Probes port 139 (NetBIOS)
 ***************************************************************/

package gremlin;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

// execute nc -nvv -o banner.txt 192.168.0.x 80 < nudge.txt

public class Probe139 {
	public Probe139(String inIP)  {
		String sCommand;
		Date d = new Date();
		String sDate;
		
		SimpleDateFormat formatter = new SimpleDateFormat ("ddMMMyyyy_hhmmss");
		Date currentTime_1 = new Date();
		String dateString = formatter.format(currentTime_1);
		sDate = dateString;
		
		sCommand ="nbtstat -A " + inIP + " > " + inIP + "_139_" + sDate + ".txt"; 
		//System.out.println(sCommand);
		try {
			//Process p2 = Runtime.getRuntime().exec(sCommand);
			GoodWindowsExec gwe = new GoodWindowsExec();
			gwe.Execute(sCommand);
			
		} catch (Exception ex) {
			String sError = "error probing port 139 for ip " + inIP + ": " + ex.toString();
			System.out.println(sError);  // debug
			
		}
	}

}
