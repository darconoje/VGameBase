package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.Publisher;

@Repository("publisherDao")
@Transactional
public class PublisherDaoImpl extends AbstractDao<Integer, Publisher> implements PublisherDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Publisher> findAll() {
		try {
			List<Publisher> publishers = getEntityManager().createQuery("SELECT p FROM Publisher p ORDER BY name ")
					.getResultList();
			return publishers;
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Publisher findByPk(long key) {
		return getByKey(key);
	}

	public Publisher findByName(String name) {
		try {
			Publisher p = (Publisher) getEntityManager().createQuery("SELECT p FROM Publisher p where p.name = :name")
					.setParameter("name", name).getSingleResult();
			return p;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Publisher save(Publisher p) {
		if (findByName(p.getName()) == null) {
			getEntityManager().persist(p);
			return p;
		} else {
			return null;
		}

	}

	public void update(Publisher p) {
		super.update(p);
	}

	public void delete(Publisher p) {
		entityManager.clear();
		super.delete(p);
	}

	public long totalPublishers() {
		long totalPublishers = (long) getEntityManager().createQuery("SELECT COUNT(pu.id) FROM Publisher pu")
				.getSingleResult();
		return totalPublishers;
	}

	public long totalPublishersFilter(Map<String, Object> params) {

		long publishersCount = 0;

		if (params == null) {
			return totalPublishers();
		}

		StringBuffer sb = new StringBuffer("SELECT COUNT(p.id) FROM Publisher p");

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

		publishersCount = (long) q.getSingleResult();

		return publishersCount;

	}

	@SuppressWarnings("unchecked")
	public List<Publisher> paginationPublisherInit(int start, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT p FROM Publisher p ORDER BY p.id ASC");

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Publisher> publishers = q.getResultList();

		return publishers;

	}

	@SuppressWarnings("unchecked")
	public List<Publisher> paginationPublisherFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT p FROM Publisher p");
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

		List<Publisher> publishers = q.getResultList();
		return publishers;

	}

	@SuppressWarnings("unchecked")
	public List<Publisher> paginationPublisherOrderNoFilter(int start, String order, String ascDesc,
			int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT p FROM Publisher p");

		sb.append(" ORDER BY " + order + " " + ascDesc);

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Publisher> publishers = q.getResultList();
		return publishers;

	}

	@SuppressWarnings("unchecked")
	public List<Publisher> paginationPublisherFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT p FROM Publisher p");

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

		List<Publisher> publishers = q.getResultList();
		return publishers;
	}

	@SuppressWarnings("unchecked")
	public List<Publisher> paginationPublisherInit2(int start, int recordsPerPage) {

		StringBuffer sb = new StringBuffer(
				"SELECT p.id, p.name, COUNT(DISTINCT rs.gamePlatform ) AS total_games, ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Publisher p, Game g, Platform pl "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region "
						+ "AND gpub = gp.gamepublisher " + "AND p = gpub.publisher "
						+ "AND g = gpub.game " + "AND pl = gp.platform " +  "GROUP BY p.id");

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Publisher> publishers = q.getResultList();

		return publishers;

	}

	@SuppressWarnings("unchecked")
	public List<Publisher> paginationPublisherFilterNoOrder2(int start, Map<String, Object> params,
			int recordsPerPage) {

		StringBuffer sb = new StringBuffer(
				"SELECT p.id, p.name, COUNT(DISTINCT rs.gamePlatform ) AS total_games, ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Publisher p, Game g, Platform pl "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region "
						+ "AND gpub = gp.gamepublisher " + "AND p = gpub.publisher "
						+ "AND g = gpub.game " + "AND pl = gp.platform ");

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

		List<Publisher> publishers = q.getResultList();
		return publishers;

	}

	@SuppressWarnings("unchecked")
	public List<Publisher> paginationPublisherOrderNoFilter2(int start, String order, String ascDesc,
			int recordsPerPage) {

		StringBuffer sb = new StringBuffer(
				"SELECT p.id, p.name, COUNT(DISTINCT rs.gamePlatform ) AS total_games, ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Publisher p, Game g, Platform pl "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region "
						+ "AND gpub = gp.gamepublisher " + "AND p = gpub.publisher "
						+ "AND g = gpub.game " + "AND pl = gp.platform " +  "GROUP BY p.id");

		sb.append(" ORDER BY " + order + " " + ascDesc);

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Publisher> publishers = q.getResultList();
		return publishers;

	}

	@SuppressWarnings("unchecked")
	public List<Publisher> paginationPublisherFilterAndOrder2(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		StringBuffer sb = new StringBuffer(
				"SELECT p.id, p.name, COUNT(DISTINCT rs.gamePlatform) AS total_games, ROUND(SUM(rs.sales),2) AS global_sales "
						+ " FROM RegionSales rs, GamePlatform gp, Region r, GamePublisher gpub, Publisher p, Game g, Platform pl "
						+ "WHERE rs.gamePlatform = gp " + "AND r = rs.region "
						+ "AND gpub = gp.gamepublisher " + "AND p = gpub.publisher "
						+ "AND g = gpub.game " + "AND pl = gp.platform ");

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

		List<Publisher> publishers = q.getResultList();
		return publishers;
	}

}
