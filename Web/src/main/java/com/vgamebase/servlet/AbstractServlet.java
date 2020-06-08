package com.vgamebase.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vgamebase.services.GameService;
import com.vgamebase.services.MaestrosService;
import com.vgamebase.services.impl.UserServiceImpl;
import com.vgamebase.servlet.controller.AdminController;
import com.vgamebase.servlet.controller.GameController;

public class AbstractServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2145263435858352687L;
	public MaestrosService maestrosService;
	public GameService gameService;
	public UserServiceImpl userService;
	public AdminController adminController;
	public GameController gameController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		maestrosService = (MaestrosService)webApplicationContext.getBean("maestrosService");
		gameService = (GameService)webApplicationContext.getBean("gameService");
		userService = (UserServiceImpl)webApplicationContext.getBean("userService");
		
		adminController = (AdminController)webApplicationContext.getBean("adminController");
		gameController = (GameController)webApplicationContext.getBean("gameController");
	}

}
