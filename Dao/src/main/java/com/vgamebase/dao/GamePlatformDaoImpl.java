package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.GamePublisher;
import com.vgamebase.model.Genre;
import com.vgamebase.model.Platform;
import com.vgamebase.model.Publisher;

@Repository("gamePlatformDao")
@Transactional
public class GamePlatformDaoImpl extends AbstractDao<Integer, GamePlatform> implements GamePlatformDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GamePlatform> findAll() {
		try {
			List<GamePlatform> platforms = getEntityManager()
					.createQuery("SELECT gpl FROM GamePlatform gpl ORDER BY id ").getResultList();
			return platforms;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GamePlatform> findAllByPlatform(Platform platform) {
		try {
			List<GamePlatform> gameplatforms = getEntityManager()
					.createQuery("SELECT gp FROM GamePlatform gp WHERE gp.platform = :platform ORDER BY id")
					.setParameter("platform", platform).getResultList();
			return gameplatforms;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> findAllReleaseYears() {
		try {
			List<Integer> releaseYears = getEntityManager()
					.createQuery("SELECT DISTINCT gpl.releaseYear FROM GamePlatform gpl ORDER BY releaseYear ").getResultList();
			return releaseYears;
		} catch (NoResultException ex) {
			return null;
		}	
	}

	public GamePlatform findByPk(long key) {
		return getByKey(key);
	}

	public GamePlatform findByGamePublisherAndPlatform(GamePublisher gamepublisher, Platform platform) {

		try {
			GamePlatform gameplatform = (GamePlatform) getEntityManager().createQuery(
					"SELECT gp FROM GamePlatform gp WHERE gp.gamepublisher.id = :gamepublisher AND gp.platform.id = :platform")
					.setParameter("gamepublisher", gamepublisher.getId()).setParameter("platform", platform.getId())
					.getSingleResult();
			return gameplatform;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public GamePlatform save(GamePlatform gpl) {
		if (findByGamePublisherAndPlatform(gpl.getGamepublisher(), gpl.getPlatform()) == null) {
			getEntityManager().persist(gpl);
			return gpl;
		} else {
			return null;
		}

	}

	public long totalGamePlatforms() {
		long totalGamePlatforms = (long) getEntityManager().createQuery("SELECT COUNT(gp.id) FROM GamePlatform gp")
				.getSingleResult();
		return totalGamePlatforms;
	}

	public long totalGamePlatformsFilter(Map<String, Object> params, boolean hideInactives) {

		long gamePlatformsCount = 0;

		if (params == null) {
			return totalGamePlatforms();
		}

		StringBuffer sb = new StringBuffer(
				"SELECT COUNT(DISTINCT gp.id) FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Game ga, Platform pl, Publisher pu, Genre gr "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region " + "AND gpub = gp.gamepublisher "
						+ "AND ga = gpub.game " + "AND pl = gp.platform " + "AND pu = gpub.publisher "
						+ "AND gr = ga.genre");

		if (params != null && params.size() != 0) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (entry.getKey().equals("ga.name") || entry.getKey().equals("gp.releaseYear")) {
					sb.append(" AND ");
					sb.append(entry.getKey());
					sb.append(" LIKE ");
					sb.append(" :");
					sb.append(entry.getKey().substring(3));					
				}else {
					sb.append(" AND ");
					sb.append(entry.getKey());
					sb.append(" = ");
					sb.append(" :");
					sb.append(entry.getKey());					
				}
			}
		}
		
		if(hideInactives == true) {
			sb.append(" AND gp.active = 1 ");			
		}

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() instanceof String) {
				q.setParameter(entry.getKey().substring(3), "%" + (String) entry.getValue() + "%");			
			} else if(entry.getValue() instanceof Integer) {
				q.setParameter(entry.getKey().substring(3), (Integer) entry.getValue());	
			} else if(entry.getValue() instanceof Genre) {
				q.setParameter(entry.getKey(), ((Genre) entry.getValue()));
			} else if(entry.getValue() instanceof Platform) {
				q.setParameter(entry.getKey(), ((Platform) entry.getValue()));
			} else if(entry.getValue() instanceof Publisher) {
				q.setParameter(entry.getKey(), ((Publisher) entry.getValue()));
			}
		}

		gamePlatformsCount = (long) q.getSingleResult();

		return gamePlatformsCount;

	}

	@SuppressWarnings("unchecked")
	public List<GamePlatform> paginationGamePlatformInit(int start, int recordsPerPage, boolean hideInactives) {

		StringBuffer sb = new StringBuffer(
				"SELECT gp.id, ga.name, gr.name, pl.name , pu.name, gp.releaseYear , ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Game ga, Platform pl, Publisher pu, Genre gr "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region " + "AND gpub = gp.gamepublisher "
						+ "AND ga = gpub.game " + "AND pl = gp.platform " + "AND pu = gpub.publisher "
						+ "AND gr = ga.genre ");
		
		if(hideInactives == true) {
			sb.append(" AND gp.active = 1 ");
		}
		
		sb.append(" GROUP BY rs.gamePlatform ");

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<GamePlatform> gamePlatforms = q.getResultList();
		return gamePlatforms;

	}

	@SuppressWarnings("unchecked")
	public List<GamePlatform> paginationGamePlatformFilterNoOrder(int start, Map<String, Object> params,
			int recordsPerPage, boolean hideInactives) {

		StringBuffer sb = new StringBuffer(
				"SELECT gp.id, ga.name, gr.name, pl.name , pu.name, gp.releaseYear , ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Game ga, Platform pl, Publisher pu, Genre gr "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region " + "AND gpub = gp.gamepublisher "
						+ "AND ga = gpub.game " + "AND pl = gp.platform " + "AND pu = gpub.publisher "
						+ "AND gr = ga.genre ");

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getKey().equals("ga.name") || entry.getKey().equals("gp.releaseYear")) {
				sb.append(" AND ");
				sb.append(entry.getKey());
				sb.append(" LIKE ");
				sb.append(" :");
				sb.append(entry.getKey().substring(3));					
			}else {
				sb.append(" AND ");
				sb.append(entry.getKey());
				sb.append(" = ");
				sb.append(" :");
				sb.append(entry.getKey());					
			}
		}
		
