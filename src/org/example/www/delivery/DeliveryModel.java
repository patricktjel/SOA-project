package org.example.www.delivery;

import java.util.ArrayList;
import java.util.Random;

public class DeliveryModel {
	private static DeliveryModel instance = null;
	
	private static int orderID = 0;
	
	private ArrayList<Carrier> shippingCompanies;

	private DeliveryModel() {
		initDeliveryCompanies();
	}

	private void initDeliveryCompanies() {
		shippingCompanies = new ArrayList<>();

		shippingCompanies.add(new Carrier("UPS", 10.00));
		shippingCompanies.add(new Carrier("TNT", 8.00));
		shippingCompanies.add(new Carrier("DHL", 11.00));
		shippingCompanies.add(new Carrier("FedEx", 8.50));
		shippingCompanies.add(new Carrier("Royal Mail", 12.50));
	}

	public static DeliveryModel getInstance() {
		if (instance == null) {
			instance = new DeliveryModel();
		}
		return instance;
	}
	
	public Carrier getCarrier() {
		return shippingCompanies.get(new Random().nextInt(shippingCompanies.size()));
	}
	
	public int getOrderID() {
		orderID = orderID++;
		return orderID;
	}
}
