package com.cricmania.models;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import net.vz.mongodb.jackson.DBQuery;
import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;

import com.cricmania.comman.BowlingType;
import com.cricmania.comman.PlayerType;
import com.cricmania.comman.PlayingStyle;

public class Player extends Modifiable {
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dob;
	private String contactNumber;
	private String email;
	private PlayingStyle battingStyle;
	private PlayingStyle bowlingStyle;
	private List<BowlingType> bowlingTypes;
	private Boolean isKeeper;
	private Address address;
	private String profilePic;
	private PlayerType playerType;

	private static JacksonDBCollection<Player,String> coll = MongoDB.getCollection(Player.class.getSimpleName(), Player.class, String.class);
	
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	public Boolean getIsKeeper() {
		return isKeeper;
	}

	public void setIsKeeper(Boolean isKeeper) {
		this.isKeeper = isKeeper;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
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

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}
	
	public void save() {
		String type = null;
		if(this.playerType == null) {
			type = "GEN";
		} else {
			type = this.playerType.name().substring(0,3);
		}
		this.id = type + MetaDataDocument.getGeneratedId(type);
		coll.save(this);
	}
	
	public static Player getPlayerByEmail(String email) {
		List<Player> players = coll.find().is("email",email).toArray();
		if(players != null && !players.isEmpty()) {
			return players.get(0);
		}
		return null;
	}
	
	public static Player getPlayerById(String id) {
		return coll.findOneById(id);
	}
	
	public static List<Player> getPlayerByQuery(String q) {
		Pattern pattern = Pattern.compile("(?i).*"+q+".*");
		return coll.find().or(DBQuery.regex("firstName", pattern),DBQuery.regex("lastName", pattern)).toArray();
	}
}
