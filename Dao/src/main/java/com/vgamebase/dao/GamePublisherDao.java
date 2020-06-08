package com.vgamebase.dao;

import java.util.List;

import com.vgamebase.model.Game;
import com.vgamebase.model.GamePublisher;
import com.vgamebase.model.Publisher;

public interface GamePublisherDao {

	List<GamePublisher> findAll();
	
	List<GamePublisher> findAllByPublisher(Publisher publisher);
	
	GamePublisher findByPk(long key);
	
	GamePublisher findByGameAndPublisher(Game game, Publisher publisher);
	
	GamePublisher save(GamePublisher gpl);
	
	void update(GamePublisher gpu);
	
	void delete(GamePublisher gpu);
	
}
