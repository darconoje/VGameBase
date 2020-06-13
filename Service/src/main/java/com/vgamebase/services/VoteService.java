package com.vgamebase.services;

import java.util.List;

import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.User;
import com.vgamebase.model.Vote;

public interface VoteService {

	List<Vote> findAll();
	
	Vote findByPk(long key);
	
	Vote findByUserAndGamePlatform(User user, GamePlatform gameplatform);
	
	Vote save(Vote g);
	
	void update(Vote g);
	
	void delete(Vote g);
	
}
