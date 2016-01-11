package com.cricmania.models;

import java.util.List;

import javax.persistence.Id;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class Country extends Base {
	private String name;

	private static JacksonDBCollection<Country,String> coll = MongoDB.getCollection(Country.class.getSimpleName(), Country.class, String.class);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static Country findById(String id) {
		return coll.findOneById(id);
	}
	
	public static List<Country> getAllCountries() {
		return coll.find().toArray();
	}
	public void save() {
		this.id = MetaDataDocument.getGeneratedId(Country.class.getSimpleName());
		coll.save(this);
	}
}
