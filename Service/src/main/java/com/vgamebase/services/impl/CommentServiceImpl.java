package com.vgamebase.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgamebase.dao.CommentDao;
import com.vgamebase.model.Comment;
import com.vgamebase.services.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	
	public List<Comment> findAll(){
		return commentDao.findAll();
	}
	
	public Comment findByPk(long key) {
		return commentDao.findByPk(key);
	}
	
	public Comment save(Comment c) {
		return commentDao.save(c);
	}
	
	public void update(Comment c) {
		commentDao.update(c);
	}
	
	public void delete(Comment c) {
		commentDao.delete(c);
	}
	
}
