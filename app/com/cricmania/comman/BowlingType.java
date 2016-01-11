package com.cricmania.comman;

import java.util.ArrayList;
import java.util.List;

import com.cricmania.vms.SelectItem;

public enum BowlingType {
	NONE("None"),FAST_SEAM("Fast Seam"),FAST_SWING("Fast Swing"),MEDIUM_PACER("Medium Pacer"),SPIN_OFF_BREAK("Spin Off Break"),SPIN_LEG_BREAK("Spin Leg Break"),SPIN_LEFT_ARM_ORTHODOX("Spin Left Arm Orthodox"),SPIN_LEFT_ARM_UNORTHODOX("Spin Left Arm Unorthodox");
	
	private String label;
	private BowlingType(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public static List<SelectItem> getAllItems() {
		List<SelectItem> labels = new ArrayList<SelectItem>(BowlingType.values().length);
		for(BowlingType style : BowlingType.values()) {
			SelectItem item = new SelectItem();
			item.setName(style.getLabel());
			item.setValue(style.name());
			labels.add(item);
		}
		return labels;
	}
}
