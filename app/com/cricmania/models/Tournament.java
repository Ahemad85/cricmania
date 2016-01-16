package com.cricmania.models;

import java.util.Date;
import java.util.List;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.DBQuery;
import net.vz.mongodb.jackson.DBQuery.Query;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class Tournament extends Modifiable {
	private Address address;
	private String name;
	private String organizedBy;
	private List<TournamentTeam> teams;
	private Date startDate;
	private TournamentStatus tournamentStatus = TournamentStatus.YET_TO_START;
	
	private static JacksonDBCollection<Tournament,String> coll = MongoDB.getCollection(Tournament.class.getSimpleName(), Tournament.class, String.class);
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TournamentTeam> getTeams() {
		return teams;
	}
	public void setTeams(List<TournamentTeam> teams) {
		this.teams = teams;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public TournamentStatus getTournamentStatus() {
		return tournamentStatus;
	}
	public void setTournamentStatus(TournamentStatus tournamentStatus) {
		this.tournamentStatus = tournamentStatus;
	}
	public String getOrganizedBy() {
		return organizedBy;
	}
	public void setOrganizedBy(String organizedBy) {
		this.organizedBy = organizedBy;
	}


	public static enum TournamentStatus {
		YET_TO_START("Yet to start"),RUNNING("Running"),DRAW("Draw"),COMPLETED("Completed");
		private String value;
		private TournamentStatus(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public void save() {
		this.id = "TOR"+MetaDataDocument.getGeneratedId(Tournament.class.getSimpleName());
		coll.save(this);
	}
	
	public void update() {
		coll.updateById(this.id, this);
	}
	
	public static Tournament getTournamentByUsername(String username) {
		List<Tournament> tournaments = coll.find().or(DBQuery.is("email", username),DBQuery.is("_id", username)).toArray();
		if(tournaments.size() > 0) {
			return tournaments.get(0);
		}
		return null;
	}
}
