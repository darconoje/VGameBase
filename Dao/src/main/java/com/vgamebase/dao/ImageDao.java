package com.vgamebase.dao;

import java.util.List;

import com.vgamebase.model.Image;

public interface ImageDao {

	List<Image> findAll();
	
	Image findByPk(long key);
	
	Image findByName(String name);
	
	Image save(Image i);
	
	void update(Image i);
	
	void delete(Image i);
	
}
