package com.cricmania.models;

import java.util.List;

public class Over extends Base {
	private String bowledBy;
	private Integer firstBowlerBowledBall;
	private Boolean isMaiden = true;
	private Integer totalRuns = 0;
	private List<String> _1;
	private List<String> _2;
	private List<String> _3;
	private List<String> _4;
	private List<String> _5;
	private List<String> _6;
	private OverType overType;

	public enum OverType {
		NORMAL_OVER,PARTIAL_OVER
	}
}
