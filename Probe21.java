/***************************************************************
 * Program:      Gremlin
 * Description:  Basic scanning tool
 * Written by:   Don Franke
 * Comments:     IS6953 Summer 2006, UTSA
 * 
 * Class:        Probe21
 * Comments:     Probes port 21 (FTP)
 ***************************************************************/
package gremlin;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Probe21 {
	public Probe21(String inIP) {
		String sCommand;
		Date d = new Date();
		String sDate;
		
		SimpleDateFormat formatter = new SimpleDateFormat ("ddMMMyyyy_hhmmss");
		Date currentTime_1 = new Date();
		String dateString = formatter.format(currentTime_1);
		sDate = dateString;
		
		try {
			GoodWindowsExec gwe;
			// open connection
			//sCommand ="ftp -A " + inIP + " > ftp_" + inIP + "_banner6.txt"; 
			sCommand ="ftp -s:ftp.txt " + inIP + " > " + inIP + "_21_" + sDate + ".txt"; 
			//System.out.println(sCommand);
			gwe = new GoodWindowsExec();
			gwe.Execute(sCommand);
					
			// quit
			sCommand ="quit"; 
			//System.out.println(sCommand);
			gwe = new GoodWindowsExec();
			gwe.Execute(sCommand);						
			
		} catch (Exception ex) {
			String sError = "error probing port 21 for ip " + inIP + ": " + ex.toString();
			System.out.println(sError);  // debug
			
		}
	}

}
