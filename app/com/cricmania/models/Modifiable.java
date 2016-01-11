package com.cricmania.models;

import java.util.Date;

public abstract class Modifiable extends Base {
	protected Date deletedOn;
	protected Date updatedOn;
	public Date getDeletedOn() {
		return deletedOn;
	}
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
}
