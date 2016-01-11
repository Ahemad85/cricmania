package com.cricmania.vms;

import java.util.List;

public class TournamentVM {

	private String id;
	private String name;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String startDate;
	private String organizedBy;
	private List<TeamVM> teamVMs;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getOrganizedBy() {
		return organizedBy;
	}
	public void setOrganizedBy(String organizedBy) {
		this.organizedBy = organizedBy;
	}
	public List<TeamVM> getTeamVMs() {
		return teamVMs;
	}
	public void setTeamVMs(List<TeamVM> teamVMs) {
		this.teamVMs = teamVMs;
	}
	
}
