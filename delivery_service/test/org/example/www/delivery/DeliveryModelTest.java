package org.example.www.delivery;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeliveryModelTest {

	private DeliveryModel model;
	
	@Before
	public void setUp() throws Exception {
		model = DeliveryModel.getInstance();
	}

	@Test
	public void getCarrier() {
		Carrier carrier = model.getCarrier();
		
//		Make sure none of the variables are null.
		assertNotEquals(null, carrier);
		assertNotEquals(null, carrier.getCarrierName());
		assertNotEquals(null, carrier.getPrice());
	}
	
	@Test
	public void getOrderID() {
		int id = model.getOrderID();
		assertEquals(id+1, model.getOrderID());
	}

}
