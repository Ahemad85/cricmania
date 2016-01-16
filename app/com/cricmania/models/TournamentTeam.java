package com.cricmania.models;

public class TournamentTeam {
	private String teamId;
	private TournamentTeamStatus status = TournamentTeamStatus.PENDING;
	
	public enum TournamentTeamStatus {
		ACTIVE,PENDING,REJECTED,DISQUALIFY,OUT
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public TournamentTeamStatus getStatus() {
		return status;
	}

	public void setStatus(TournamentTeamStatus status) {
		this.status = status;
	} 

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof TournamentTeam) {
			return this.teamId.equals(((TournamentTeam)obj).teamId);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.teamId != null ? this.teamId.hashCode() : super.hashCode();
	}
}
