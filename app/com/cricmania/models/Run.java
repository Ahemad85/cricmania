package com.cricmania.models;

public class Run extends Base {
	private String scoredBy;
	private String bowledBy;
	private Integer value = 0;
	private RunType runType;
	private Integer onBall;
	
	public enum RunType {
		NORMAL,BYES,LEG_BYES,WIDE,NO
	}

	public String getScoredBy() {
		return scoredBy;
	}

	public void setScoredBy(String scoredBy) {
		this.scoredBy = scoredBy;
	}

	public String getBowledBy() {
		return bowledBy;
	}

	public void setBowledBy(String bowledBy) {
		this.bowledBy = bowledBy;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public RunType getRunType() {
		return runType;
	}

	public void setRunType(RunType runType) {
		this.runType = runType;
	}

	public Integer getOnBall() {
		return onBall;
	}

	public void setOnBall(Integer onBall) {
		this.onBall = onBall;
	}
	
}
