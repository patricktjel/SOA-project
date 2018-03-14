
/**
 * InventoryServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.inventory;

import java.math.BigInteger;
import java.util.Stack;

/**
 * InventoryServiceSkeleton java skeleton for the axisService
 */
public class InventoryServiceSkeleton implements InventoryServiceSkeletonInterface {

	private InventoryModel model;

	public InventoryServiceSkeleton() {
		model = InventoryModel.getInstance();
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param inventory0
	 * @return inventoryResponse1
	 */
	public org.example.www.inventory.InventoryResponse inventory(org.example.www.inventory.Inventory inventory0) {
		InventoryResponse response = new InventoryResponse();

		System.out.println("Available stock: " + model.iceSkates);
		
		Stack<Integer> skates = model.iceSkates.get(inventory0.getSize());
		if (skates.isEmpty()) {
			response.setAvailability(false);
		} else {
			int id = model.iceSkates.get(inventory0.getSize()).pop();
			response.setIceSkatesID(BigInteger.valueOf(id));
			response.setPrice(5.00);
			response.setAvailability(true);
		}

		return response;
	}

}
