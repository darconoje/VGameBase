package com.vgamebase.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@WebListener
public class SessionListener implements HttpSessionListener {

	public static int onlineusers = 0;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		onlineusers++;	
		System.out.println(onlineusers);
		
		UserDetails principalUser = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			principalUser = ((UserDetails) principal);
		} 
		
		if(principalUser != null && principalUser.getAuthorities().contains(new SimpleGrantedAuthority("Admin"))){
			se.getSession().setMaxInactiveInterval(400 * 60); // in seconds
		}else {
			se.getSession().setMaxInactiveInterval(10 * 60); // in seconds
		}
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if(onlineusers>0) {
			onlineusers--;	
		}
		System.out.println(onlineusers);
	}

}
