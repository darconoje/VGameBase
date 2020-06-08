package com.vgamebase.servlet.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserValidator {

	public boolean validateUser(HttpServletRequest request) {

		boolean valid = false;

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");
		String answer = request.getParameter("answer");

		HttpSession session = request.getSession();
		String captcha = (String) session.getAttribute("dns_security_code");

		String view = request.getParameter("view");
		String id = request.getParameter("id");

		if (view != null && view.equals("user")) {

			if (id == null || id.equals("")) {

				if (username != null && email != null && password != null && passwordRepeat != null
						&& !username.equals("") && !email.equals("") && !password.equals("")
						&& !passwordRepeat.equals("") && username.length() <= 15 && password.length() >= 8
						&& passwordRepeat.length() >= 8) {

					valid = true;
				}

			} else {

				if (password == null || password.equals("") && passwordRepeat == null || passwordRepeat.equals("")) {

					if (username != null && email != null && !username.equals("") && !email.equals("")
							&& username.length() <= 15) {

						valid = true;
					}

				} else {

					if (username != null && email != null && password != null && passwordRepeat != null
							&& !username.equals("") && !email.equals("") && !password.equals("")
							&& !passwordRepeat.equals("") && username.length() <= 15 && password.length() >= 8
							&& passwordRepeat.length() >= 8) {

						valid = true;
					}

				}

			}

		} else {

			if (username != null && email != null && password != null && passwordRepeat != null && answer != null
					&& captcha != null && !username.equals("") && !email.equals("") && !password.equals("")
					&& !passwordRepeat.equals("") && !answer.equals("") && !captcha.equals("")
					&& username.length() <= 15 && password.length() >= 8 && passwordRepeat.length() >= 8
					&& answer.length() == 6 && captcha.length() == 6 && captcha.equals(answer)
					&& password.equals(passwordRepeat)) {

				valid = true;

			}

		}

		return valid;

	}

}
