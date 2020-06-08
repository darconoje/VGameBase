package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.GamePublisher;
import com.vgamebase.model.Platform;

public interface GamePlatformDao {

	List<GamePlatform> findAll();

	List<GamePlatform> findAllByPlatform(Platform platform);
	
	List<Integer> findAllReleaseYears();

	GamePlatform findByPk(long key);

	GamePlatform findByGamePublisherAndPlatform(GamePublisher gamepublisher, Platform platform);

	GamePlatform save(GamePlatform gpl);

	void update(GamePlatform gpl);

	void delete(GamePlatform gpl);

	long totalGamePlatforms();

	long totalGamePlatformsFilter(Map<String, Object> params, boolean hideInactives);

	List<GamePlatform> paginationGamePlatformInit(int start, int recordsPerPage, boolean hideInactives);

	List<GamePlatform> paginationGamePlatformFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage, boolean hideInactives);

	List<GamePlatform> paginationGamePlatformOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage, boolean hideInactives);

	List<GamePlatform> paginationGamePlatformFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage, boolean hideInactives);

}
