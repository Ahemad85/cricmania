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

}
