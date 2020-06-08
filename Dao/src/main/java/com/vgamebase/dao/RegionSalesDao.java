package com.vgamebase.dao;

import java.util.List;

import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.Region;
import com.vgamebase.model.RegionSales;

public interface RegionSalesDao {
	
	List<RegionSales> findAll();
	
	List<RegionSales> findAllByRegion(Region region);
	
	RegionSales findByPk(long key);
	
	RegionSales findByRegionAndGamePlatform(Region region, GamePlatform gameplatform);
	
	RegionSales save(RegionSales rs);
	
	void update(RegionSales rs);
	
	void delete(RegionSales rs);

}
