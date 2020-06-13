package com.vgamebase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@WebServlet(name = "LoginServlet", 
urlPatterns = { "/login" }) 
public class LoginServlet extends AbstractServlet {
	
	private static final long serialVersionUID = -1720688734823865429L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String error = request.getParameter("error");
		
		HttpSession session = request.getSession();
		
		if(error != null && error.equals("")) {
			session.setAttribute("loginError", "true");
		}
		
		request.getRequestDispatcher("./login.jsp").forward(request, response);
	}

}
