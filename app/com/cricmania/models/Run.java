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
}
