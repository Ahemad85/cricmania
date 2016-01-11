package com.cricmania.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cricmania.comman.BowlingType;
import com.cricmania.comman.PlayerType;
import com.cricmania.comman.PlayingStyle;
import com.cricmania.models.Address;
import com.cricmania.models.Player;
import com.cricmania.models.PlayerMetaData;
import com.cricmania.models.Team;
import com.cricmania.models.Tournament;
import com.cricmania.models.Tournament.TournamentStatus;
import com.cricmania.models.Umpire;
import com.cricmania.vms.PlayerVM;
import com.cricmania.vms.TournamentVM;
import com.cricmania.vms.UmpireVM;
import com.fasterxml.jackson.databind.JsonNode;

public class Mapper {

	public static PlayerVM mapJsonNodeToPlayerVM(JsonNode node) {
		if(node != null) {
			PlayerVM playerVM = new PlayerVM();
			playerVM.setId(node.get("id")!=null ? node.get("id").asText() : null);
			try {
				playerVM.setBattingStyle(node.get("battingStyle") != null ? PlayingStyle.valueOf(node.get("battingStyle").asText()) : null);
			} catch(Exception e) {
				playerVM.setBattingStyle(null);
			}
			try {
				playerVM.setBowlingStyle(node.get("bowlingStyle") != null ? PlayingStyle.valueOf(node.get("bowlingStyle").asText()) : null);
			} catch(Exception e) {
				playerVM.setBattingStyle(null);
			}
			if(node.get("bowlingTypes") != null) {
				System.out.println(node.get("bowlingTypes").size());
				playerVM.setBowlingTypes(new ArrayList<BowlingType>(node.get("bowlingTypes").size()));
				for(JsonNode n : node.get("bowlingTypes")) {
					try {
						if(n != null) {
							playerVM.getBowlingTypes().add(BowlingType.valueOf(n.asText()));
						}
					} catch(Exception e) {
					}
				}
			}
			playerVM.setCity(node.get("city") != null ? node.get("city").asText() : null);
			playerVM.setContactNumber(node.get("contactNumber") != null ? node.get("contactNumber").asText() : null);
			playerVM.setCountry(node.get("country") != null ? node.get("country").asText() : null);
			playerVM.setDob(node.get("dob") != null ? node.get("dob").asText() : null);
			playerVM.setEmail(node.get("email") != null ? node.get("email").asText() : null);
			playerVM.setFirstName(node.get("firstName") != null ? node.get("firstName").asText() : null);
			playerVM.setIsKeeper(node.get("isKeeper") != null ? node.get("isKeeper").asBoolean() : null);
			playerVM.setLastName(node.get("lastName") != null ? node.get("lastName").asText() : null);
			playerVM.setMiddleName(node.get("middleName") != null ? node.get("middleName").asText() : null);
			try {
				playerVM.setPlayerType(node.get("playerType") != null ? PlayerType.valueOf(node.get("playerType").asText()) : null);
			} catch(Exception e) {
				playerVM.setPlayerType(null);
			}
			playerVM.setState(node.get("state") != null ? node.get("state").asText() : null);
			playerVM.setStreet(node.get("street") != null ? node.get("street").asText() : null);
			playerVM.setZipcode(node.get("zipcode") != null ? node.get("zipcode").asText() : null);
			return playerVM;
		} else {
			return null;
		}
	}
	public static UmpireVM mapJsonNodeToUmpireVM(JsonNode node) {
		if(node != null) {
			UmpireVM umpireVM = new UmpireVM();
			umpireVM.setId(node.get("id")!=null ? node.get("id").asText() : null);
			umpireVM.setCity(node.get("city") != null ? node.get("city").asText() : null);
			umpireVM.setContactNumber(node.get("contactNumber") != null ? node.get("contactNumber").asText() : null);
			umpireVM.setCountry(node.get("country") != null ? node.get("country").asText() : null);
			umpireVM.setDob(node.get("dob") != null ? node.get("dob").asText() : null);
			umpireVM.setEmail(node.get("email") != null ? node.get("email").asText() : null);
			umpireVM.setFirstName(node.get("firstName") != null ? node.get("firstName").asText() : null);
			umpireVM.setLastName(node.get("lastName") != null ? node.get("lastName").asText() : null);
			umpireVM.setMiddleName(node.get("middleName") != null ? node.get("middleName").asText() : null);
			umpireVM.setState(node.get("state") != null ? node.get("state").asText() : null);
			umpireVM.setStreet(node.get("street") != null ? node.get("street").asText() : null);
			umpireVM.setZipcode(node.get("zipcode") != null ? node.get("zipcode").asText() : null);
			return umpireVM;
		} else {
			return null;
		}
	}
	
