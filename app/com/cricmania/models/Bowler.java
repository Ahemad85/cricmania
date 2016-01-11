package com.cricmania.models;

import java.util.List;

public class Bowler extends Base {
	public String inning;
	public String bowledBy;
	public List<String> overs;
	public List<String> wickets;
	public String getInning() {
		return inning;
	}
	public void setInning(String inning) {
		this.inning = inning;
	}
	public String getBowledBy() {
		return bowledBy;
	}
	public void setBowledBy(String bowledBy) {
		this.bowledBy = bowledBy;
	}
	public List<String> getOvers() {
		return overs;
	}
	public void setOvers(List<String> overs) {
		this.overs = overs;
	}
	public List<String> getWickets() {
		return wickets;
	}
	public void setWickets(List<String> wickets) {
		this.wickets = wickets;
	}
	
}
