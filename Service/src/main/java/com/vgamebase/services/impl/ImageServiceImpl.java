package com.vgamebase.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgamebase.dao.ImageDao;
import com.vgamebase.model.Image;
import com.vgamebase.services.ImageService;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;
	
	public List<Image> findAll(){
		return imageDao.findAll();
	}
	
	public Image findByPk(long key) {
		return imageDao.findByPk(key);
	}
	
	public Image findByName(String name) {
		return imageDao.findByName(name);
	}
	
	public Image save(Image i) {
		return imageDao.save(i);
	}
	
	public void update(Image i) {
		imageDao.update(i);
	}
	
	public void delete(Image i) {
		imageDao.delete(i);
	}
	
}
