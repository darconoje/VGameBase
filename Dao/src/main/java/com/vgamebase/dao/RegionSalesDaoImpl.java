package com.vgamebase.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.Region;
import com.vgamebase.model.RegionSales;

@Repository("regionSalesDao")
@Transactional
public class RegionSalesDaoImpl extends AbstractDao<Integer, RegionSales> implements RegionSalesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<RegionSales> findAll() {
		try {
			List<RegionSales> platforms = getEntityManager().createQuery("SELECT rs FROM RegionSales rs ORDER BY id ").getResultList();
			return platforms;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RegionSales> findAllByRegion(Region region) {
		
		try {
			List<RegionSales> regionsales = getEntityManager()
					.createQuery("SELECT rs FROM RegionSales rs WHERE rs.region = :region ORDER BY id").setParameter("region", region)
					.getResultList();
			return regionsales;
		} catch (NoResultException ex) {
			return null;
		}
		
	}
	
	public RegionSales findByPk(long key) {
		return getByKey(key);
	}
	
	public RegionSales findByRegionAndGamePlatform(Region region, GamePlatform gameplatform) {
		
		try {
			RegionSales regionsales = (RegionSales) getEntityManager()
					.createQuery("SELECT rs FROM RegionSales rs WHERE rs.region.id = :region AND rs.gamePlatform.id = :gameplatform").setParameter("region", region.getId())
					.setParameter("gameplatform", gameplatform.getId()).getSingleResult();
			return regionsales;
		} catch (Exception ex) {
			return null;
		}
		
	}
	
	@Override
	public RegionSales save(RegionSales rs) {
		if (findByRegionAndGamePlatform(rs.getRegion(),rs.getGamePlatform()) == null) {
			getEntityManager().persist(rs);
			return rs;
		}else {
			return null;
		}

	}
	
	public void update(RegionSales rs) {
		super.update(rs);
	}
	
	public void delete(RegionSales rs) {
		super.delete(rs);
	}
	
}
