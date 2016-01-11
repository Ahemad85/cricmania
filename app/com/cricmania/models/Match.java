package com.cricmania.models;

import java.util.Date;
import java.util.List;

public class Match extends Base {
	private String title;
	private Integer number;
	private String tournament;
	private String firstTeam;
	private String secondTeam;
	private MatchResult matchResult;
	private String wonBy;
	private String lostBy;
	private Integer totalOver;
	//ground id
	private String venue;
	private String firstInning;
	private String secondInning;
	private String tossWonBy;
	private TossDecision tossDecision;
	private String matchResultStr;
	private List<String> umpires;
	private MatchStatus status;
	private Date dateTime;
	
	public enum MatchStatus {
		FORTH_COMING,ON_GOING,COMPLETED
	}
	public enum MatchResult {
		NORMAL,DRAW,OFF
	}
	public enum TossDecision {
		BATTING,BOWLING
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getTournament() {
		return tournament;
	}
	public void setTournament(String tournament) {
		this.tournament = tournament;
	}
	public String getFirstTeam() {
		return firstTeam;
	}
	public void setFirstTeam(String firstTeam) {
		this.firstTeam = firstTeam;
	}
	public String getSecondTeam() {
		return secondTeam;
	}
	public void setSecondTeam(String secondTeam) {
		this.secondTeam = secondTeam;
	}
	public MatchResult getMatchResult() {
		return matchResult;
	}
	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}
	public String getWonBy() {
		return wonBy;
	}
	public void setWonBy(String wonBy) {
		this.wonBy = wonBy;
	}
	public String getLostBy() {
		return lostBy;
	}
	public void setLostBy(String lostBy) {
		this.lostBy = lostBy;
	}
	public Integer getTotalOver() {
		return totalOver;
	}
	public void setTotalOver(Integer totalOver) {
		this.totalOver = totalOver;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getFirstInning() {
		return firstInning;
	}
	public void setFirstInning(String firstInning) {
		this.firstInning = firstInning;
	}
	public String getSecondInning() {
		return secondInning;
	}
	public void setSecondInning(String secondInning) {
		this.secondInning = secondInning;
	}
	public String getTossWonBy() {
		return tossWonBy;
	}
	public void setTossWonBy(String tossWonBy) {
		this.tossWonBy = tossWonBy;
	}
	public TossDecision getTossDecision() {
		return tossDecision;
	}
	public void setTossDecision(TossDecision tossDecision) {
		this.tossDecision = tossDecision;
	}
	public String getMatchResultStr() {
		return matchResultStr;
	}
	public void setMatchResultStr(String matchResultStr) {
		this.matchResultStr = matchResultStr;
	}
	public List<String> getUmpires() {
		return umpires;
	}
	public void setUmpires(List<String> umpires) {
		this.umpires = umpires;
	}
	public MatchStatus getStatus() {
		return status;
	}
	public void setStatus(MatchStatus status) {
		this.status = status;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
