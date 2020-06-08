package com.vgamebase.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.Image;

@Repository("imageDao")
@Transactional
public class ImageDaoImpl extends AbstractDao<Integer, Image> implements ImageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> findAll() {
		try {
			List<Image> games = getEntityManager().createQuery("SELECT i FROM Image i ORDER BY id ").getResultList();
			return games;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public Image findByPk(long key) {
		return getByKey(key);
	}
	
	public Image findByName(String name) {
		try {
			Image i = (Image) getEntityManager().createQuery("SELECT i FROM Image i where i.name = :name")
					.setParameter("name", name).getSingleResult();
			return i;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Image save(Image i) {
		if (findByPk(i.getId()) == null) {
			getEntityManager().persist(i);
		}
		return i;
	}
	
	public void update(Image i) {
		super.update(i);
	}
	
	public void delete(Image i) {
		super.delete(i);
	}
	
}
