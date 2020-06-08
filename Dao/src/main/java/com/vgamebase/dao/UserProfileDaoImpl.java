package com.vgamebase.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.UserProfile;



@Repository("userProfileDao")
@Transactional
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile>implements UserProfileDao{

	public UserProfile findById(long id) {
		return getByKey(id);
	}

	public UserProfile findByType(String type) {
		System.out.println("type: "+type);
		try{
			UserProfile userProfile = (UserProfile) getEntityManager()
					.createQuery("SELECT p FROM UserProfile p WHERE p.type LIKE :type")
					.setParameter("type", type)
					.getSingleResult();
			return userProfile; 
		}catch(NoResultException ex){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll(){
		List<UserProfile> userProfiles = getEntityManager()
				.createQuery("SELECT p FROM UserProfile p  ORDER BY p.type ASC")
				.getResultList();
		return userProfiles;
	}
	
	@Override
	public UserProfile save(UserProfile p) {
		if (findByType(p.getType()) == null) {
			getEntityManager().persist(p);
			return p;
		}else {
			return null;	
		}
	}
	
	public void update(UserProfile p) {
		super.update(p);
	}
	
	public void delete(UserProfile p) {
		super.delete(p);
	}
	
}
