package com.cricmania.models;

import java.util.Date;
import java.util.List;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;

public class Umpire extends Modifiable {
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dob;
	private String contactNumber;
	private String email;
	private Integer matchCount;
	private List<String> matches;
	private String profilePic;
	private Address address;
	
	private static JacksonDBCollection<Umpire,String> coll = MongoDB.getCollection(Umpire.class.getSimpleName(), Umpire.class, String.class);
	
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
	public Integer getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(Integer matchCount) {
		this.matchCount = matchCount;
	}
	public List<String> getMatches() {
		return matches;
	}
	public void setMatches(List<String> matches) {
		this.matches = matches;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void save() {
		this.id = "UMP" + MetaDataDocument.getGeneratedId(Umpire.class.getSimpleName());
		coll.save(this);
	}
	public static Umpire getUmpireByEmail(String email) {
		List<Umpire> umpires = coll.find().is("email",email).toArray();
		if(umpires != null && !umpires.isEmpty()) {
			return umpires.get(0);
		}
		return null;
	}
}