		if(hideInactives == true) {
			sb.append(" AND gp.active = 1 ");			
		}

		sb.append(" GROUP BY rs.gamePlatform ");

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() instanceof String) {
				q.setParameter(entry.getKey().substring(3), "%" + (String) entry.getValue() + "%");			
			} else if(entry.getValue() instanceof Integer) {
				q.setParameter(entry.getKey().substring(3), (Integer) entry.getValue());	
			} else if(entry.getValue() instanceof Genre) {
				q.setParameter(entry.getKey(), ((Genre) entry.getValue()));
			} else if(entry.getValue() instanceof Platform) {
				q.setParameter(entry.getKey(), ((Platform) entry.getValue()));
			} else if(entry.getValue() instanceof Publisher) {
				q.setParameter(entry.getKey(), ((Publisher) entry.getValue()));
			}
		}

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<GamePlatform> gamePlatforms = q.getResultList();
		return gamePlatforms;

	}

	@SuppressWarnings("unchecked")
	public List<GamePlatform> paginationGamePlatformOrderNoFilter(int start, String order, String ascDesc,
			int recordsPerPage, boolean hideInactives) {

		StringBuffer sb = new StringBuffer(
				"SELECT gp.id, ga.name, gr.name, pl.name , pu.name, gp.releaseYear , ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Game ga, Platform pl, Publisher pu, Genre gr "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region " + "AND gpub = gp.gamepublisher "
						+ "AND ga = gpub.game " + "AND pl = gp.platform " + "AND pu = gpub.publisher "
						+ "AND gr = ga.genre ");
		
		if(hideInactives == true) {
			sb.append(" AND gp.active = 1 ");
		}
		
		sb.append(" GROUP BY rs.gamePlatform ");

		sb.append(" ORDER BY " + order + " " + ascDesc);

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<GamePlatform> gamePlatforms = q.getResultList();
		return gamePlatforms;

	}

	@SuppressWarnings("unchecked")
	public List<GamePlatform> paginationGamePlatformFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage, boolean hideInactives) {

		StringBuffer sb = new StringBuffer(
				"SELECT gp.id, ga.name, gr.name, pl.name , pu.name, gp.releaseYear , ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Game ga, Platform pl, Publisher pu, Genre gr "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region " + "AND gpub = gp.gamepublisher "
						+ "AND ga = gpub.game " + "AND pl = gp.platform " + "AND pu = gpub.publisher "
						+ "AND gr = ga.genre ");

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getKey().equals("ga.name") || entry.getKey().equals("gp.releaseYear")) {
				sb.append(" AND ");
				sb.append(entry.getKey());
				sb.append(" LIKE ");
				sb.append(" :");
				sb.append(entry.getKey().substring(3));					
			}else {
				sb.append(" AND ");
				sb.append(entry.getKey());
				sb.append(" = ");
				sb.append(" :");
				sb.append(entry.getKey());					
			}
		}
		
		if(hideInactives == true) {
			sb.append(" AND gp.active = 1 ");			
		}

		sb.append(" GROUP BY rs.gamePlatform");

		if (order != null && !order.equals("")) {
			sb.append(" ORDER BY " + order + " " + ascDesc);
		}

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() instanceof String) {
				q.setParameter(entry.getKey().substring(3), "%" + (String) entry.getValue() + "%");			
			} else if(entry.getValue() instanceof Integer) {
				q.setParameter(entry.getKey().substring(3), (Integer) entry.getValue());	
			} else if(entry.getValue() instanceof Genre) {
				q.setParameter(entry.getKey(), ((Genre) entry.getValue()));
			} else if(entry.getValue() instanceof Platform) {
				q.setParameter(entry.getKey(), ((Platform) entry.getValue()));
			} else if(entry.getValue() instanceof Publisher) {
				q.setParameter(entry.getKey(), ((Publisher) entry.getValue()));
			}
		}

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<GamePlatform> gamePlatforms = q.getResultList();
		return gamePlatforms;

	}

	public void update(GamePlatform gpl) {
		super.update(gpl);
	}

	public void delete(GamePlatform gpl) {
		super.delete(gpl);
	}

}
