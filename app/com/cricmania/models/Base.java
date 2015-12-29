package com.cricmania.models;

import java.util.Date;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;

public abstract class Base {

	@Id
	protected String id;
	protected Date createdOn;
	protected Date updatedOn;
	
	
	public void save() {
		JacksonDBCollection coll = MongoDB.getCollection(this.getClass().getSimpleName(), this.getClass(), String.class);
		coll.save(this);
	}
	
	public void delete() {
		JacksonDBCollection coll = MongoDB.getCollection(this.getClass().getSimpleName(), this.getClass(), String.class);
		coll.removeById(this.id);
	}
	
	public void update() {
		JacksonDBCollection coll = MongoDB.getCollection(this.getClass().getSimpleName(), this.getClass(), String.class);
		coll.updateById(this.id, this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
