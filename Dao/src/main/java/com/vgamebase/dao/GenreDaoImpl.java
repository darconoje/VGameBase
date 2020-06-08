package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.Genre;

@Repository("genreDao")
@Transactional
public class GenreDaoImpl extends AbstractDao<Integer, Genre> implements GenreDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> findAll() {
		try {
			List<Genre> genres = getEntityManager().createQuery("SELECT g FROM Genre g ORDER BY name ").getResultList();
			return genres;
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Genre findByPk(long key) {
		return getByKey(key);
	}

	public Genre findByName(String name) {
		try {
			Genre g = (Genre) getEntityManager().createQuery("SELECT g FROM Genre g where g.name = :name")
					.setParameter("name", name).getSingleResult();
			return g;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Genre save(Genre g) {

		if (findByName(g.getName()) == null) {
			getEntityManager().persist(g);
			return g;
		} else {
			return null;
		}

	}

	public void update(Genre g) {
		super.update(g);
	}

	public void delete(Genre g) {
		entityManager.clear();
		super.delete(g);
	}

	public long totalGenres() {
		long totalGenres = (long) getEntityManager().createQuery("SELECT COUNT(g.id) FROM Genre g").getSingleResult();
		return totalGenres;
	}

	public long totalGenresFilter(Map<String, Object> params) {

		long genresCount = 0;

		if (params == null) {
			return totalGenres();
		}

		StringBuffer sb = new StringBuffer("SELECT COUNT(g.id) FROM Genre g");

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

		genresCount = (long) q.getSingleResult();

		return genresCount;

	}

	@SuppressWarnings("unchecked")
	public List<Genre> paginationGenreInit(int start, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT g FROM Genre g ORDER BY g.id ASC");

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Genre> genres = q.getResultList();

		return genres;

	}

	@SuppressWarnings("unchecked")
	public List<Genre> paginationGenreFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT g FROM Genre g");
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

		List<Genre> genres = q.getResultList();
		return genres;

	}

	@SuppressWarnings("unchecked")
	public List<Genre> paginationGenreOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage) {

		StringBuffer sb = new StringBuffer("SELECT g FROM Genre g");

		sb.append(" ORDER BY " + order + " " + ascDesc);

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<Genre> genres = q.getResultList();
		return genres;

	}

	@SuppressWarnings("unchecked")
	public List<Genre> paginationGenreFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage) {
		
		StringBuffer sb = new StringBuffer("SELECT g FROM Genre g");

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
		
		List<Genre> genres = q.getResultList();
		return genres;
	}

}
