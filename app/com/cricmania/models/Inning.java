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
}
