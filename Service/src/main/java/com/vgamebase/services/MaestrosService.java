package com.vgamebase.services;

import java.util.List;
import java.util.Map;

import com.vgamebase.model.Game;
import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.GamePublisher;
import com.vgamebase.model.Genre;
import com.vgamebase.model.Platform;
import com.vgamebase.model.Publisher;
import com.vgamebase.model.Region;
import com.vgamebase.model.RegionSales;
import com.vgamebase.model.UserProfile;

public interface MaestrosService {

	List<Genre> findAllGenres();

	Genre findGenreByPk(long key);
	
	Genre findGenreByName(String name);

	Genre saveGenre(Genre g);

	void updateGenre(Genre g);

	void deleteGenre(Genre g);

	long totalGenres();

	long totalGenresFilter(Map<String, Object> params);

	List<Genre> paginationGenreInit(int start, int recordsPerPage);

	List<Genre> paginationGenreFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage);

	List<Genre> paginationGenreOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage);

	List<Genre> paginationGenreFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage);

	List<Platform> findAllPlatforms();

	Platform findPlatformByPk(long key);
	
	Platform findPlatformByName(String name);

	Platform savePlatform(Platform p);

	void updatePlatform(Platform p);

	void deletePlatform(Platform p);

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

	List<Publisher> findAllPublishers();

	Publisher findPublisherByPk(long key);
	
	Publisher findPublisherByName(String name);

	Publisher savePublisher(Publisher p);

	void updatePublisher(Publisher p);

	void deletePublisher(Publisher p);

	long totalPublishers();

	long totalPublishersFilter(Map<String, Object> params);

	List<Publisher> paginationPublisherInit(int start, int recordsPerPage);

	List<Publisher> paginationPublisherFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage);

	List<Publisher> paginationPublisherOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage);

	List<Publisher> paginationPublisherFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage);
	
	List<Publisher> paginationPublisherInit2(int start, int recordsPerPage);

	List<Publisher> paginationPublisherFilterNoOrder2(int start, Map<String, Object> params, int recordsPerPage);

	List<Publisher> paginationPublisherOrderNoFilter2(int start, String order, String ascDesc, int recordsPerPage);

	List<Publisher> paginationPublisherFilterAndOrder2(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage);

	List<Region> findAllRegions();

	Region findRegionByPk(long key);
	
	Region findRegionByName(String name);

	Region saveRegion(Region r);

	void updateRegion(Region r);

	void deleteRegion(Region r);

	long totalRegions();
	
	long totalRegionsFilter(Map<String, Object> params);

	List<Region> paginationRegionInit(int start, int recordsPerPage);

	List<Region> paginationRegionFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage);

	List<Region> paginationRegionOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage);

	List<Region> paginationRegionFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage);	

	List<UserProfile> findAllUserProfiles();

	UserProfile findUserProfileByType(String type);

	UserProfile findUserProfileById(long id);

	UserProfile saveUserProfile(UserProfile p);

	void updateUserProfile(UserProfile p);

	void deleteUserProfile(UserProfile p);

	List<RegionSales> findAllRegionSales();
	
	List<RegionSales> findAllRegionSalesByRegion(Region region);

	RegionSales findRegionSalesByPk(long key);
	
	RegionSales findRegionSalesByRegionAndGamePlatform(Region region, GamePlatform gameplatform);

	RegionSales saveRegionSales(RegionSales rs);

	void updateRegionSales(RegionSales rs);

	void deleteRegionSales(RegionSales rs);

	List<GamePlatform> findAllGamePlatforms();
	
	List<GamePlatform> findAllGamePlatformsByPlatform(Platform platform);
	
	List<Integer> findAllReleaseYears();

	GamePlatform findGamePlatformByPk(long key);
	
	GamePlatform findGamePlatformByGamePublisherAndPlatform(GamePublisher gamepublisher, Platform platform);
	
	long totalGamePlatforms();
	
	long totalGamePlatformsFilter(Map<String, Object> params, boolean hideInactives);

	List<GamePlatform> paginationGamePlatformInit(int start, int recordsPerPage, boolean hideInactives);

	List<GamePlatform> paginationGamePlatformFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage, boolean hideInactives);

	List<GamePlatform> paginationGamePlatformOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage, boolean hideInactives);

	List<GamePlatform> paginationGamePlatformFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage, boolean hideInactives);	

	GamePlatform saveGamePlatform(GamePlatform gpl);

	void updateGamePlatform(GamePlatform gpl);

	void deleteGamePlatform(GamePlatform gpl);

	List<GamePublisher> findAllGamePublishers();
	
	List<GamePublisher> findAllGamePublishersByPublisher(Publisher publisher);

	GamePublisher findGamePublisherByPk(long key);
	
	GamePublisher findGamePublisherByGameAndPublisher(Game game, Publisher publisher);

	GamePublisher saveGamePublisher(GamePublisher gpu);

	void updateGamePublisher(GamePublisher gpu);

	void deleteGamePublisher(GamePublisher gpu);

}
