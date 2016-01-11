package com.cricmania.models;

import java.util.List;

import play.modules.mongodb.jackson.MongoDB;
import views.html.main;
import net.vz.mongodb.jackson.DBQuery;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class Login extends Base {
	private String email;
	private String password;
	private LoginType loginType;
	
	public static JacksonDBCollection<Login, String> coll = MongoDB.getCollection(Login.class.getSimpleName(), Login.class, String.class);
	
	public static enum LoginType {
		PLAYER,UMPIRE,TOURNAMENT,TEAM
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}
	
	public static Login getLoginByUsername(String username) {
		List<Login> logins = coll.find().or(DBQuery.is("email",username),DBQuery.is("_id", username)).toArray();
		if(!logins.isEmpty()) {
			return logins.get(0);
		}
		return null;
	}
	
	public void save() {
		coll.save(this);
	}
	
}
