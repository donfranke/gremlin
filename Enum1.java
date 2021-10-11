/***************************************************************
 * Program:      Gremlin
 * Description:  Basic scanning tool
 * Written by:   Don Franke
 * Comments:     IS6953 Summer 2006, UTSA
 * 
 * Class:        Enum1
 * Comments:     Takes results of scan2 and starts probing each host 
 *                 and port
 ***************************************************************/
package gremlin;

import java.io.BufferedReader;
import java.io.FileReader;

public class Enum1 {
	
	String[][] aryHost;
	int iHostTotal;
	public String GetList() {
//		 get list of addresses and ports
		// 2-d array?
		// loop thru scan2.txt and extract
		  //  address
		    //  ports
		int i = 0;
				
		aryHost = new String[255][20];
		String sError;		
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("scan2.txt"));
		
			String temp = null;
			String sIP= null;
			String[] aryTemp;
			while((temp = in.readLine()) != null) {
				if(temp.indexOf("Host:")>-1) {
					// grab it, put it into the array
					//sIP = temp.substring(6,temp.indexOf("("));
					aryHost[i++][0] = temp;
				}
			}
			in.close();
			iHostTotal = i;
			
			// now that host lines are in array, let's parse the ports for each host
			int j = 0;
			for(i=0;i<iHostTotal;i++ ) {
				sIP = aryHost[i][0];
				aryTemp = sIP.split(" ");
				j = 0;
				for (int x=3; x<aryTemp.length; x++) {
			      
			      aryHost[i][0] = sIP.substring(6,sIP.indexOf(" ("));
			      
			      if(aryTemp[x].indexOf("/open/")>-1) {
			      	aryHost[i][j+1] = aryTemp[x].replace(",","");
			      	//aryHost[i][19] = String.valueOf(aryTemp.length);
			      	//System.out.println(i + " " + aryHost[i][0] + " " + j + " " + aryTemp[x].replace(",","") + " length: " + aryHost[i][19]);
			      	j++;
			      }	
			      aryHost[i][19] = String.valueOf(j);
				}
			}			
			
			return "complete";
		} catch (Exception e) {
			sError = "error reading scan2.txt: " + e.toString();
			System.out.println(sError);  // debug
			return sError;
		}	
	}
	
	public String go() {
		// iterate thru target list (aryHost)
		// see what you can find
		Integer iPortTotal;
		String sPort;
		for(int i=0;i<iHostTotal;i++) {
			iPortTotal = Integer.valueOf(aryHost[i][19]);
			System.out.println(i + "|" + aryHost[i][0] + "|" + aryHost[i][1] + "|" + aryHost[i][2] + "|" + aryHost[i][19]);
			if(iPortTotal.intValue()>0) {
				//	iterate thru ports
				for(int j=1;j<iPortTotal.intValue()+1;j++) {
					// grab port
					sPort = aryHost[i][j].substring(0,aryHost[i][j].indexOf("/"));
					//System.out.println(j + ">" + sPort);
					switch(Integer.valueOf(sPort).intValue()) {
					case 80:
						new Probe80(aryHost[i][0]);  // anon obj	
						break;
					
					case 139:
						new Probe139(aryHost[i][0]);  // non obj
						break;
					
					case 21:
						new Probe21(aryHost[i][0]);  // non obj
						break;
						
					}
				}
			}

		}
		
		return "success";
	}
}
