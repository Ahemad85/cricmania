package controllers;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.apache.commons.codec.binary.Base64;

import com.cricmania.models.Base;
import com.cricmania.comman.BowlingType;
import com.cricmania.models.Address;
import com.cricmania.models.City;
import com.cricmania.models.Country;
import com.cricmania.models.Ground;
import com.cricmania.models.Login;
import com.cricmania.models.Login.LoginType;
import com.cricmania.models.MetaDataDocument;
import com.cricmania.models.Player;
import com.cricmania.models.Team;
import com.cricmania.models.Tournament;
import com.cricmania.models.Tournament.TournamentStatus;
import com.cricmania.models.TournamentTeam;
import com.cricmania.models.Umpire;
import com.cricmania.comman.PlayerType;
import com.cricmania.models.State;
import com.cricmania.utils.Mapper;
import com.cricmania.utils.TeamValidator;
import com.cricmania.vms.PlayerVM;
import com.cricmania.vms.SelectItem;
import com.cricmania.vms.TeamVM;
import com.cricmania.vms.TournamentVM;
import com.cricmania.vms.UmpireVM;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import play.api.libs.Codecs;
import play.data.DynamicForm;
import play.db.ebean.Transactional;
import play.libs.Crypto;
import play.libs.Json;
import play.modules.mongodb.jackson.MongoDB;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import views.html.*;
import views.html.defaultpages.error;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    
    public static Result cpanel() {
    	return ok(cpanel.render());
    }
    
    public static Result saveBase() {
    	//Base base = new Base();
    	//base.setId("1");
    	//base.save();
    	return ok(Json.toJson(""));
    }
    
    public static Result getBowlingTypes() {
    	return ok(Json.toJson(BowlingType.getAllItems()));
    }
    
    public static Result getPlayerTypes() {
    	return ok(Json.toJson(PlayerType.getAllItems()));
    }

    public static Result getCountries() {
    	List<Country> countries = Country.getAllCountries();
    	List<SelectItem> items = new ArrayList<SelectItem>(countries.size());
    	for(Country country : countries) {
    		SelectItem item = new SelectItem();
    		item.setName(country.getName());
    		item.setValue(country.getId().toString());
    		items.add(item);
    	}
    	return ok(Json.toJson(items));
    }
    
    public static Result saveCountry() {
    	String str = "ahemad";
    	String encoded = Crypto.encryptAES(str);
    	String original = Crypto.decryptAES(encoded);
    	System.out.println(encoded);
    	System.out.println(original);
    	return ok(Json.toJson(""));
    }
    
    public static Result getStatesByCountry(String countryId) {
    	List<State> states = State.getAllStatesByCountryId(countryId);
    	List<SelectItem> items = new ArrayList<SelectItem>(states.size());
    	for(State state : states) {
    		SelectItem item = new SelectItem();
    		item.setName(state.getName());
    		item.setValue(state.getId().toString());
    		items.add(item);
    	}
    	return ok(Json.toJson(items));
    }
    
    public static Result getCitiesByState(String stateId) {
    	List<City> cities = City.getAllCitiesByStateId(stateId);
    	List<SelectItem> items = new ArrayList<SelectItem>(cities.size());
    	for(City city : cities) {
    		SelectItem item = new SelectItem();
    		item.setName(city.getName());
    		item.setValue(city.getId().toString());
    		items.add(item);
    	}
    	return ok(Json.toJson(items));
    }
    
    public static Result savePlayer() {
    	JsonNode object = request().body().asJson();
    	PlayerVM playerVM1 = request().body().as(PlayerVM.class);
    	PlayerVM playerVM = Mapper.mapJsonNodeToPlayerVM(object);
    	Player player = Mapper.mapPlayerVMToPlayer(playerVM);
    	player.save();
    	Login login = new Login();
    	login.setEmail(player.getEmail());
    	login.setId(player.getId());
    	login.setLoginType(LoginType.PLAYER);
    	login.setPassword(Crypto.encryptAES(object.get("password") != null ? object.get("password").asText() : "1234"));
    	login.save();
    	return ok(Json.toJson(player.getId()));
    }
    
    public static Result saveUmpire() {
    	JsonNode object = request().body().asJson();
    	if(object.get("email") != null && !object.get("email").asText().isEmpty()) {
    		Login login = Login.getLoginByUsername(object.get("email").asText());
    		if(login != null) {
    			return ok(Json.toJson("Error : Email already exist."));
    		}
    	}
    	UmpireVM umpireVM = Mapper.mapJsonNodeToUmpireVM(object);
    	Umpire umpire = Mapper.mapUmpireVMToUmpire(umpireVM);
    	umpire.save();
    	Login login = new Login();
    	login.setEmail(umpireVM.getEmail());
    	login.setLoginType(LoginType.UMPIRE);
    	login.setId(umpire.getId());
    	login.setPassword(object.get("password") != null ? object.get("password").asText() : "1234");
    	login.save();
    	return ok(Json.toJson(umpire.getId()));
    }
    
    public static Result findPlayer() {
    	JsonNode object = request().body().asJson();
    	String email = object.get("email").asText();
    	Player p = Player.getPlayerByEmail(email);
    	if(p != null) {
    		PlayerVM playerVM = new PlayerVM();
    		playerVM.setId(p.getId());
    		playerVM.setEmail(p.getEmail());
    		playerVM.setFirstName(p.getFirstName());
    		playerVM.setLastName(p.getLastName());
    		return ok(Json.toJson(playerVM));
    	}
    	return ok(Json.toJson("not found"));
    }
    
    public static Result findUmpire() {
    	JsonNode object = request().body().asJson();
    	String email = object.get("email").asText();
    	Umpire u = Umpire.getUmpireByEmail(email);
    	if(u != null) {
    		UmpireVM umpireVM = new UmpireVM();
    		umpireVM.setId(u.getId());
    		umpireVM.setEmail(u.getEmail());
    		umpireVM.setFirstName(u.getFirstName());
    		umpireVM.setLastName(u.getLastName());
    		return ok(Json.toJson(umpireVM));
    	}
    	return ok(Json.toJson("not found"));
    }
    
    public static Result loginEntity() {
    	JsonNode object = request().body().asJson();
    	String username = object.get("username").asText();
    	String password = object.get("password").asText();
    	System.out.println(username);
    	System.out.println(password);
    	Login login = Login.getLoginByUsername(username);
    	Map result = new HashMap();
    	System.out.println(login);
    	if(login != null) {
    		if(login.getPassword().equals(Crypto.encryptAES(password))) {
    			String str = Crypto.encryptAES(username+"-"+request().remoteAddress());
    			session().put("uuid", str);
    			if(login.getLoginType() == LoginType.TOURNAMENT) {
    				result.put("url", "/tournament");
    			} else if(login.getLoginType() == LoginType.PLAYER){
    				result.put("url", "/player");
    			} else if(login.getLoginType() == LoginType.UMPIRE){
    				result.put("url", "/umpire");
    			}
    			result.put("status", "ok");
    		} else {
    			result.put("status", "error");
    		}
    	} else {
    		result.put("status", "error");
    	}
    	return ok(Json.toJson(result));
    }
    
    public static Result tournament() {
    	String uuid = null;
    	if(session().get("uuid") == null) {
    		return redirect("/cpanel");
    	}
    	uuid = session().get("uuid");
    	try {
    		String original = Crypto.decryptAES(uuid);
    		String arr[] = original.split("-");
    		if(arr.length == 2 && arr[1].equals(request().remoteAddress())) {
    			Login login = Login.getLoginByUsername(arr[0]);
    			return ok(tournament.render(login.getId()));
    		}
    	} catch(Exception e) {
    	}
    	return redirect("/cpanel");
    }
    
    public static Result saveTournament() {
    	JsonNode node = request().body().asJson();
    	TournamentVM tournamentVM = Mapper.mapJsonToTournamentVM(node);
    	Tournament tournament = Mapper.mapTournamentVMToTournament(tournamentVM);
    	tournament.save();
    	Login login = new Login();
    	login.setEmail(tournament.getId().toLowerCase()+"@cricmania.com");
    	login.setId(tournament.getId());
    	login.setLoginType(LoginType.TOURNAMENT);
    	login.setPassword(Crypto.encryptAES(node.get("password") != null ? node.get("password").asText() : "1234"));
    	login.save();
    	return ok(Json.toJson(tournament.getId()));
    }
    public static Result logout() {
    	session().remove("uuid");
    	return redirect("/cpanel");
    }
    
    public static Result saveTeam() {
    	JsonNode node = request().body().asJson();
    	List<String> errors = TeamValidator.validateTeamFromJsonNode(node);
    	Map map = new HashMap();
    	if(errors.size() > 0) {
    		map.put("status","error");
    		map.put("errors", errors);
    		return ok(Json.toJson(map));
    	}
    	Team team = Mapper.mapJsonNodeToTeam(node);
    	team.save();
    	Login login = new Login();
    	login.setEmail(team.getEmail()!= null ?team.getEmail() : team.getId().toLowerCase()+"@cricmania.com");
    	login.setId(team.getId());
    	login.setPassword(node.get("password") != null ? node.get("password").asText() : "1234");
    	login.setLoginType(LoginType.TEAM);
    	login.save();
    	map.put("status","ok");
    	map.put("id", login.getId());
    	return ok(Json.toJson(map));
    }
    
    public static Result uploadTeamLogo() {
    	try {
    		MultipartFormData form = request().body().asMultipartFormData();
    		Map<String ,String[]> map = form.asFormUrlEncoded();
    		if(map.get("id") == null || map.get("id").length == 0) {
    			return ok(Json.toJson("error"));
    		} else {
    			String id = map.get("id")[0];
    			Team team = Team.findById(id);
    			if(team != null) {
    				FilePart file = request().body().asMultipartFormData().getFile("file");
    				GridFS fs = new GridFS(Login.coll.getDB(),"photo");
    				GridFSInputFile fsInputFile = fs.createFile(file.getFile());
    				fsInputFile.setFilename(file.getFilename());
    				fsInputFile.setContentType(file.getContentType());
    				fsInputFile.save();
    				team.setLogoName(file.getFilename());
    				team.update();
    			} else {
    				return ok(Json.toJson("error"));
    			}
    		}
    		
		} catch (IOException e) {
			return ok(Json.toJson("error"));
		}
    	return ok(Json.toJson("success"));
    }
    
    public static Result getGroundsByCity(String cityId) {
    	List<Ground> grounds = Ground.getGroundsByCity(cityId);
    	List<SelectItem> items = new ArrayList<SelectItem>(grounds.size());
    	for(Ground ground : grounds) {
    		SelectItem item = new SelectItem();
    		item.setName(ground.getName());
    		item.setValue(ground.getId().toString());
    		items.add(item);
    	}
    	return ok(Json.toJson(items));
    }
    
    public static Result saveGround() {
    	JsonNode node = request().body().asJson();
    	Ground ground = new Ground();
    	String uuid = session().get("uuid");
    	if(uuid != null) {
    		try {
    			ground.setAddedBy(Crypto.decryptAES(uuid).split("-")[0]);
    		} catch(Exception e) {
    			ground.setAddedBy(request().remoteAddress());
    		}
    	} else {
    		ground.setAddedBy(request().remoteAddress());
    	}
    	ground.setName(node.get("name").asText());
    	Address address = new Address();
    	address.setCity(node.get("city").asText());
    	address.setCountry(node.get("country").asText());
    	address.setState(node.get("country").asText());
    	address.setZipcode(node.get("zipcode").asText());
    	ground.setAddress(address);
    	ground.save();
    	return ok(Json.toJson(ground.getId()));
    }
    
    public static Result loggedInTournament() {
    	String uuid = session().get("uuid");
    	if(uuid != null) {
    		String[] arr = Crypto.decryptAES(uuid).split("-");
    		if(arr.length == 2) {
    			Tournament tournament = Tournament.getTournamentByUsername(arr[0]);
    			TournamentVM tournamentVM = Mapper.mapTournamentToTournamentVM(tournament);
    			Map<String,String> countryMap = new HashMap<String,String>();
    			Map<String,String> stateMap = new HashMap<String,String>();
    			Map<String,String> cityMap = new HashMap<String,String>(); 
    			if(tournamentVM != null) {
    				tournamentVM.setTeamVMs(new ArrayList<TeamVM>());
    				if(tournament.getTeams() != null) {
    					for(TournamentTeam tournamentTeam : tournament.getTeams()) {
    						Team team = Team.findById(tournamentTeam.getTeamId());
    						TeamVM teamVM = new TeamVM();
    						teamVM.setId(team.getId());
    						teamVM.setContactPerson(team.getContactNumber());
    						teamVM.setContactPerson(team.getContactPerson());
    						teamVM.setEmail(team.getEmail());
    						teamVM.setName(team.getName());
    						teamVM.setSponsoredBy(team.getSponsoredBy());
    						if(team != null) {
    							if(team.getAddress()!=null) {
    								Address address = team.getAddress();
    								if(address.getCity()!=null) {
    									if(cityMap.get(address.getCity()) == null) {
    										City city = City.findById(address.getCity());
    										if(city != null) {
    											cityMap.put(city.getId(), city.getName());
    											teamVM.setCity(city.getName());
    										}
    									} else {
    										teamVM.setCity(cityMap.get(address.getCity()));
    									}
    								}
    								if(address.getState()!=null) {
    									if(stateMap.get(address.getState()) == null) {
    										State state = State.findById(address.getState());
    										if(state != null) {
    											stateMap.put(state.getId(), state.getName());
    											teamVM.setState(state.getName());
    										}
    									} else {
    										teamVM.setState(stateMap.get(address.getState()));
    									}
    								}
    								if(address.getCountry()!=null) {
    									if(countryMap.get(address.getCountry()) == null) {
    										Country country = Country.findById(address.getCountry());
    										if(country != null) {
    											countryMap.put(country.getId(), country.getName());
    											teamVM.setCountry(country.getName());
    										}
    									} else {
    										teamVM.setCountry(countryMap.get(address.getCountry()));
    									}
    								}
    							}
    						}
    						tournamentVM.getTeamVMs().add(teamVM);
    					}
    				}
    				return ok(Json.toJson(tournamentVM));
    			}
    		}
    	}
    	return ok(Json.toJson("/cpanel"));
    }
    
    public static Result addTeamToTournament() {
    	JsonNode node = request().body().asJson();
    	String teamId = node.get("teamId") != null ? node.get("teamId").asText() : null;
    	Team teamEntity = null;
    	if(teamId == null) {
    		return ok(Json.toJson("team id is empty"));
    	} else {
    		teamEntity = Team.findById(teamId);
    		if(teamEntity == null) {
    			return ok(Json.toJson("invalid team id"));
    		}
    	}
    	String uuid = session().get("uuid");
    	if(uuid != null) {
    		try {
    			String[] arr = Crypto.decryptAES(uuid).split("-");
        		if(arr.length == 2) {
        			Tournament tournament = Tournament.getTournamentByUsername(arr[0]);
        			if(tournament != null) {
        				if(tournament.getTeams() == null) {
        					List<TournamentTeam> teams = new ArrayList<TournamentTeam>();
        					TournamentTeam tournamentTeam = new TournamentTeam();
        					tournamentTeam.setTeamId(teamId);
        					teams.add(tournamentTeam);
        					tournament.setTeams(teams);
        				} else {
        					for(TournamentTeam team : tournament.getTeams()) {
        						if(team.getTeamId().equals(teamId)) {
        							return ok(Json.toJson("team already present"));
        						}
        					}
        					TournamentTeam tournamentTeam = new TournamentTeam();
        					tournamentTeam.setTeamId(teamId);
        					tournament.getTeams().add(tournamentTeam);
        				}
        				tournament.update();
        				TeamVM teamVM = new TeamVM();
        				teamVM.setId(teamEntity.getId());
						teamVM.setContactPerson(teamEntity.getContactNumber());
						teamVM.setContactPerson(teamEntity.getContactPerson());
						teamVM.setEmail(teamEntity.getEmail());
						teamVM.setName(teamEntity.getName());
						teamVM.setSponsoredBy(teamEntity.getSponsoredBy());
						if(teamEntity.getAddress()!=null) {
							Address address = teamEntity.getAddress();
							if(address.getCity()!=null) {
								City city = City.findById(address.getCity());
								if(city != null) {
									teamVM.setCity(city.getName());
								}
							}
							if(address.getState()!=null) {
								State state = State.findById(address.getState());
								if(state != null) {
									teamVM.setState(state.getName());
								}
							}
							if(address.getCountry()!=null) {
								Country country = Country.findById(address.getCountry());
								if(country != null) {
									teamVM.setCountry(country.getName());
								}
							}
						}
        				return ok(Json.toJson(teamVM));
        			}
        		}
    		} catch(Exception e) {	
    		}
    	} 
    	return ok(Json.toJson("not authenticated"));
    }
    
    public static Result searchTeam() {
    	List<Team> teams = Team.getTeamByQuery(request().getQueryString("q"));
    	List<SelectItem> results = new ArrayList<SelectItem>(teams.size());
    	for(Team team : teams) {
    		SelectItem item = new SelectItem();
    		item.setName(team.getName()+" ["+team.getId()+"]");
    		item.setValue(team.getId());
    		results.add(item);
    	}
    	return ok(Json.toJson(results));
    }
    
    public static Result removeTeamFromTournam() {
    	JsonNode node = request().body().asJson();
    	String teamId = node.get("teamId") != null ? node.get("teamId").asText() : null;
    	String uuid = session().get("uuid");
    	if(uuid == null) {
    		return ok(Json.toJson("not authenticated"));
    	}
    	if(teamId != null) {
    		try {
    			String[] arr = Crypto.decryptAES(uuid).split("-");
        		if(arr.length == 2) {
        			Tournament tournament = Tournament.getTournamentByUsername(arr[0]);
        			if(tournament != null) {
        				if(tournament.getTeams() == null) {
        					return ok(Json.toJson("teams not found"));
        				} else {
        					if(tournament.getTeams().remove(teamId)) {
        						tournament.update();
        						return ok(Json.toJson("ok"));
        					}
        				}
        			}
        		}
    		} catch(Exception e) {
    		}
    	} 
    	return ok(Json.toJson("no change"));
    }
    
    public static Result playerSearch() {
    	List<Player> players = Player.getPlayerByQuery(request().getQueryString("q"));
    	List<SelectItem> results = new ArrayList<SelectItem>(players.size());
    	for(Player player : players) {
    		SelectItem item = new SelectItem();
    		item.setName(player.getFirstName() +"  "+player.getLastName() +" ["+player.getId()+"]");
    		item.setValue(player.getId());
    		results.add(item);
    	}
    	return ok(Json.toJson(results));
    }
}
