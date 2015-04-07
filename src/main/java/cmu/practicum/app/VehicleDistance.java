package cmu.practicum.app;

import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.ResponseMode;
import org.jgroups.util.RspList;

import cmu.practicum.CommonAPI;
import cmu.practicum.JgroupsRpc;

/*
 * Sample object which can be submitted into jgroups rpc for processing
*/
public class VehicleDistance extends CommonAPI {
	

	int distance;
		
	 String vehiclename;

	/**
	 * @return distance  distance of the vehicle
	 */
	public int getDistance() {
		return distance;
	}

	
	/**
	 * @return vehiclename  name of the vehicle
	 */
	public String getVehiclename() {
		return vehiclename;
	}
	
	
	/**
	 * Populates the vehiclename and distance in the vehicle where this is executed
	 */
	public void execute() {
		Random rdn = new Random();
		this.distance=rdn.nextInt(6);
		try {
			this.vehiclename=java.net.InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	


}
