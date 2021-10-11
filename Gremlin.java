/***************************************************************
 * Program:      Gremlin
 * Description:  Basic scanning tool
 * Written by:   Don Franke
 * Comments:     IS6953 Summer 2006, UTSA
 * 
 * Class:        Gremlin
 * Comments:     Main class
 ***************************************************************/
package gremlin;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Gremlin {
	public String gsPortList;
	public String gsHostList;
	Timer timer;
	/**
	 * @param args
	 */
	public Gremlin() {
		Config c = new Config();
		c.getConfig();
		
		timer = new Timer();
        timer.schedule(new RemindTask(), 0, c.interval.intValue()*1000);
       
	}
	
	public static void main(String[] args) {
	
		Gremlin g = new Gremlin();
		// get config
		
		Config c = new Config();
		c.getConfig();
		
		// get list of ports to scan for for this session		
		g.gsPortList = c.portlist;
		g.gsHostList = c.hostlist;
	}	
	
	class RemindTask extends TimerTask {
		String result = "";
		public void run() {
        	System.out.println("Gremlin started at " + new Date());
    		System.out.println("-----------------------------------------------");
        	
//        	 scan 1
    		// create new Scan 1 and execute methods
    		Scan1 s = new Scan1();
    		String subnet = s.GetSubnet();
    		if(subnet.toLowerCase().indexOf("error")==-1) {
    			result = s.ScanSubnet(subnet);
    		} else {
    			result = "";
    			System.exit(0);
    		}
    		
    		// scan 2
    		Scan2 s2 = new Scan2() ;
    		result = s2.ScanForPorts();
    		
    		
    		// this goes thru results and puts hosts and ports into array
    		// each element in the array is ip address and port
    		// if host has more than one ip address, there is more than
    		// one mention of ip address in file
    		Enum1 e1 = new Enum1();
    		result = e1.GetList();  // is now in global gsPostList
    		// now the enum object is populated with an array of hosts and ports
    		// pass target array to probe and get to work
    		result = e1.go();
    		//System.out.println("EXITING");
    		System.gc();
    		System.out.println("-------------------------------------------------");
    		System.out.print("Gremlin completed at " + new Date());
    		
    		//System.exit(0);	
        }
    }
	
}
