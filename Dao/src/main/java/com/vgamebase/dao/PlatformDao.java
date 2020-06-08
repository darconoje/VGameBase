package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import com.vgamebase.model.Platform;

public interface PlatformDao {

	List<Platform> findAll();

	Platform findByPk(long key);

	Platform findByName(String name);

	Platform save(Platform p);

	void update(Platform p);

	void delete(Platform p);

	long totalPlatforms();

	long totalPlatformsFilter(Map<String, Object> params);

	List<Platform> paginationPlatformInit(int start, int recordsPerPage);

	List<Platform> paginationPlatformFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage);

	List<Platform> paginationPlatformOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage);

	List<Platform> paginationPlatformFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage);
	
	List<Platform> paginationPlatformInit2(int start, int recordsPerPage);

	List<Platform> paginationPlatformFilterNoOrder2(int start, Map<String, Object> params, int recordsPerPage);

	List<Platform> paginationPlatformOrderNoFilter2(int start, String order, String ascDesc, int recordsPerPage);

	List<Platform> paginationPlatformFilterAndOrder2(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage);	

}
