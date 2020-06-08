package com.vgamebase.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.Game;
import com.vgamebase.model.GamePublisher;
import com.vgamebase.model.Publisher;

@Repository("gamePublisherDao")
@Transactional
public class GamePublisherDaoImpl extends AbstractDao<Integer, GamePublisher> implements GamePublisherDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GamePublisher> findAll() {
		try {
			List<GamePublisher> platforms = getEntityManager().createQuery("SELECT gpu FROM GamePublisher gpu ORDER BY id ").getResultList();
			return platforms;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<GamePublisher> findAllByPublisher(Publisher publisher) {
		try {
			List<GamePublisher> gamepublishers = getEntityManager()
					.createQuery("SELECT gp FROM GamePublisher gp WHERE gp.publisher = :publisher ORDER BY id").setParameter("publisher", publisher)
					.getResultList();
			return gamepublishers;
		} catch (NoResultException ex) {
			return null;
		}		
	}
	
	public GamePublisher findByPk(long key) {
		return getByKey(key);
	}
	
	public GamePublisher findByGameAndPublisher(Game game, Publisher publisher) {
		try {
			GamePublisher gamepublisher = (GamePublisher) getEntityManager()
					.createQuery("SELECT gp FROM GamePublisher gp WHERE gp.game.id = :game AND gp.publisher.id = :publisher").setParameter("game", game.getId())
					.setParameter("publisher", publisher.getId()).getSingleResult();
			return gamepublisher;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}		
	}
	
	@Override
	public GamePublisher save(GamePublisher gpu) {
		if (findByGameAndPublisher(gpu.getGame(),gpu.getPublisher()) == null) {
			getEntityManager().persist(gpu);
			return gpu;
		}else {
			return null;
		}

	} 
	
	public void update(GamePublisher gpu) {
		super.update(gpu);
	}
	
	public void delete(GamePublisher gpu) {
		super.delete(gpu);
	}

	
}
