package com.vgamebase.dao;

import java.util.List;

import com.vgamebase.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(long id);
	
	UserProfile save(UserProfile p);
	
	void update(UserProfile p);
	
	void delete(UserProfile p);
	
}
