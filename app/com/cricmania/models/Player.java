package com.cricmania.models;

import java.util.Date;
import java.util.List;

public class Player extends Modifiable {
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dob;
	private String contactNumber;
	private String email;
	private BattingStyle battingStyle;
	private List<BowlingStyle> bowlingStyles;
	private Boolean isKeeper;
	private String address;
	
	public enum PlayerType {
		ALL_ROUNDER,BATSMAN,BOWLER
	}
	

}
