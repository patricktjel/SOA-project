package org.example.www.inventory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InventoryModelTest {

	private InventoryModel model;

	@Before
	public void setUp() throws Exception {
		model = InventoryModel.getInstance();
	}
	
	@Test
	public void getPairofSkates() {
		int id = model.getIceskates("M");
//		you get -1 if there are no iceskates available
		assertNotEquals(-1, id);
	}
	
	@Test
	public void outOfStockTest() {
		model.getIceskates("XL");
		int id = model.getIceskates("XL");
//		There is only 1 pair of "XL" iceskates
		assertEquals(-1, id);
	}
	
	@Test
	public void invalidSize() {
		int id = model.getIceskates("XXL");
//		This size is not available and thus id is-1
		assertEquals(-1, id);
	}
	
	@Test
	public void nullSize() {
		int id = model.getIceskates(null);
//		This size is not available and thus id is-1
		assertEquals(-1, id);
	}

	@Test
	public void returnPairofSkates() {
		int id = 5;
		String size = "M";
		boolean succeeded = model.returnSkates(size, id);
		assertEquals(true, succeeded);
//		We just inserted this one so we should be able to get it back.
		assertEquals(5, model.getIceskates("M"));
	}
	
	@Test
	public void returnWithNotExistingSize() {
		int id = 5;
		String size = "Test";
		boolean succeeded = model.returnSkates(size, id);
//		Shouldn't be possible
		assertEquals(false, succeeded);
	}

	@Test
	public void returnWithNullSize() {
		int id = 5;
		String size = null;
		boolean succeeded = model.returnSkates(size, id);
		assertEquals(false, succeeded);
	}

}
