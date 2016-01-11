package com.cricmania.models;

import java.util.List;

public class Inning extends Base {
	private String battingTeam;
	private String bowlingTeam;
	private Integer wickets = 0;
	private Integer totalScore = 0;
	private List<String> batsmans;
	private List<String> overs;
	private String match;
	public String getBattingTeam() {
		return battingTeam;
	}
	public void setBattingTeam(String battingTeam) {
		this.battingTeam = battingTeam;
	}
	public String getBowlingTeam() {
		return bowlingTeam;
	}
	public void setBowlingTeam(String bowlingTeam) {
		this.bowlingTeam = bowlingTeam;
	}
	public Integer getWickets() {
		return wickets;
	}
	public void setWickets(Integer wickets) {
		this.wickets = wickets;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public List<String> getBatsmans() {
		return batsmans;
	}
	public void setBatsmans(List<String> batsmans) {
		this.batsmans = batsmans;
	}
	public List<String> getOvers() {
		return overs;
	}
	public void setOvers(List<String> overs) {
		this.overs = overs;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	
}
