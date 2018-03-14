
/**
 * OrchestratorServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.orchestrator;

import java.util.HashMap;
import java.util.Map;

import org.example.www.billing.BillingServiceSkeleton;
import org.example.www.billing.Payment;
import org.example.www.delivery.Delivery;
import org.example.www.delivery.DeliveryServiceSkeleton;
import org.example.www.inventory.Inventory;
import org.example.www.inventory.InventoryResponse;
import org.example.www.inventory.InventoryServiceSkeleton;
import org.example.www.rentaltracking.RentalTracking;
import org.example.www.rentaltracking.RentalTrackingServiceSkeleton;

/**
 * OrchestratorServiceSkeleton java skeleton for the axisService
 */
public class OrchestratorServiceSkeleton implements OrchestratorServiceSkeletonInterface {
	
	private static int deliveryID = 0;
	
	private static Map<Integer, DeliveryResponse> deliveryResponses;
	
	public OrchestratorServiceSkeleton() {
		deliveryResponses = new HashMap<Integer, DeliveryResponse>();
	}
	
	/**
	 * Auto generated method signature
	 * 
	 * @param deliveryResponse0
	 * @return
	 */
	public void deliveryResponse(org.example.www.orchestrator.DeliveryResponse deliveryResponse0) {
		System.out.println("delivery:" + deliveryResponse0.getDeliverID());
		deliveryResponses.put(deliveryResponse0.getDeliverID(), deliveryResponse0);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param returnmentReminder1
	 * @return
	 */
	public void returnmentReminder(org.example.www.orchestrator.ReturnmentReminder returnmentReminder1) {
		System.out.println("reminder");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param rentIceSkates2
	 * @return rentIceSkatesResponse3
	 */

	public org.example.www.orchestrator.RentIceSkatesResponse rentIceSkates(org.example.www.orchestrator.RentIceSkates rentIceSkates2) {
		RentIceSkatesResponse response = new RentIceSkatesResponse();
		int deliverID = getDeliveryID();
		
		System.out.println("incoming");
		
		Inventory inv = new Inventory();
		inv.setSize(rentIceSkates2.getSize());
		InventoryResponse inventoryResponse = new InventoryServiceSkeleton().inventory(inv);

		Delivery delivery = new Delivery();
		delivery.setIceSkatesID(inventoryResponse.getIceSkatesID());
		delivery.setCustomer(rentIceSkates2.getCustomer());
		delivery.setDeliveryID(deliverID);
		new DeliveryServiceSkeleton().delivery(delivery);

		// Wait for Delivery response
		while (deliveryResponses.get(deliverID) == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		DeliveryResponse deliveryResponse = deliveryResponses.get(deliverID);
		Payment payment = new Payment();
		payment.setTotalprice(inventoryResponse.getPrice() + deliveryResponse.getPrice());
		payment.setCustomer(rentIceSkates2.getCustomer());
		new BillingServiceSkeleton().payment(payment);

		RentalTracking rentalTracking = new RentalTracking();
		rentalTracking.setIceSkatesID(inventoryResponse.getIceSkatesID());
		new RentalTrackingServiceSkeleton().rentalTracking(rentalTracking);
		
		response.setCarrier(deliveryResponse.getCarrier());
		response.setExpectedDeliveryDate(deliveryResponse.getExpectedDeliveryDate());
		response.setIceSkatesID(inventoryResponse.getIceSkatesID());
		response.setSucceeded(true);
		
		System.out.println("send response");
		return response;
	}
	
	private static int getDeliveryID() {
		deliveryID = deliveryID +1;
		return deliveryID;
	}

}
