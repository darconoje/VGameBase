package com.vgamebase.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.Game;
import com.vgamebase.model.Genre;

@Repository("gameDao")
@Transactional
public class GameDaoImpl extends AbstractDao<Integer, Game> implements GameDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> findAll() {
		try {
			List<Game> games = getEntityManager().createQuery("SELECT g FROM Game g ORDER BY id ").getResultList();
			return games;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Game> findAllByGenre(Genre genre) {
		try {
			List<Game> games = getEntityManager()
					.createQuery("SELECT g FROM Game g WHERE g.genre = :genre ORDER BY id").setParameter("genre", genre)
					.getResultList();
			return games;
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Game findByPk(long key) {
		return getByKey(key);
	}

	public Game findByName(String name) {
		try {
			Game g = (Game) getEntityManager().createQuery("SELECT g FROM Game g where g.name = :name")
					.setParameter("name", name).getSingleResult();
			return g;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Game save(Game g) {
		if (findByName(g.getName()) == null) {
			getEntityManager().persist(g);
			return g;
		} else {
			return null;
		}

	}

	public void update(Game g) {
		super.update(g);
	}

	public void delete(Game g) {
		super.delete(g);
	}

	public long totalGames() {
		long totalGames = (long) getEntityManager().createQuery("SELECT COUNT(g.id) FROM Game g").getSingleResult();
		return totalGames;
	}

}
