package com.cricmania.comman;

import java.util.ArrayList;
import java.util.List;

import com.cricmania.vms.SelectItem;

public enum PlayingStyle {
	LEFT_HANDED("Left Handed"),RIGHT_HANDED("Right Handed");
	private String label;
	private PlayingStyle(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public static List<SelectItem> getAllItems() {
		List<SelectItem> labels = new ArrayList<SelectItem>(PlayingStyle.values().length);
		for(PlayingStyle style : PlayingStyle.values()) {
			SelectItem item = new SelectItem();
			item.setName(style.getLabel());
			item.setValue(style.name());
			labels.add(item);
		}
		return labels;
	}
}
