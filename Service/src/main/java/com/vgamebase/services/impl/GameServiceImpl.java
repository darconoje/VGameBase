package com.vgamebase.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgamebase.dao.GameDao;
import com.vgamebase.model.Game;
import com.vgamebase.model.Genre;
import com.vgamebase.services.GameService;

@Service("gameService")
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;
	
	public List<Game> findAll(){
		return gameDao.findAll();
	}
	
	public List<Game> findAllByGenre(Genre genre) {
		return gameDao.findAllByGenre(genre);
	}
	
	public Game findByPk(long key) {
		return gameDao.findByPk(key);
	}
	
	public Game findByName(String name) {
		return gameDao.findByName(name);
	}
	
	public Game save(Game g) {
		return gameDao.save(g);
	}
	
	public void update(Game g) {
		gameDao.update(g);
	}
	
	public void delete(Game g) {
		gameDao.delete(g);
	}
	
	public long totalGames() {
		return gameDao.totalGames();
	}
	
}
