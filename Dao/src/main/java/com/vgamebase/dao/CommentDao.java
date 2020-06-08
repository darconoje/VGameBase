package com.vgamebase.dao;

import java.util.List;

import com.vgamebase.model.Comment;

public interface CommentDao {
	
	List<Comment> findAll();
	
	Comment findByPk(long key);
	
	Comment save(Comment c);
	
	void update(Comment c);
	
	void delete(Comment c);
	
}
