package com.cricmania.models;

import java.util.List;

public class Match extends Base {
	private String name;
	private Integer number;
	private String tournament;
	private String firstTeam;
	private String secondTeam;
	private MatchResult matchResult;
	private String wonBy;
	private String lostBy;
	private Integer totalOver;
	private String venue;
	private String firstInning;
	private String secondInning;
	private String tossWonBy;
	private PlayType tossDecision;
	private String matchResultStr;
	private List<String> umpires;
	
	public enum MatchResult {
		NORMAL,DRAW,OFF
	}
	public enum PlayType {
		BATTING,BOWLING
	}
}
