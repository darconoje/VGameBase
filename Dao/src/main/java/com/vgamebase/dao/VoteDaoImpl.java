package com.vgamebase.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.User;
import com.vgamebase.model.Vote;

@Repository("voteDao")
@Transactional
public class VoteDaoImpl extends AbstractDao<Integer, Vote> implements VoteDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Vote> findAll() {
		try {
			List<Vote> votes = getEntityManager().createQuery("SELECT v FROM Vote v ORDER BY id ").getResultList();
			return votes;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public Vote findByPk(long key) {
		return getByKey(key);
	}
	
	public Vote findByUserAndGamePlatform(User user, GamePlatform gameplatform) {
		try {
			Vote vote = (Vote) getEntityManager().createQuery("SELECT v FROM Vote v WHERE v.user = :user AND v.game = :gameplatform")
					.setParameter("user", user).setParameter("gameplatform", gameplatform).getSingleResult();
			return vote;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	@Override
	public Vote save(Vote v) {
		if (findByPk(v.getId()) == null) {
			getEntityManager().persist(v);
		}
		return v;
	}
	
	public void update(Vote v) {
		super.update(v);
	}
	
	public void delete(Vote v) {
		super.delete(v);
	}
	
}
