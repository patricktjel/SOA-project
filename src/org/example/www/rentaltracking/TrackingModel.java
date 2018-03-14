package org.example.www.rentaltracking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;

public class TrackingModel {
	private static TrackingModel instance = null;

	private class TrackCustomer {
		BigInteger iceskatesID;
		String customerCallback;
		Calendar returnDate;
		
		public TrackCustomer(BigInteger iceskatesID, String customerCallback) {
			this.iceskatesID = iceskatesID;
			this.customerCallback = customerCallback;
			
			Calendar calendar = Calendar.getInstance();
//			return in 1 minute for testing purposes.
			calendar.setTimeInMillis(System.currentTimeMillis() + 60000);
			this.returnDate = calendar;
		}
	}

	private ArrayList<TrackCustomer> trackingSkates;

	private TrackingModel() {
		trackingSkates = new ArrayList<>();
	}

	public static TrackingModel getInstance() {
		if (instance == null) {
			instance = new TrackingModel();
		}
		return instance;
	}

	/**
	 * Given the iceskatesID and the customerCallback, keep track of this pair of iceskates.
	 * @param iceskatesID
	 * @param customerCallback
	 */
	public Calendar trackIceskates(BigInteger iceskatesID, String customerCallback) {
		TrackCustomer tracker = new TrackCustomer(iceskatesID, customerCallback);
		trackingSkates.add(tracker);

		return tracker.returnDate;
	}
}
