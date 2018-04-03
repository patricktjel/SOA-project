package org.example.www.delivery;

public class Carrier {
	private String carrierName;
	private double price;
	
	Carrier(String carrierName, double price){
		this.carrierName = carrierName;
		this.price = price;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}