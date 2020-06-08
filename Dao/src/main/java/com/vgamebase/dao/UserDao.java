package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import com.vgamebase.model.User;

public interface UserDao {

	User findById(long id);

	User findByEmail(String email);

	User findByUsername(String username);

	User save(User user);

	void deleteByEmail(String email);

	List<User> findAllUsers();

	void update(User g);

	void delete(User g);

	long totalUsers();

	long totalUsersFilter(Map<String, Object> params, boolean hideInactives);

	List<User> paginationUserInit(int start, int recordsPerPage, boolean hideInactives);

	List<User> paginationUserFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage, boolean hideInactives);

	List<User> paginationUserOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage, boolean hideInactives);

	List<User> paginationUserFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage, boolean hideInactives);

}
