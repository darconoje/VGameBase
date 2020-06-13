package com.vgamebase.dao;

import java.util.List;

import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.User;
import com.vgamebase.model.Vote;

public interface VoteDao {

	List<Vote> findAll();
	
	Vote findByPk(long key);
	
	Vote findByUserAndGamePlatform(User user, GamePlatform gameplatform);
	
	Vote save(Vote g);
	
	void update(Vote g);
	
	void delete(Vote g);
	
}