	public static TournamentVM mapJsonToTournamentVM(JsonNode node) {
		if(node != null) {
			TournamentVM tournamentVM = new TournamentVM();
			tournamentVM.setCity(node.get("city") != null ? node.get("city").asText() : null);
			tournamentVM.setCountry(node.get("country") != null ? node.get("country").asText() : null);
			tournamentVM.setId(node.get("id")!=null ? node.get("id").asText() : null);
			tournamentVM.setName(node.get("name") != null ? node.get("name").asText() : null);
			tournamentVM.setOrganizedBy(node.get("organizedBy") != null ? node.get("organizedBy").asText() : null);
			tournamentVM.setStartDate(node.get("startDate") != null ? node.get("startDate").asText() : null);
			tournamentVM.setState(node.get("state") != null ? node.get("state").asText() : null);
			tournamentVM.setZipcode(node.get("zipcode") != null ? node.get("zipcode").asText() : null);
			return tournamentVM;
		}
		return null;
	}
	
	public static Tournament mapTournamentVMToTournament(TournamentVM tournamentVM) {
		if(tournamentVM == null)
			return null;
		Tournament tournament = new Tournament();
    	tournament.setName(tournamentVM.getName());
    	tournament.setOrganizedBy(tournamentVM.getOrganizedBy());
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	try {
    		tournament.setStartDate(df.parse(tournamentVM.getStartDate()));
    		if(tournament.getStartDate().getTime() < new Date().getTime()) {
    			tournament.setTournamentStatus(TournamentStatus.RUNNING);
    		}
    	} catch(Exception e) {}
    	Address address = new Address();
    	address.setCity(tournamentVM.getCity());
    	address.setCountry(tournamentVM.getCountry());
    	address.setState(tournamentVM.getState());
    	address.setZipcode(tournamentVM.getZipcode());
    	tournament.setAddress(address);
    	return tournament;
	}
	
	public static TournamentVM mapTournamentToTournamentVM(Tournament tournament) {
		if(tournament == null)
			return null;
		TournamentVM tournamentVM = new TournamentVM();
		tournamentVM.setId(tournament.getId());
		tournamentVM.setName(tournament.getName());
		tournamentVM.setOrganizedBy(tournament.getOrganizedBy());
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	if(tournament.getStartDate() != null) {
    		tournamentVM.setStartDate(df.format(tournament.getStartDate()));
    	}
    	if(tournament.getAddress() != null) {
    		tournamentVM.setCity(tournament.getAddress().getCity());
    		tournamentVM.setCountry(tournament.getAddress().getCountry());
    		tournamentVM.setState(tournament.getAddress().getState());
    		tournamentVM.setZipcode(tournament.getAddress().getZipcode());
    	}
    	return tournamentVM;
	}
	
