package com.vgamebase.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgamebase.dao.GamePlatformDao;
import com.vgamebase.dao.GamePublisherDao;
import com.vgamebase.dao.GenreDao;
import com.vgamebase.dao.PlatformDao;
import com.vgamebase.dao.PublisherDao;
import com.vgamebase.dao.RegionDao;
import com.vgamebase.dao.RegionSalesDao;
import com.vgamebase.dao.UserProfileDao;
import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.GamePublisher;
import com.vgamebase.model.Genre;
import com.vgamebase.model.Platform;
import com.vgamebase.model.Publisher;
import com.vgamebase.model.Region;
import com.vgamebase.model.RegionSales;
import com.vgamebase.model.UserProfile;
import com.vgamebase.model.Game;
import com.vgamebase.services.MaestrosService;

@Service("maestrosService")
public class MaestrosServiceImpl implements MaestrosService {

	@Autowired
	private GenreDao genreDao;

	@Autowired
	private PlatformDao platformDao;

	@Autowired
	private PublisherDao publisherDao;

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private UserProfileDao userProfileDao;

	@Autowired
	private RegionSalesDao regionSalesDao;

	@Autowired
	private GamePlatformDao gamePlatformDao;

	@Autowired
	private GamePublisherDao gamePublisherDao;

	public List<Genre> findAllGenres() {
		return genreDao.findAll();
	}

	public Genre findGenreByPk(long key) {
		return genreDao.findByPk(key);
	}
	
	public Genre findGenreByName(String name) {
		return genreDao.findByName(name);
	}

	public Genre saveGenre(Genre g) {
		return genreDao.save(g);
	}

	public void updateGenre(Genre g) {
		genreDao.update(g);
	}

	public void deleteGenre(Genre g) {
		genreDao.delete(g);
	}

	public long totalGenres() {
		return genreDao.totalGenres();
	}

	public long totalGenresFilter(Map<String, Object> params) {
		return genreDao.totalGenresFilter(params);
	}

