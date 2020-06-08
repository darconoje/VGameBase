package com.vgamebase.services;

import java.util.List;

import com.vgamebase.model.Comment;

public interface CommentService {

	List<Comment> findAll();
	
	Comment findByPk(long key);
	
	Comment save(Comment c);
	
	void update(Comment c);
	
	void delete(Comment c);
	
}
