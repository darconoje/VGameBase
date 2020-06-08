package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.Platform;

@Repository("platformDao")
@Transactional
public class PlatformDaoImpl extends AbstractDao<Integer, Platform> implements PlatformDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Platform> findAll() {
		try {
			List<Platform> platforms = getEntityManager().createQuery("SELECT p FROM Platform p ORDER BY name ")
					.getResultList();
			return platforms;
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Platform findByPk(long key) {
		return getByKey(key);
	}

	public Platform findByName(String name) {
		try {
			Platform p = (Platform) getEntityManager().createQuery("SELECT p FROM Platform p where p.name = :name")
					.setParameter("name", name).getSingleResult();
			return p;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Platform save(Platform p) {
		if (findByName(p.getName()) == null) {
			getEntityManager().persist(p);
			return p;
		} else {
			return null;
		}

	}

	public void update(Platform p) {
		super.update(p);
	}

	public void delete(Platform p) {
		super.delete(p);
	}

	public long totalPlatforms() {
		long totalPlatforms = (long) getEntityManager().createQuery("SELECT COUNT(pl.id) FROM Platform pl")
				.getSingleResult();
		return totalPlatforms;
	}

	public long totalPlatformsFilter(Map<String, Object> params) {

		long platformsCount = 0;

		if (params == null) {
			return totalPlatforms();
		}

		StringBuffer sb = new StringBuffer("SELECT COUNT(p.id) FROM Platform p");

		if (params != null && params.size() != 0) {
			sb.append(" WHERE ");
			int counter = 0;
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (counter != 0) {
					sb.append(" OR ");
				}
				sb.append(entry.getKey());
				sb.append(" LIKE ");
				sb.append(" :");
				sb.append(entry.getKey().substring(2));
				counter++;
			}
		}

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey().substring(2), "%" + (String) entry.getValue() + "%");
		}

		platformsCount = (long) q.getSingleResult();

		return platformsCount;

	}

	@SuppressWarnings("unchecked")
	public List<Platform> paginationPlatformInit(int start, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT p FROM Platform p ORDER BY p.id ASC");

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Platform> platforms = q.getResultList();

		return platforms;

	}

	@SuppressWarnings("unchecked")
	public List<Platform> paginationPlatformFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT p FROM Platform p");
		sb.append(" WHERE ");

		int counter = 0;
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (counter != 0) {
				sb.append(" OR ");
			}
			sb.append(entry.getKey());
			sb.append(" LIKE ");
			sb.append(" :");
			sb.append(entry.getKey().substring(2));
			counter++;
		}

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey().substring(2), "%" + (String) entry.getValue() + "%");
		}

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Platform> platforms = q.getResultList();

		return platforms;

	}

	@SuppressWarnings("unchecked")
	public List<Platform> paginationPlatformOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT p FROM Platform p");

		sb.append(" ORDER BY " + order + " " + ascDesc);

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Platform> platforms = q.getResultList();

		return platforms;

	}

	@SuppressWarnings("unchecked")
	public List<Platform> paginationPlatformFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT p FROM Platform p");

		sb.append(" WHERE ");

		int counter = 0;
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (counter != 0) {
				sb.append(" OR ");
			}
			sb.append(entry.getKey());
			sb.append(" LIKE ");
			sb.append(" :");
			sb.append(entry.getKey().substring(2));
			counter++;
		}

		if (order != null && !order.equals("")) {
			sb.append(" ORDER BY " + order + " " + ascDesc);
		}

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey().substring(2), "%" + (String) entry.getValue() + "%");
		}

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Platform> platforms = q.getResultList();

		return platforms;

	}

	@SuppressWarnings("unchecked")
	public List<Platform> paginationPlatformInit2(int start, int recordsPerPage) {

		StringBuffer sb = new StringBuffer(
				"SELECT p.id, p.name, COUNT(DISTINCT rs.gamePlatform ) AS total_games, ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Game g, Platform p "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region " + "AND gpub = gp.gamepublisher "
					    + "AND g = gpub.game " + "AND p = gp.platform " + "GROUP BY p.id");

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Platform> platforms = q.getResultList();

		return platforms;

	}

	@SuppressWarnings("unchecked")
	public List<Platform> paginationPlatformFilterNoOrder2(int start, Map<String, Object> params, int recordsPerPage) {

		StringBuffer sb = new StringBuffer(
				"SELECT p.id, p.name, COUNT(DISTINCT rs.gamePlatform ) AS total_games, ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Game g, Platform p "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region " + "AND gpub = gp.gamepublisher "
					    + "AND g = gpub.game " + "AND p = gp.platform ");

		for (Map.Entry<String, Object> entry : params.entrySet()) {

			sb.append(" AND ");

			sb.append(entry.getKey());
			sb.append(" LIKE ");
			sb.append(" :");
			sb.append(entry.getKey().substring(2));
		}
		
		sb.append(" GROUP BY p.id");

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey().substring(2), "%" + (String) entry.getValue() + "%");
		}

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Platform> platforms = q.getResultList();

		return platforms;

	}

	@SuppressWarnings("unchecked")
	public List<Platform> paginationPlatformOrderNoFilter2(int start, String order, String ascDesc,
			int recordsPerPage) {

		StringBuffer sb = new StringBuffer(
				"SELECT p.id, p.name, COUNT(DISTINCT rs.gamePlatform ) AS total_games, ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Game g, Platform p "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region " + "AND gpub = gp.gamepublisher "
					    + "AND g = gpub.game " + "AND p = gp.platform " + "GROUP BY p.id");

		sb.append(" ORDER BY " + order + " " + ascDesc);

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Platform> platforms = q.getResultList();

		return platforms;

	}

	@SuppressWarnings("unchecked")
	public List<Platform> paginationPlatformFilterAndOrder2(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		StringBuffer sb = new StringBuffer(
				"SELECT p.id, p.name, COUNT(DISTINCT rs.gamePlatform ) AS total_games, ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Game g, Platform p "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region " + "AND gpub = gp.gamepublisher "
					    + "AND g = gpub.game " + "AND p = gp.platform ");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			sb.append(" AND ");

			sb.append(entry.getKey());
			sb.append(" LIKE ");
			sb.append(" :");
			sb.append(entry.getKey().substring(2));
		}
		
		sb.append(" GROUP BY p.id ");

		if (order != null && !order.equals("")) {
			sb.append(" ORDER BY " + order + " " + ascDesc);
		}

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey().substring(2), "%" + (String) entry.getValue() + "%");
		}

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Platform> platforms = q.getResultList();

		return platforms;

	}

}
