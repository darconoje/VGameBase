package com.vgamebase.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

	public static int onlineusers = 0;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		onlineusers++;	
		System.out.println(onlineusers);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if(onlineusers>0) {
			onlineusers--;	
		}
		System.out.println(onlineusers);
	}

}
