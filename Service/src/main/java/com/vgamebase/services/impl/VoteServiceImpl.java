package com.vgamebase.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgamebase.dao.VoteDao;
import com.vgamebase.model.Vote;
import com.vgamebase.services.VoteService;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteDao voteDao;
	
	public List<Vote> findAll(){
		return voteDao.findAll();
	}
	
	public Vote findByPk(long key) {
		return voteDao.findByPk(key);
	}
	
	public Vote save(Vote v) {
		return voteDao.save(v);
	}
	
	public void update(Vote v) {
		voteDao.update(v);
	}
	
	public void delete(Vote v) {
		voteDao.delete(v);
	}
	
}
