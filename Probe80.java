/***************************************************************
 * Program:      Gremlin
 * Description:  Basic scanning tool
 * Written by:   Don Franke
 * Comments:     IS6953 Summer 2006, UTSA
 * 
 * Class:        Probe80
 * Comments:     Probes port 80 (HTTP)
 ***************************************************************/
package gremlin;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

// execute nc -nvv -o banner.txt 192.168.0.x 80 < nudge.txt

public class Probe80 {
	public Probe80(String inIP)  {
		String sCommand;
		String sDate="";
		
		//SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss a zzz");
		SimpleDateFormat formatter = new SimpleDateFormat ("ddMMMyyyy_hhmmss");
		Date currentTime_1 = new Date();
		String dateString = formatter.format(currentTime_1);
		sDate = dateString;
						
		sCommand ="nc -nvv -o " + inIP + "_80_" + sDate + ".txt " + inIP + " 80 < nudge.txt"; 
		//System.out.println(sCommand);
		try {
			//Process p2 = Runtime.getRuntime().exec(sCommand);
			GoodWindowsExec gwe = new GoodWindowsExec();
			gwe.Execute(sCommand);
			
		} catch (Exception ex) {
			String sError = "error probing port 80 for ip " + inIP + ": " + ex.toString();
			System.out.println(sError);  // debug
			
		}
	}

}
