package com.cricmania.models;

import java.util.List;
import java.util.regex.Pattern;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class Team extends Modifiable {
	private String name;
	private List<PlayerMetaData> players;
	private String sponsoredBy;
	private String contactNumber;
	private String contactPerson;
	private String email;
	private String logoName;
	private Address address;
	private static JacksonDBCollection<Team, String> coll = MongoDB.getCollection(Team.class.getSimpleName(), Team.class, String.class);

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PlayerMetaData> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerMetaData> players) {
		this.players = players;
	}
	public String getSponsoredBy() {
		return sponsoredBy;
	}
	public void setSponsoredBy(String sponsoredBy) {
		this.sponsoredBy = sponsoredBy;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	public static JacksonDBCollection<Team, String> getColl() {
		return coll;
	}
	public static void setColl(JacksonDBCollection<Team, String> coll) {
		Team.coll = coll;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void save() {
		this.id = "TEA" + MetaDataDocument.getGeneratedId(Team.class.getSimpleName());
		coll.save(this);
	}
	
	public static Team findById(String id) {
		return coll.findOneById(id);
	}
	
	public void update() {
		coll.updateById(this.id,this);
	}
	
	public static List<Team> getTeamByQuery(String q) {
		return coll.find().regex("name", Pattern.compile("(?i).*"+q+".*")).toArray();
	}
}
