
/**
 * BillingServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.billing;

/**
 * BillingServiceSkeleton java skeleton for the axisService
 */
public class BillingServiceSkeleton implements BillingServiceSkeletonInterface {
	
	private BillingModel model;

	public BillingServiceSkeleton() {
		model = BillingModel.getInstance();
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param payment0
	 * @return paymentResponse1
	 */

	public org.example.www.billing.PaymentResponse payment(org.example.www.billing.Payment payment0) {
			PaymentResponse response = new PaymentResponse();
			
			boolean success = model.doPayment(payment0.getCustomer().getBankaccount(), payment0.getTotalprice());
			response.setSucceeded(success);
			System.out.println("payment was succesfull? " + success);
			return response;
	}

}
