package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.Region;

@Repository("regionDao")
@Transactional
public class RegionDaoImpl extends AbstractDao<Integer, Region> implements RegionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Region> findAll() {
		try {
			List<Region> regions = getEntityManager().createQuery("SELECT r FROM Region r ORDER BY name ")
					.getResultList();
			return regions;
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Region findByPk(long key) {
		return getByKey(key);
	}

	public Region findByName(String name) {
		try {
			Region r = (Region) getEntityManager().createQuery("SELECT r FROM Region r where r.name = :name")
					.setParameter("name", name).getSingleResult();
			return r;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Region save(Region r) {
		if (findByName(r.getName()) == null) {
			getEntityManager().persist(r);
			return r;
		} else {
			return null;
		}

	}

	public void update(Region r) {
		super.update(r);
	}

	public void delete(Region r) {
		entityManager.clear();
		super.delete(r);
	}

	public long totalRegions() {
		long totalRegions = (long) getEntityManager().createQuery("SELECT COUNT(r.id) FROM Region r").getSingleResult();
		return totalRegions;
	}

	public long totalRegionsFilter(Map<String, Object> params) {

		long regionsCount = 0;

		if (params == null) {
			return totalRegions();
		}

		StringBuffer sb = new StringBuffer("SELECT COUNT(r.id) FROM Region r");

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

		regionsCount = (long) q.getSingleResult();

		return regionsCount;

	}

	@SuppressWarnings("unchecked")
	public List<Region> paginationRegionInit(int start, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT r FROM Region r ORDER BY r.id ASC");

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Region> regions = q.getResultList();

		return regions;

	}

	@SuppressWarnings("unchecked")
	public List<Region> paginationRegionFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT r FROM Region r");
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

		List<Region> regions = q.getResultList();

		return regions;

	}

	@SuppressWarnings("unchecked")
	public List<Region> paginationRegionOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT r FROM Region r");

		sb.append(" ORDER BY " + order + " " + ascDesc);

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Region> regions = q.getResultList();

		return regions;

	}

	@SuppressWarnings("unchecked")
	public List<Region> paginationRegionFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT r FROM Region r");

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

		List<Region> regions = q.getResultList();

		return regions;

	}

}
