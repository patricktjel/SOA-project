
/**
 * DeliveryServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.delivery;

import java.rmi.RemoteException;
import java.util.Calendar;

import org.apache.axis2.AxisFault;
import org.example.www.delivery.client.DeliveryServiceStub;

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
		Carrier carrier = model.getCarrier();
		System.out.println(carrier);

		try {
			DeliveryServiceStub stub = new DeliveryServiceStub();
			DeliveryServiceStub.DeliveryCallBack response = new DeliveryServiceStub.DeliveryCallBack();
			response.setCarrier(carrier.getCarrierName());
			response.setApproved(true);
			response.setDeliveryID(delivery0.getDeliveryID());
			response.setExpectedDeliveryDate(Calendar.getInstance());
			response.setOrderID(delivery0.getDeliveryID());
			response.setPrice(carrier.getPrice());
			
			stub.deliveryCallBack(response);
			System.out.println("sended");
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
