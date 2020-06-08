package com.vgamebase.model.constant;

public enum UserProfileType {
	USER("USER"),
	MODERATOR("MODERATOR"),
	GESTOR("GESTOR"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
