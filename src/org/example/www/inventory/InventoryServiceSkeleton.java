
/**
 * InventoryServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.inventory;

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

		int id = model.getIceskates(inventory0.getSize());
		
		if (-1 == id) {
			response.setAvailability(false);
		} else {
			response.setIceSkatesID(id);
			response.setPrice(5.00);
			response.setAvailability(true);
		}

		return response;
	}
	
	public org.example.www.inventory.ReturnSkatesResponse returnSkates(org.example.www.inventory.ReturnSkates returnSkates0) {
		int id = returnSkates0.getIceSkatesID();
		String size = returnSkates0.getSize();
		
		ReturnSkatesResponse response = new ReturnSkatesResponse();
		response.setSucceeded(model.returnSkates(size, id));
		return response;
	}

}
