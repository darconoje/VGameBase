package com.vgamebase.dao;

import java.util.List;

import com.vgamebase.model.Game;
import com.vgamebase.model.Genre;

public interface GameDao {

	List<Game> findAll();

	List<Game> findAllByGenre(Genre genre);

	Game findByPk(long key);

	Game findByName(String name);

	Game save(Game g);

	void update(Game g);

	void delete(Game g);

	long totalGames();

}
