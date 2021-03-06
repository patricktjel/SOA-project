package org.example.www.inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InventoryModel {

	private static InventoryModel instance = null;

//	A map with the size and the amount of ice skates
	private Map<String, Stack<Integer>> iceSkates;

	private InventoryModel() {
		initIceSkates();
	}
	
	private void initIceSkates() {
		iceSkates = new HashMap<>();
		
		Stack<Integer> stackS = new Stack<>();
		stackS.addAll(Arrays.asList(0,1,2,3,4));
		iceSkates.put("S", stackS);
		
		Stack<Integer> stackM = new Stack<>();
		stackM.addAll(Arrays.asList(10, 11, 12, 13, 14, 15, 16, 17, 18));
		iceSkates.put("M", stackM);
		
		Stack<Integer> stackL = new Stack<>();
		stackL.addAll(Arrays.asList(20, 21, 22, 23, 24, 25, 26));
		iceSkates.put("L", stackL);
		
		Stack<Integer> stackXL = new Stack<>();
		stackXL.addAll(Arrays.asList(30));
		iceSkates.put("XL", stackXL);
	}

	public static InventoryModel getInstance() {
		if (instance == null) {
			instance = new InventoryModel();
		}
		return instance;
	}
	
	public int getIceskates(String size) {		
		Stack<Integer> skates = iceSkates.get(size);
		int iceSkatesID = -1;
		if (skates != null && !skates.isEmpty()) {
			iceSkatesID = iceSkates.get(size).pop();
		}
		System.out.println("Available stock after this purchase: " + iceSkates);
		return iceSkatesID;
	}

	public boolean returnSkates(String size, int id) {
		Stack<Integer> list = iceSkates.get(size);
		if (null == list) {
			return false;
		} else {
			return list.add(id);	
		}
	}
}