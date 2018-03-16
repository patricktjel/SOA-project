
/**
 * DeliveryServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.delivery;

import java.math.BigInteger;
import java.util.Calendar;

import org.example.www.orchestrator.DeliveryResponse;
import org.example.www.orchestrator.OrchestratorServiceSkeleton;

/**
 * DeliveryServiceSkeleton java skeleton for the axisService
 */
public class DeliveryServiceSkeleton implements DeliveryServiceSkeletonInterface {

	private DeliveryModel model;
	
	public DeliveryServiceSkeleton() {
		model = DeliveryModel.getInstance();
	}
	
	/**
	 * Auto generated method signature
	 * 
	 * @param delivery0
	 * @return
	 */

	public void delivery(org.example.www.delivery.Delivery delivery0) {
		DeliveryResponse response = new DeliveryResponse();
		
		Carrier carrier = model.getCarrier();
		
		response.setApproved(true);
		response.setCarrier(carrier.getCarrierName());
		response.setPrice(carrier.getPrice());
		response.setOrderID(model.getOrderID());
		response.setExpectedDeliveryDate(Calendar.getInstance());
		response.setDeliverID(delivery0.getDeliveryID());
		
//		temporal callback
		new OrchestratorServiceSkeleton().deliveryResponse(response);
	}

}
