package com.vgamebase.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.vgamebase.dao.UserDao;
import com.vgamebase.model.UserProfile;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.vgamebase.model.User u = userDao.findByEmail(username);

		List<GrantedAuthority> authorities = buildUserAuthority(u.getUserProfile());

		return buildUserForAuthentication(u, authorities);
		
	}
	
	private User buildUserForAuthentication(com.vgamebase.model.User user, List<GrantedAuthority> authorities) {
		return new User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(UserProfile userRole) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		setAuths.add(new SimpleGrantedAuthority(userRole.getType()));


		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}	

	public com.vgamebase.model.User findById(long id) {
		return userDao.findById(id);
	}

	public com.vgamebase.model.User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public com.vgamebase.model.User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public void save(com.vgamebase.model.User user) {
		userDao.save(user);
	}

	public void deleteByEmail(String email) {
		userDao.deleteByEmail(email);
	}

	public List<com.vgamebase.model.User> findAllUsers() {
		return userDao.findAllUsers();
	}

	public void update(com.vgamebase.model.User g) {
		userDao.update(g);
	}

	public void delete(com.vgamebase.model.User g) {
		userDao.delete(g);
	}

	public long totalUsers() {
		return userDao.totalUsers();
	}

	public long totalUsersFilter(Map<String, Object> params, boolean hideInactives) {
		return userDao.totalUsersFilter(params, hideInactives);
	}

	public List<com.vgamebase.model.User> paginationUserInit(int start, int recordsPerPage, boolean hideInactives) {
		return userDao.paginationUserInit(start, recordsPerPage, hideInactives);
	}

	public List<com.vgamebase.model.User> paginationUserFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage, boolean hideInactives) {
		return userDao.paginationUserFilterNoOrder(start, params, recordsPerPage, hideInactives);
	}

	public List<com.vgamebase.model.User> paginationUserOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage, boolean hideInactives) {
		return userDao.paginationUserOrderNoFilter(start, order, ascDesc, recordsPerPage, hideInactives);
	}

	public List<com.vgamebase.model.User> paginationUserFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage, boolean hideInactives) {
		return userDao.paginationUserFilterAndOrder(start, params, order, ascDesc, recordsPerPage, hideInactives);
	}

}
