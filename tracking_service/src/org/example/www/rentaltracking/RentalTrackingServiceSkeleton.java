
/**
 * RentalTrackingServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.rentaltracking;

import java.util.Calendar;

import org.example.www.orchestrator.OrchestratorServiceSkeleton;
import org.example.www.orchestrator.ReturnmentReminder;

/**
 * RentalTrackingServiceSkeleton java skeleton for the axisService
 */
public class RentalTrackingServiceSkeleton implements RentalTrackingServiceSkeletonInterface {
	
	private TrackingModel model;

	public RentalTrackingServiceSkeleton() {
		model = TrackingModel.getInstance();
	}
	
	/**
	 * Auto generated method signature
	 * 
	 * @param rentalTracking0
	 * @return
	 */

	public void rentalTracking(org.example.www.rentaltracking.RentalTracking rentalTracking0) {
		Calendar returndate = model.trackIceskates(rentalTracking0.getIceSkatesID(), rentalTracking0.getCallbackURL());
		
		ReturnmentReminder response = new ReturnmentReminder();
		response.setExpectedReturnDate(returndate);
		response.setIceSkateID(rentalTracking0.getIceSkatesID());
		
		try {
			Thread.sleep(10000);
			//temporary callback
			new OrchestratorServiceSkeleton().returnmentReminder(response);			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
