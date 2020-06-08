package com.vgamebase.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(long id) {
		User user = getByKey(id);
		return user;
	}

	public User findByEmail(String email) {
		System.out.println("email : " + email);
		try {
			User user = (User) getEntityManager().createQuery("SELECT u FROM User u WHERE u.email = :email")
					.setParameter("email", email).getSingleResult();
			return user;
		} catch (NoResultException ex) {
			return null;
		}
	}

	public User findByUsername(String username) {
		System.out.println("username : " + username);
		try {
			User user = (User) getEntityManager().createQuery("SELECT u FROM User u WHERE u.userName = :username")
					.setParameter("username", username).getSingleResult();
			return user;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		List<User> users = getEntityManager().createQuery("SELECT u FROM User u ORDER BY u.userName ASC")
				.getResultList();
		return users;
	}

	public User save(User user) {
		if (findByUsername(user.getUserName()) == null) {
			getEntityManager().persist(user);
			return user;
		} else {
			return null;
		}
	}

	public void deleteByEmail(String email) {
		User user = (User) getEntityManager().createQuery("SELECT u FROM User u WHERE u.email = :email")
				.setParameter("email", email).getSingleResult();
		delete(user);
	}

	public void update(User u) {
		super.update(u);
	}

	public void delete(User u) {
		super.delete(u);
	}

	public long totalUsers() {

		long totalUsers = (long) getEntityManager().createQuery("SELECT COUNT(u.id) FROM User u").getSingleResult();
		return totalUsers;

	}

	public long totalUsersFilter(Map<String, Object> params, boolean hideInactives) {

		long usersCount = 0;
		
		StringBuffer sb = new StringBuffer("SELECT COUNT(u.id) FROM User u");

		if (params == null) {
			
			return totalUsers();
			
		}

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
		
		if(hideInactives == true) {
			
			if(params != null && params.size() != 0) {
				sb.append(" AND u.active = 1 ");			
			}else {
				sb.append(" WHERE u.active = 1 ");
			}	

		}

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey().substring(2), "%" + (String) entry.getValue() + "%");
		}

		usersCount = (long) q.getSingleResult();

		return usersCount;

	}

	@SuppressWarnings("unchecked")
	public List<User> paginationUserInit(int start, int recordsPerPage, boolean hideInactives) {

		StringBuffer sb = new StringBuffer("SELECT u FROM User u ORDER BY u.id ASC");
		
		if(hideInactives == true) {
			sb.append(" WHERE u.active = 1 ");
		}

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<User> users = q.getResultList();

		return users;

	}

	@SuppressWarnings("unchecked")
	public List<User> paginationUserFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage, boolean hideInactives) {

		StringBuffer sb = new StringBuffer("SELECT u FROM User u");
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
		
		if(hideInactives == true) {
			sb.append(" AND u.active = 1 ");
		}

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey().substring(2), "%" + (String) entry.getValue() + "%");
		}

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<User> users = q.getResultList();

		return users;

	}

	@SuppressWarnings("unchecked")
	public List<User> paginationUserOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage, boolean hideInactives) {

		StringBuffer sb = new StringBuffer("SELECT u FROM User u");

		if(hideInactives == true) {
			sb.append(" WHERE u.active = 1 ");
		}
		
		sb.append(" ORDER BY " + order + " " + ascDesc);

		System.out.println(sb.toString());

		Query q = getEntityManager().createQuery(sb.toString());

		int pageNumber = start / recordsPerPage;
		q.setFirstResult((pageNumber) * recordsPerPage);
		q.setMaxResults(recordsPerPage);

		List<User> users = q.getResultList();

		return users;

	}

	@SuppressWarnings("unchecked")
	public List<User> paginationUserFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage, boolean hideInactives) {

		StringBuffer sb = new StringBuffer("SELECT u FROM User u");

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
		
		if(hideInactives == true) {
			sb.append(" AND u.active = 1 ");
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

		List<User> users = q.getResultList();

		return users;

	}

	// An alternative to Hibernate.initialize()
	protected void initializeCollection(Collection<?> collection) {
		if (collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}

}
