package com.cricmania.models;

import java.util.List;

public class Batsman extends Base {
	private String player;
	private List<String> runs;
	private Integer battingOrder;
	private String inning;
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public List<String> getRuns() {
		return runs;
	}
	public void setRuns(List<String> runs) {
		this.runs = runs;
	}
	public Integer getBattingOrder() {
		return battingOrder;
	}
	public void setBattingOrder(Integer battingOrder) {
		this.battingOrder = battingOrder;
	}
	public String getInning() {
		return inning;
	}
	public void setInning(String inning) {
		this.inning = inning;
	}
	
}