	public static Umpire mapUmpireVMToUmpire(UmpireVM umpireVM) {
		if(umpireVM == null)
			return null;
		Address address = new Address();
    	address.setCity(umpireVM.getCity());
    	address.setCountry(umpireVM.getCountry());
    	address.setState(umpireVM.getState());
    	address.setStreet(umpireVM.getStreet());
    	address.setZipcode(umpireVM.getZipcode());
    	Umpire umpire = new Umpire();
    	umpire.setAddress(address);
    	umpire.setContactNumber(umpireVM.getContactNumber());
    	umpire.setDeletedOn(null);
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	try {
    		umpire.setDob(df.parse(umpireVM.getDob()));
		} catch (ParseException e) {
		}
    	umpire.setEmail(umpireVM.getEmail());
    	umpire.setFirstName(umpireVM.getFirstName());
    	umpire.setLastName(umpireVM.getLastName());
    	umpire.setMiddleName(umpireVM.getMiddleName());
    	umpire.setUpdatedOn(null);
    	return umpire;
	}
	
	public static Player mapPlayerVMToPlayer(PlayerVM playerVM) {
		if(playerVM == null)
			return null;
		Player player = new Player();
    	Address address = new Address();
    	address.setCity(playerVM.getCity());
    	address.setCountry(playerVM.getCountry());
    	address.setState(playerVM.getState());
    	address.setStreet(playerVM.getStreet());
    	address.setZipcode(playerVM.getZipcode());
    	player.setAddress(address);
    	player.setBattingStyle(playerVM.getBattingStyle());
    	player.setBowlingStyle(playerVM.getBowlingStyle());
    	player.setContactNumber(playerVM.getContactNumber());
    	player.setDeletedOn(null);
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	try {
			player.setDob(df.parse(playerVM.getDob()));
		} catch (ParseException e) {
		}
    	player.setEmail(playerVM.getEmail());
    	player.setFirstName(playerVM.getFirstName());
    	player.setIsKeeper(playerVM.getIsKeeper());
    	player.setLastName(playerVM.getLastName());
    	player.setMiddleName(playerVM.getMiddleName());
    	player.setPlayerType(playerVM.getPlayerType());
    	player.setUpdatedOn(null);
    	return player;
	}
	
	public static Team mapJsonNodeToTeam(JsonNode node) {
		if(node == null)
			return null;
		Team team = new Team();
		team.setContactNumber(node.get("contactNumber")!=null ?node.get("contactNumber").asText() : null);
		team.setContactPerson(node.get("contactPerson")!=null ? node.get("contactPerson").asText() : null);
		team.setEmail(node.get("email") != null ? node.get("email").asText() : null);
		team.setName(node.get("name") != null ? node.get("name").asText() : null);
		team.setSponsoredBy(node.get("sponsoredBy") != null ? node.get("sponsoredBy").asText() : null);
		Address address = new Address();
		address.setCity(node.get("city") != null ? node.get("city").asText() : null);
		address.setCountry(node.get("country") != null ? node.get("country").asText() : null);
		address.setState(node.get("state") != null ? node.get("state").asText() : null);
		address.setZipcode(node.get("zipcode") != null ? node.get("zipcode").asText() : null);
		team.setAddress(address);
		if(node.get("players") != null) {
			List<PlayerMetaData> datas = new ArrayList<PlayerMetaData>(node.get("players").size());
			for(JsonNode player : node.get("players")) {
				PlayerMetaData data = new PlayerMetaData();
				data.setBatsman(player.get("isBatsman") != null ?player.get("isBatsman").asBoolean() : null);
				data.setBowler(player.get("isBowler") != null ? player.get("isBowler").asBoolean() : null);
				data.setCaptain(player.get("isCaptain") != null ? player.get("isCaptain").asBoolean() : null);
				data.setKeeper(player.get("isKeeper")!=null ? player.get("isKeeper").asBoolean() : false);
				data.setPlayer(player.get("player") != null ? player.get("player").asText() : null);
				data.setViceCaptain(player.get("isViceCaptain")!=null ? player.get("isViceCaptain").asBoolean() : false);
				datas.add(data);
			}
			team.setPlayers(datas);
		}
		return team;
	}
}
