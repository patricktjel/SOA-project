
/**
 * OrchestratorServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.orchestrator;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.example.www.billing.BillingServiceSkeleton;
import org.example.www.billing.Payment;
import org.example.www.billing.PaymentResponse;
import org.example.www.customer.Customer;
import org.example.www.delivery.Delivery;
import org.example.www.delivery.DeliveryServiceSkeleton;
import org.example.www.inventory.Inventory;
import org.example.www.inventory.InventoryResponse;
import org.example.www.inventory.InventoryServiceSkeleton;
import org.example.www.inventory.ReturnSkates;
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
	
	private static int getDeliveryID() {
		deliveryID = deliveryID +1;
		return deliveryID;
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
		int deliverID = getDeliveryID();
		Customer customer = rentIceSkates2.getCustomer();
		String size = rentIceSkates2.getSize();

		System.out.println("incoming for: " + deliverID);
		
		InventoryResponse inventoryResponse = requestInventoryService(size);
		if (!inventoryResponse.getAvailability()) {
			return createFalseResponse(size, -1);
		}
		int iceSkatesID = inventoryResponse.getIceSkatesID();
		
		requestDeliveryService(iceSkatesID, customer, deliverID);

		// Wait for Delivery response
		while (deliveryResponses.get(deliverID) == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		DeliveryResponse deliveryResponse = deliveryResponses.get(deliverID);
		if(!deliveryResponse.getApproved()) {
			return createFalseResponse(size, iceSkatesID);
		}
		
		PaymentResponse paymentResponse = requestPaymentService(inventoryResponse.getPrice(), deliveryResponse.getPrice(), customer);
		if(!paymentResponse.getSucceeded()) {
			return createFalseResponse(size, iceSkatesID);
		}
		
		requestRentalTrackingService(iceSkatesID);
		
		System.out.println("send response for: " + deliverID);
		return createResponse(true, deliveryResponse.getCarrier(), deliveryResponse.getExpectedDeliveryDate(), iceSkatesID);
	}
	
	/**
	 * Requests the inventory service, to get the right pair of ice skates and to make sure that that pair of ice skates is available
	 * @param size
	 * @return
	 */
	private InventoryResponse requestInventoryService(String size) {
		Inventory inv = new Inventory();
		inv.setSize(size);
		return new InventoryServiceSkeleton().inventory(inv);
	}
	
	/**
	 * Requests the delivery service, to get the carrier of the ice skates and the price of the delivery.
	 * @param iceSkatesID
	 * @param customer
	 * @param deliverID
	 */
	private void requestDeliveryService(int iceSkatesID, Customer customer, int deliverID) {
		Delivery delivery = new Delivery();
		delivery.setIceSkatesID(iceSkatesID);
		delivery.setCustomer(customer);
		delivery.setDeliveryID(deliverID);
		new DeliveryServiceSkeleton().delivery(delivery);
	}
	
	/**
	 * Requests the paymentservice, which makes sure that the customer pays.
	 * @param iceSkatesPrice
	 * @param deliveryprice
	 * @param customer
	 * @return
	 */
	private PaymentResponse requestPaymentService(double iceSkatesPrice, double deliveryprice, Customer customer) {
		Payment payment = new Payment();
		payment.setTotalprice(iceSkatesPrice + deliveryprice);
		payment.setCustomer(customer);
		return new BillingServiceSkeleton().payment(payment);
	}
	
	/**
	 * Requests the rental tracking service, which keeps track of the rental ice skates.
	 * @param iceSkatesID
	 */
	private void requestRentalTrackingService(int iceSkatesID) {
		RentalTracking rentalTracking = new RentalTracking();
		rentalTracking.setIceSkatesID(iceSkatesID);
		new RentalTrackingServiceSkeleton().rentalTracking(rentalTracking);
	}

	/**
	 * Creates a response for the customer
	 * @param carrier
	 * @param deliveryDate
	 * @param iceSkatesID
	 * @return
	 */
	private RentIceSkatesResponse createResponse(boolean succeeded, String carrier, Calendar deliveryDate, int iceSkatesID) {
		RentIceSkatesResponse response = new RentIceSkatesResponse();
		response.setSucceeded(succeeded);
		if (succeeded) {
			response.setCarrier(carrier);
			response.setExpectedDeliveryDate(deliveryDate);
			response.setIceSkatesID(iceSkatesID);
		}
		return response;
	}
	
	private RentIceSkatesResponse createFalseResponse(String size, int id) {
		if (id != -1) {
//			If there is an id the iceskates were already taken from the inventory and they have to be placed back.
			ReturnSkates request = new ReturnSkates();
			request.setSize(size);
			request.setIceSkatesID(id);
			new InventoryServiceSkeleton().returnSkates(request);
		}
		return createResponse(false, null, null, id);
	}

}
