package com.vgamebase.servlet.validator;

import javax.servlet.http.HttpServletRequest;

public class GameValidator {

	public boolean validateGame(HttpServletRequest request) {

		boolean valid = false;

		String name = request.getParameter("name");
		String genre = request.getParameter("genre");
		String publisher = request.getParameter("publisher");
		String platform = request.getParameter("platform");
		String[] regionSales = request.getParameterValues("regionSales");

		if (name != null && genre != null && publisher != null && platform != null && !name.equals("")
				&& !genre.equals("") && !publisher.equals("") && !platform.equals("") && regionSales != null
				&& regionSales.length > 0) {

			valid = true;

		}

		return valid;

	}

}
