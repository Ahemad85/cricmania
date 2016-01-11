package com.cricmania.models;

import java.util.List;

import com.mongodb.DBObject;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;

public class City extends Base {
	private String name;
	private String state;
	private static JacksonDBCollection<City,String> coll = MongoDB.getCollection(City.class.getSimpleName(), City.class, String.class);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public static List<City> getAllCities() {
		return coll.find().toArray();
	}
	public static List<City> getAllCitiesByStateId(String stateId) {
		return coll.find().is("state", stateId).toArray();
	}
	
	public static City findById(String id) {
		return coll.findOneById(id);
	}
	public void save() {
		this.id = MetaDataDocument.getGeneratedId(City.class.getSimpleName());
		coll.save(this);
	} 
}
