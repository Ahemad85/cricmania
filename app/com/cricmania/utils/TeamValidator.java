package com.cricmania.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cricmania.models.Player;
import com.fasterxml.jackson.databind.JsonNode;

public class TeamValidator {

	public static List<String> validateTeamFromJsonNode(JsonNode node) {
		List<String> errors = new ArrayList<String>();
		if(node.get("name") == null || node.get("name").asText().equals("null")) {
			errors.add("Team name is mandatory.");
		}
		if(node.get("email") == null || node.get("email").asText().equals("null")) {
			errors.add("Email address is mandatory.");
		}
		if(node.get("password") == null || node.get("password").asText().equals("null")) {
			errors.add("Password is mandatory.");
		}
		if(node.get("contactPerson") == null || node.get("contactPerson").asText().equals("null")) {
			errors.add("Contact Person Name is mandatory.");
		}
		if(node.get("contactNumber") == null || node.get("contactNumber").asText().equals("null")) {
			errors.add("Contact Number is mandatory.");
		}
		if(node.get("password") != null && !node.get("password").equals(node.get("cpassword"))) {
			errors.add("Password mismatch.");
		}
		if(node.get("players") == null || node.get("players").asText().equals("null")) {
			errors.add("11 Players are mandatory.");
		} else {
			if(node.get("players").size() < 11) {
				errors.add("11 Players are mandatory.");
			} else {
				Map<String, Boolean> track = new HashMap<String,Boolean>(3);
				Set<String> playerTrack = new HashSet<String>(15);
				int index = 1;
				for(JsonNode player : node.get("players")) {
					
					System.out.println(player.get("player"));
					if(player.get("player") == null || player.get("player").equals("null")) {
						errors.add("Player id of "+index +" is empty");
					} else {
						if(!playerTrack.add(player.get("player").asText())) {
							errors.add("Player with "+player.get("player").asText() +" id is added more than one time");
						}
						Player p = Player.getPlayerById(player.get("player").asText());
						if(p == null) {
							errors.add("Player "+index +" is not valid player.");
						}
					}
					if(player.get("isCaptain") != null && player.get("isCaptain").asBoolean()) {
						if(track.get("Captain") != null && track.get("Captain")) {
							errors.add("Captain can not be more than one");
						} else {
							track.put("Captain", true);
						}
					}
					if(player.get("isViceCaptain") != null && player.get("isViceCaptain").asBoolean()) {
						if(track.get("ViceCaptain") != null && track.get("ViceCaptain")) {
							errors.add("Vice captain can not be more than one");
						} else {
							track.put("ViceCaptain", true);
						}
					}
					if(player.get("isKeeper") != null && player.get("isKeeper").asBoolean()) {
						track.put("Keeper", true);
					}
					index++;
				}
				if( track.get("Captain") == null) {
					errors.add("At least one Captain required.");
				}
				if( track.get("ViceCaptain") == null) {
					errors.add("At least one Vice captain required.");
				}
				if( track.get("Keeper") == null) {
					errors.add("At least one keeper required.");
				}
			}
		}
		return errors;
	}
}
