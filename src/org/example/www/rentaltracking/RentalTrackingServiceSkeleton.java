
/**
 * RentalTrackingServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.rentaltracking;

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
		model.trackIceskates(rentalTracking0.getIceSkatesID(), rentalTracking0.getCallbackURL());
		
		try {
			Thread.sleep(30000);
			System.out.println("yh");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
