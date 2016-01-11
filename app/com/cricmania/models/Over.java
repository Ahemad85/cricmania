package com.cricmania.models;

import java.util.List;

public class Over extends Base {
	private String bowledBy;
	private Integer firstBowlerBowledBall;
	private Boolean isMaiden = true;
	private Integer totalRuns = 0;
	private List<String> _1Ball;
	private List<String> _2Ball;
	private List<String> _3Ball;
	private List<String> _4Ball;
	private List<String> _5Ball;
	private List<String> _6Ball;
	private OverType overType;

	public enum OverType {
		NORMAL_OVER,PARTIAL_OVER
	}

	public String getBowledBy() {
		return bowledBy;
	}

	public void setBowledBy(String bowledBy) {
		this.bowledBy = bowledBy;
	}

	public Integer getFirstBowlerBowledBall() {
		return firstBowlerBowledBall;
	}

	public void setFirstBowlerBowledBall(Integer firstBowlerBowledBall) {
		this.firstBowlerBowledBall = firstBowlerBowledBall;
	}

	public Boolean getIsMaiden() {
		return isMaiden;
	}

	public void setIsMaiden(Boolean isMaiden) {
		this.isMaiden = isMaiden;
	}

	public Integer getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(Integer totalRuns) {
		this.totalRuns = totalRuns;
	}

	public List<String> get_1Ball() {
		return _1Ball;
	}

	public void set_1Ball(List<String> _1Ball) {
		this._1Ball = _1Ball;
	}

	public List<String> get_2Ball() {
		return _2Ball;
	}

	public void set_2Ball(List<String> _2Ball) {
		this._2Ball = _2Ball;
	}

	public List<String> get_3Ball() {
		return _3Ball;
	}

	public void set_3Ball(List<String> _3Ball) {
		this._3Ball = _3Ball;
	}

	public List<String> get_4Ball() {
		return _4Ball;
	}

	public void set_4Ball(List<String> _4Ball) {
		this._4Ball = _4Ball;
	}

	public List<String> get_5Ball() {
		return _5Ball;
	}

	public void set_5Ball(List<String> _5Ball) {
		this._5Ball = _5Ball;
	}

	public List<String> get_6Ball() {
		return _6Ball;
	}

	public void set_6Ball(List<String> _6Ball) {
		this._6Ball = _6Ball;
	}

	public OverType getOverType() {
		return overType;
	}

	public void setOverType(OverType overType) {
		this.overType = overType;
	}
	
}
