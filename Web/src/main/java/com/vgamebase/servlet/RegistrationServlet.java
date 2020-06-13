package com.vgamebase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vgamebase.model.User;
import com.vgamebase.servlet.validator.UserValidator;

@WebServlet(name = "RegistrationServlet", 
urlPatterns = { "/registration" }) 
public class RegistrationServlet extends AbstractServlet {
	
	private static final long serialVersionUID = -1720688734823865429L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("./registration.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String answer = request.getParameter("answer");
		HttpSession session = request.getSession();
		String captcha = (String) session.getAttribute("dns_security_code");

		UserValidator userValidator = new UserValidator();
		
		boolean valid = userValidator.validateUser(request);
		
		if(userService.findByUsername(username)!=null) {
			valid = false;
			session.setAttribute("takenUsername", "true");
		}
		
		if(userService.findByEmail(email)!=null) {
			valid = false;
			session.setAttribute("takenEmail", "true");
		}
		
		if (valid == true) {
			
			User newUser = new User();
			
			newUser.setUserName(username);
			newUser.setEmail(email);
			newUser.setUserProfile(maestrosService.findUserProfileByType("User"));
			newUser.setActive(true);
			PasswordEncoder encoder = new BCryptPasswordEncoder(12);
			String encryptedPassword = encoder.encode(password) ;
			newUser.setPassword(encryptedPassword);
			
			userService.save(newUser);
			
			response.sendRedirect("./login");
			
			session.setAttribute("successfulRegistration", "true");
			
		}else {
			
			if(!answer.equals(captcha)) {
				session.setAttribute("captcha","wrong");
			}
			
			response.sendRedirect("./registration");
			
		}
		

		
	}
	
}
