package com.vgamebase.servlet.datatable;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vgamebase.model.User;
import com.vgamebase.model.datatable.DataTableAdminUser;
import com.vgamebase.servlet.AbstractServlet;
import com.vgamebase.servlet.configuration.HibernateProxyTypeAdapter;

@WebServlet(name = "AdminUsersJSONServlet", urlPatterns = {
"/adminUsersJSON" })
public class AdminUsersJSONServlet extends AbstractServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DataTableAdminUser datatable = new DataTableAdminUser();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int draw = Integer.parseInt(request.getParameter("draw"));
		datatable.setDraw(draw);
		
		int start = Integer.valueOf(request.getParameter("start"));
		int length = Integer.valueOf(request.getParameter("length"));
		
		long countTotal = userService.totalUsers();
		datatable.setRecordsTotal(countTotal);
		
		String search = request.getParameter("search[value]");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (search != null && !search.equals("")) {
			map.put("u.userName", search);
			map.put("u.email", search);
		}
		
		String hideInactives = request.getParameter("columns[1][search][value]");
		
		boolean hide = hideInactives.equals("true") ? true : false;
		
		long count = userService.totalUsersFilter(map,hide);
		datatable.setRecordsFiltered(count);
		
		String orderCol = request.getParameter("order[0][column]");
		String orderDir = request.getParameter("order[0][dir]");
		
		String order = "";

		switch (orderCol == null ? "" : orderCol) {
		case "0":
			order = "u.id";
			break;
		case "1":
			order = "u.userName";
			break;
		case "2":
			order = "u.email";
			break;
		case "3":
			order = "u.userProfile.type";
			break;
		default:
			break;
		}
		

		
		if(orderCol.equals("") && search.equals("")) {
			datatable.setUsers(userService.paginationUserInit(start, length, hide));
		} else if(search.equals("")) {
			datatable.setUsers(userService.paginationUserOrderNoFilter(start, order, orderDir, length, hide));
		} else if(orderCol.equals("")) {
			datatable.setUsers(userService.paginationUserFilterNoOrder(start, map, length, hide));
		} else {
			datatable.setUsers(userService.paginationUserFilterAndOrder(start, map, order, orderDir, length, hide));
		}
		
		for(User user : datatable.getUsers()) {
			user.setVotes(null);
			user.setComments(null);
		}
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
		out.print(gson.toJson(datatable));
		out.flush();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.doPost(request, response);

	}

}
