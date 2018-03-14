package org.example.www.billing;

import java.util.HashMap;
import java.util.Map;

public class BillingModel {
	private static BillingModel instance = null;

//	A map with the bankaccount and its balance
	private Map<String, Double> balances;

	private BillingModel() {
		balances = new HashMap<String, Double>();

		balances.put("123456", 50.00);
		balances.put("123123", 10.00);
	}

	public static BillingModel getInstance() {
		if (instance == null) {
			instance = new BillingModel();
		}
		return instance;
	}
	
	/**
	 * Checks if the bankaccount exists and if there is enough money available.
	 * 
	 * If there is enough money available, the payment will be succesful.
	 * @param bankaccount
	 * @param price
	 * @return
	 */
	public boolean doPayment(String bankaccount, double price) {
		if(!balances.containsKey(bankaccount)) {
			return false;
		}
		double balance = balances.get(bankaccount);
		
		if (balance < price) {
			return false;
		} else {
			balances.put(bankaccount, balance - price);
			return true;
		}
	}
}
