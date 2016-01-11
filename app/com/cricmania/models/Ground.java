package com.cricmania.models;

import java.util.List;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class Ground extends Base {
	private String name;
	private Address address;
	private String addedBy;
	
	private static JacksonDBCollection<Ground, String> coll = MongoDB.getCollection(Ground.class.getSimpleName(), Ground.class, String.class);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	
	public static List<Ground> getGroundsByCity(String city) {
		return coll.find().is("address.city", city).toArray();
	}
	
	public void save() {
		this.id = MetaDataDocument.getGeneratedId(Ground.class.getSimpleName());
		coll.save(this);
	}
}
