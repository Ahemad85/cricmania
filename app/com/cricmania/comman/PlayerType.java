package com.cricmania.comman;

import java.util.ArrayList;
import java.util.List;

import com.cricmania.vms.SelectItem;

public enum PlayerType {
	ALL_ROUNDER("All-Rounder"),BATSMAN("Batsman"),BOWLER("Bowler");
	private String label;
	private PlayerType(String value) {
		this.label = value;
	}	
	public String getLabel() {
		return label;
	}

	public static List<SelectItem> getAllItems() {
		List<SelectItem> labels = new ArrayList<SelectItem>(PlayingStyle.values().length);
		for(PlayerType style : PlayerType.values()) {
			SelectItem item = new SelectItem();
			item.setName(style.getLabel());
			item.setValue(style.name());
			labels.add(item);
		}
		return labels;
	}
}
