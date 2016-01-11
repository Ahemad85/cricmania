package com.cricmania.models;

import java.util.List;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;

public class State extends Base {
	private String name;
	private String country;
	
	private static JacksonDBCollection<State,String> coll = MongoDB.getCollection(State.class.getSimpleName(), State.class, String.class);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public static List<State> getAllCountries() {
		return coll.find().toArray();
	}
	public static List<State> getAllStatesByCountryId(String countryId) {
		return coll.find().is("country", countryId).toArray();
	}
	public static State findById(String id) {
		return coll.findOneById(id);
	}
	public void save() {
		this.id = MetaDataDocument.getGeneratedId(State.class.getSimpleName());
		coll.save(this);
	}
}
