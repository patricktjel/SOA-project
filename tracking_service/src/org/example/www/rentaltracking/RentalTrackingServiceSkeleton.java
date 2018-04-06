
/**
 * RentalTrackingServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.rentaltracking;

import java.rmi.RemoteException;
import java.util.Calendar;

import org.example.www.rentaltracking.client.RentalTrackingCallBackServiceStub;

/**
 * RentalTrackingServiceSkeleton java skeleton for the axisService
 */
public class RentalTrackingServiceSkeleton implements RentalTrackingServiceSkeletonInterface {

	/**
	 * Auto generated method signature
	 * 
	 * @param rentalTracking0
	 * @return
	 */

	public void rentalTracking(org.example.www.rentaltracking.RentalTracking rentalTracking0) {
		
		Calendar calendar = Calendar.getInstance();
//		return in 1 minute for testing purposes.
		calendar.setTimeInMillis(System.currentTimeMillis() + 1000);
		
		try {
			RentalTrackingCallBackServiceStub stub = new RentalTrackingCallBackServiceStub();
			RentalTrackingCallBackServiceStub.ReturnmentReminder response = new RentalTrackingCallBackServiceStub.ReturnmentReminder();
			
			
			response.setIceSkateID(rentalTracking0.getIceSkatesID());
			response.setDeliveryID(rentalTracking0.getDeliveryID());
			response.setExpectedReturnDate(calendar);
			
			stub.returnmentReminder(response);
			System.out.println("sended");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
