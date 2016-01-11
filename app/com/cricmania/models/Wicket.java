package com.cricmania.models;

public class Wicket extends Base {
	private String takenBy;
	private String wicketOf;
	private Integer onBall;
	private String stumpedBy;
	private String caughtBy;
	private WicketType wicketType;
	private String runOutBy;
	private String umpire;
	
	public enum WicketType {
		BOWLED,CAUGHT,RUNOUT,TWICE_BALL_HEATING,RETIRED,HIT_WICKET,LBW,STUMPED,TIME_OUT,HANDLED_BALL,OBSTRACTING_FIELD
	}

	public String getTakenBy() {
		return takenBy;
	}

	public void setTakenBy(String takenBy) {
		this.takenBy = takenBy;
	}

	public String getWicketOf() {
		return wicketOf;
	}

	public void setWicketOf(String wicketOf) {
		this.wicketOf = wicketOf;
	}

	public Integer getOnBall() {
		return onBall;
	}

	public void setOnBall(Integer onBall) {
		this.onBall = onBall;
	}

	public String getStumpedBy() {
		return stumpedBy;
	}

	public void setStumpedBy(String stumpedBy) {
		this.stumpedBy = stumpedBy;
	}

	public String getCaughtBy() {
		return caughtBy;
	}

	public void setCaughtBy(String caughtBy) {
		this.caughtBy = caughtBy;
	}

	public WicketType getWicketType() {
		return wicketType;
	}

	public void setWicketType(WicketType wicketType) {
		this.wicketType = wicketType;
	}

	public String getRunOutBy() {
		return runOutBy;
	}

	public void setRunOutBy(String runOutBy) {
		this.runOutBy = runOutBy;
	}

	public String getUmpire() {
		return umpire;
	}

	public void setUmpire(String umpire) {
		this.umpire = umpire;
	}

}
