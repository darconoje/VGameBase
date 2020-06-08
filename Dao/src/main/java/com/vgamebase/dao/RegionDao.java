package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import com.vgamebase.model.Region;

public interface RegionDao {

	List<Region> findAll();

	Region findByPk(long key);

	Region findByName(String name);

	Region save(Region r);

	void update(Region r);

	void delete(Region r);

	long totalRegions();

	long totalRegionsFilter(Map<String, Object> params);

	List<Region> paginationRegionInit(int start, int recordsPerPage);

	List<Region> paginationRegionFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage);

	List<Region> paginationRegionOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage);

	List<Region> paginationRegionFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage);

}
