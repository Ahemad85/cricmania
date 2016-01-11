package com.cricmania.models;

public class PlayerMetaData {
	private String player;
	private boolean isCaptain;
	private boolean isViceCaptain;
	private boolean isKeeper;
	private boolean isBowler;
	private boolean isBatsman;
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public boolean isCaptain() {
		return isCaptain;
	}
	public void setCaptain(boolean isCaptain) {
		this.isCaptain = isCaptain;
	}
	public boolean isViceCaptain() {
		return isViceCaptain;
	}
	public void setViceCaptain(boolean isViceCaptain) {
		this.isViceCaptain = isViceCaptain;
	}
	public boolean isKeeper() {
		return isKeeper;
	}
	public void setKeeper(boolean isKeeper) {
		this.isKeeper = isKeeper;
	}
	public boolean isBowler() {
		return isBowler;
	}
	public void setBowler(boolean isBowler) {
		this.isBowler = isBowler;
	}
	public boolean isBatsman() {
		return isBatsman;
	}
	public void setBatsman(boolean isBatsman) {
		this.isBatsman = isBatsman;
	}
	
}