	public List<Genre> paginationGenreInit(int start, int recordsPerPage) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return genreDao.paginationGenreInit(begin, recordsPerPage);
		
	}

	public List<Genre> paginationGenreFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return genreDao.paginationGenreFilterNoOrder(begin, params, recordsPerPage);
		
	}

	public List<Genre> paginationGenreOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return genreDao.paginationGenreOrderNoFilter(begin, order, ascDesc, recordsPerPage);
		
	}

	public List<Genre> paginationGenreFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return genreDao.paginationGenreFilterAndOrder(begin, params, order, ascDesc, recordsPerPage);
		
	}

	public List<Platform> findAllPlatforms() {
		return platformDao.findAll();
	}

	public Platform findPlatformByPk(long key) {
		return platformDao.findByPk(key);
	}
	
	public Platform findPlatformByName(String name) {
		return platformDao.findByName(name);
	}

	public Platform savePlatform(Platform p) {
		return platformDao.save(p);
	}

	public void updatePlatform(Platform p) {
		platformDao.update(p);
	}

	public void deletePlatform(Platform p) {
		platformDao.delete(p);
	}

	public long totalPlatforms() {
		return platformDao.totalPlatforms();
	}
	
	public long totalPlatformsFilter(Map<String, Object> params) {
		return platformDao.totalPlatformsFilter(params);
	}

	public List<Platform> paginationPlatformInit(int start, int recordsPerPage) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return platformDao.paginationPlatformInit(begin, recordsPerPage);
		
	}

	public List<Platform> paginationPlatformFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return platformDao.paginationPlatformFilterNoOrder(begin, params, recordsPerPage);
		
	}

	public List<Platform> paginationPlatformOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return platformDao.paginationPlatformOrderNoFilter(begin, order, ascDesc, recordsPerPage);
		
	}

	public List<Platform> paginationPlatformFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return platformDao.paginationPlatformFilterAndOrder(begin, params, order, ascDesc, recordsPerPage);
		
	}	
	
	public List<Platform> paginationPlatformInit2(int start, int recordsPerPage) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return platformDao.paginationPlatformInit2(begin, recordsPerPage);
		
	}

	public List<Platform> paginationPlatformFilterNoOrder2(int start, Map<String, Object> params, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return platformDao.paginationPlatformFilterNoOrder2(begin, params, recordsPerPage);
		
	}

	public List<Platform> paginationPlatformOrderNoFilter2(int start, String order, String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return platformDao.paginationPlatformOrderNoFilter2(begin, order, ascDesc, recordsPerPage);
		
	}

	public List<Platform> paginationPlatformFilterAndOrder2(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return platformDao.paginationPlatformFilterAndOrder2(begin, params, order, ascDesc, recordsPerPage);
		
	}		

	public List<Publisher> findAllPublishers() {
		return publisherDao.findAll();
	}

	public Publisher findPublisherByPk(long key) {
		return publisherDao.findByPk(key);
	}
	
	public Publisher findPublisherByName(String name) {
		return publisherDao.findByName(name);
	}

	public Publisher savePublisher(Publisher p) {
		return publisherDao.save(p);
	}

	public void updatePublisher(Publisher p) {
		publisherDao.update(p);
	}

	public void deletePublisher(Publisher p) {
		publisherDao.delete(p);
	}

	public long totalPublishers() {
		return publisherDao.totalPublishers();
	}
	
	public long totalPublishersFilter(Map<String, Object> params) {
		return publisherDao.totalPublishersFilter(params);
	}

	public List<Publisher> paginationPublisherInit(int start, int recordsPerPage) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return publisherDao.paginationPublisherInit(begin, recordsPerPage);
		
	}

	public List<Publisher> paginationPublisherFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return publisherDao.paginationPublisherFilterNoOrder(begin, params, recordsPerPage);
		
	}

	public List<Publisher> paginationPublisherOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return publisherDao.paginationPublisherOrderNoFilter(begin, order, ascDesc, recordsPerPage);
		
	}

	public List<Publisher> paginationPublisherFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return publisherDao.paginationPublisherFilterAndOrder(begin, params, order, ascDesc, recordsPerPage);
		
	}
	
	public List<Publisher> paginationPublisherInit2(int start, int recordsPerPage) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return publisherDao.paginationPublisherInit2(begin, recordsPerPage);
		
	}

	public List<Publisher> paginationPublisherFilterNoOrder2(int start, Map<String, Object> params, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return publisherDao.paginationPublisherFilterNoOrder2(begin, params, recordsPerPage);
		
	}

	public List<Publisher> paginationPublisherOrderNoFilter2(int start, String order, String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return publisherDao.paginationPublisherOrderNoFilter2(begin, order, ascDesc, recordsPerPage);
		
	}

	public List<Publisher> paginationPublisherFilterAndOrder2(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return publisherDao.paginationPublisherFilterAndOrder2(begin, params, order, ascDesc, recordsPerPage);
		
	}	

	public List<Region> findAllRegions() {
		return regionDao.findAll();
	}

	public Region findRegionByPk(long key) {
		return regionDao.findByPk(key);
	}
	
	public Region findRegionByName(String name) {
		return regionDao.findByName(name);
	}

	public Region saveRegion(Region r) {
		return regionDao.save(r);
	}

	public void updateRegion(Region r) {
		regionDao.update(r);
	}

	public void deleteRegion(Region r) {
		regionDao.delete(r);
	}

	public long totalRegions() {
		return regionDao.totalRegions();
	}
	
	public long totalRegionsFilter(Map<String, Object> params) {
		return regionDao.totalRegionsFilter(params);
	}

	public List<Region> paginationRegionInit(int start, int recordsPerPage) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return regionDao.paginationRegionInit(begin, recordsPerPage);
		
	}

	public List<Region> paginationRegionFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return regionDao.paginationRegionFilterNoOrder(begin, params, recordsPerPage);
		
	}

	public List<Region> paginationRegionOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return regionDao.paginationRegionOrderNoFilter(begin, order, ascDesc, recordsPerPage);
		
	}

	public List<Region> paginationRegionFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return regionDao.paginationRegionFilterAndOrder(begin, params, order, ascDesc, recordsPerPage);
		
	}	

	public List<UserProfile> findAllUserProfiles() {
		return userProfileDao.findAll();
	}

	public UserProfile findUserProfileByType(String type) {
		return userProfileDao.findByType(type);
	}

	public UserProfile findUserProfileById(long id) {
		return userProfileDao.findById(id);
	}

	public UserProfile saveUserProfile(UserProfile p) {
		return userProfileDao.save(p);
	}

	public void updateUserProfile(UserProfile p) {
		userProfileDao.update(p);
	}

	public void deleteUserProfile(UserProfile p) {
		userProfileDao.delete(p);
	}

	public List<RegionSales> findAllRegionSales() {
		return regionSalesDao.findAll();
	}
	
	public List<RegionSales> findAllRegionSalesByRegion(Region region) {
		return regionSalesDao.findAllByRegion(region);
	}

	public RegionSales findRegionSalesByPk(long key) {
		return regionSalesDao.findByPk(key);
	}
	
	public RegionSales findRegionSalesByRegionAndGamePlatform(Region region, GamePlatform gameplatform) {
		return regionSalesDao.findByRegionAndGamePlatform(region, gameplatform);
	}

	public RegionSales saveRegionSales(RegionSales rs) {
		return regionSalesDao.save(rs);
	}

	public void updateRegionSales(RegionSales rs) {
		regionSalesDao.update(rs);
	}

	public void deleteRegionSales(RegionSales rs) {
		regionSalesDao.delete(rs);
	}

	public List<GamePlatform> findAllGamePlatforms() {
		return gamePlatformDao.findAll();
	}
	
	public List<GamePlatform> findAllGamePlatformsByPlatform(Platform platform) {
		return gamePlatformDao.findAllByPlatform(platform);
	}
	
	public List<Integer> findAllReleaseYears() {
		return gamePlatformDao.findAllReleaseYears();
	}

	public GamePlatform findGamePlatformByPk(long key) {
		return gamePlatformDao.findByPk(key);
	}
	
	public GamePlatform findGamePlatformByGamePublisherAndPlatform(GamePublisher gamepublisher, Platform platform) {
		return gamePlatformDao.findByGamePublisherAndPlatform(gamepublisher, platform);
	}
	
	public long totalGamePlatforms() {
		return gamePlatformDao.totalGamePlatforms();
	}
	
	public long totalGamePlatformsFilter(Map<String, Object> params, boolean hideInactives) {
		return gamePlatformDao.totalGamePlatformsFilter(params, hideInactives);
	}

	public List<GamePlatform> paginationGamePlatformInit(int start, int recordsPerPage, boolean hideInactives) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return gamePlatformDao.paginationGamePlatformInit(begin, recordsPerPage, hideInactives);
		
	}

	public List<GamePlatform> paginationGamePlatformFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage, boolean hideInactives) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return gamePlatformDao.paginationGamePlatformFilterNoOrder(begin, params, recordsPerPage, hideInactives);
		
	}

	public List<GamePlatform> paginationGamePlatformOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage, boolean hideInactives) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return gamePlatformDao.paginationGamePlatformOrderNoFilter(begin, order, ascDesc, recordsPerPage, hideInactives);
		
	}

	public List<GamePlatform> paginationGamePlatformFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage, boolean hideInactives) {
		
		int begin = start;

		if (begin < 0) {
			begin = 0;
		}
		
		return gamePlatformDao.paginationGamePlatformFilterAndOrder(begin, params, order, ascDesc, recordsPerPage, hideInactives);		
		
	}

	public GamePlatform saveGamePlatform(GamePlatform gpl) {
		return gamePlatformDao.save(gpl);
	}

	public void updateGamePlatform(GamePlatform gpl) {
		gamePlatformDao.update(gpl);
	}

	public void deleteGamePlatform(GamePlatform gpl) {
		gamePlatformDao.delete(gpl);
	}

	public List<GamePublisher> findAllGamePublishers() {
		return gamePublisherDao.findAll();
	}
	
	public List<GamePublisher> findAllGamePublishersByPublisher(Publisher publisher){
		return gamePublisherDao.findAllByPublisher(publisher);
	}

	public GamePublisher findGamePublisherByPk(long key) {
		return gamePublisherDao.findByPk(key);
	}
	
	public GamePublisher findGamePublisherByGameAndPublisher(Game game, Publisher publisher) {
		return gamePublisherDao.findByGameAndPublisher(game,publisher);
	}

	public GamePublisher saveGamePublisher(GamePublisher gpu) {
		return gamePublisherDao.save(gpu);
	}

	public void updateGamePublisher(GamePublisher gpu) {
		gamePublisherDao.update(gpu);
	}

	public void deleteGamePublisher(GamePublisher gpu) {
		gamePublisherDao.delete(gpu);
	}

}
