package com.cricmania.models;

public class Address extends Modifiable {
	private String lane;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private AddressType addressType;
	
	public enum AddressType {
		PLAYER,TEAM,TOURNAMENT,MATCH
	}
}
