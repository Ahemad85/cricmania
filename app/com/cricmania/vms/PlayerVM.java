package com.cricmania.vms;

import java.util.Date;
import java.util.List;

import com.cricmania.comman.BowlingType;
import com.cricmania.comman.PlayerType;
import com.cricmania.comman.PlayingStyle;


public class PlayerVM {
	public String id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String dob;
	private String contactNumber;
	private String email;
	private PlayingStyle battingStyle;
	private PlayingStyle bowlingStyle;
	private List<BowlingType> bowlingTypes;
	private Boolean isKeeper;
	private String profilePic;
	private PlayerType playerType;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public PlayingStyle getBattingStyle() {
		return battingStyle;
	}
	public void setBattingStyle(PlayingStyle battingStyle) {
		this.battingStyle = battingStyle;
	}
	public PlayingStyle getBowlingStyle() {
		return bowlingStyle;
	}
	public void setBowlingStyle(PlayingStyle bowlingStyle) {
		this.bowlingStyle = bowlingStyle;
	}
	public List<BowlingType> getBowlingTypes() {
		return bowlingTypes;
	}
	public void setBowlingTypes(List<BowlingType> bowlingTypes) {
		this.bowlingTypes = bowlingTypes;
	}
	public Boolean getIsKeeper() {
		return isKeeper;
	}
	public void setIsKeeper(Boolean isKeeper) {
		this.isKeeper = isKeeper;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public PlayerType getPlayerType() {
		return playerType;
	}
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
