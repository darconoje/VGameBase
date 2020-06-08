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
import com.vgamebase.model.datatable.DataTableAdminPublishers;
import com.vgamebase.servlet.AbstractServlet;
import com.vgamebase.servlet.configuration.HibernateProxyTypeAdapter;

@WebServlet(name = "AdminPublishersJSONServlet", urlPatterns = { "/adminPublishersJSON" })
public class AdminPublishersJSONServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataTableAdminPublishers datatable = new DataTableAdminPublishers();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		int draw = Integer.parseInt(request.getParameter("draw"));
		datatable.setDraw(draw);

		int start = Integer.valueOf(request.getParameter("start"));
		int length = Integer.valueOf(request.getParameter("length"));

		long countTotal = maestrosService.totalPublishers();
		datatable.setRecordsTotal(countTotal);

		String search = request.getParameter("search[value]");

		Map<String, Object> map = new HashMap<String, Object>();

		if (search != null && !search.equals("")) {
			map.put("p.name", search);
		}

		long count = maestrosService.totalPublishersFilter(map);
		datatable.setRecordsFiltered(count);

		String orderCol = request.getParameter("order[0][column]");
		String orderDir = request.getParameter("order[0][dir]");

		String order = "";

		switch (orderCol == null ? "" : orderCol) {
		case "0":
			order = "p.id";
			break;
		case "1":
			order = "p.name";
			break;
		default:
			break;
		}

		if (orderCol.equals("") && search.equals("")) {
			datatable.setPublishers(maestrosService.paginationPublisherInit(start, length));
		} else if (search.equals("")) {
			datatable.setPublishers(maestrosService.paginationPublisherOrderNoFilter(start, order, orderDir, length));
		} else if (orderCol.equals("")) {
			datatable.setPublishers(maestrosService.paginationPublisherFilterNoOrder(start, map, length));
		} else {
			datatable.setPublishers(maestrosService.paginationPublisherFilterAndOrder(start, map, order, orderDir, length));
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
