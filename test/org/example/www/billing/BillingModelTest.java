package org.example.www.billing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BillingModelTest {

	private BillingModel model;
	
	private final String RICH_ACCOUNT = "123456";
	private final String NON_EXISTING_ACCOUNT = "111111";
	
	@Before
	public void setUp() throws Exception {
		model = BillingModel.getInstance();
	}

	@Test
	public void getBalance() {
		double balance = model.getBalance("123123");
		assertEquals(10.00, balance, 0.01);
	}
	
	@Test
	public void getWrongBalance() {
		double balance = model.getBalance(NON_EXISTING_ACCOUNT);
//		Non existing accounts return a negative value
		assertEquals(-1, balance, 0.01);
	}
	
	@Test
	public void getNullBalance() {
		double balance = model.getBalance(null);
//		Non existing accounts return a negative value
		assertEquals(-1, balance, 0.01);
	}
	
	@Test
	public void doPaymentTest() {
		double price = 10.00;
		double balance = model.getBalance(RICH_ACCOUNT);
		boolean success = model.doPayment(RICH_ACCOUNT, price);
		
		assertEquals(true, success);
		assertEquals(balance - price, model.getBalance(RICH_ACCOUNT), 0.01);
	}
	
	@Test
	public void cantPayPayment() {
		double price = 100.00;
		double balance = model.getBalance(RICH_ACCOUNT);
		boolean success = model.doPayment(RICH_ACCOUNT, price);
		
		assertEquals(false, success);
		assertEquals(balance, model.getBalance(RICH_ACCOUNT), 0.01);
	}
	
	@Test
	public void falseBankaccount() {
		double price = 100.00;
		boolean success = model.doPayment(NON_EXISTING_ACCOUNT, price);
		
		assertEquals(false, success);
	}
	
	@Test
	public void nullBankaccount() {
		double price = 100.00;
		boolean success = model.doPayment(null, price);
		
		assertEquals(false, success);
	}
	
	@Test
	public void negativePrice() {
		double price = -10.00;
		double balance = model.getBalance(RICH_ACCOUNT);
		boolean success = model.doPayment(RICH_ACCOUNT, price);
		
		assertEquals(false, success);
		assertEquals(balance, model.getBalance(RICH_ACCOUNT), 0.01);
	}

}
