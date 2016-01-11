package com.cricmania.models;

import java.util.Date;

public  class Base {

	@javax.persistence.Id
	protected String id;
	protected Date createdOn = new Date();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
