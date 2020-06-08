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
import com.vgamebase.model.datatable.DataTableAdminRegions;
import com.vgamebase.servlet.AbstractServlet;
import com.vgamebase.servlet.configuration.HibernateProxyTypeAdapter;

@WebServlet(name = "AdminRegionsJSONServlet", urlPatterns = {
"/adminRegionsJSON" })
public class AdminRegionJSONServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DataTableAdminRegions datatable = new DataTableAdminRegions();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int draw = Integer.parseInt(request.getParameter("draw"));
		datatable.setDraw(draw);
		
		int start = Integer.valueOf(request.getParameter("start"));
		int length = Integer.valueOf(request.getParameter("length"));
		
		long countTotal = maestrosService.totalRegions();
		datatable.setRecordsTotal(countTotal);
		
		String search = request.getParameter("search[value]");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (search != null && !search.equals("")) {
			map.put("r.name", search);
		}
		
		long count = maestrosService.totalRegionsFilter(map);
		datatable.setRecordsFiltered(count);
		
		String orderCol = request.getParameter("order[0][column]");
		String orderDir = request.getParameter("order[0][dir]");
		
		String order = "";
		
		switch (orderCol == null ? "" : orderCol) {
		case "0":
			order = "r.id";
			break;
		case "1":
			order = "r.name";
			break;
		default:
			break;
		}
		
		if(orderCol.equals("") && search.equals("")) {
			datatable.setRegions(maestrosService.paginationRegionInit(start, length));
		} else if(search.equals("")) {
			datatable.setRegions(maestrosService.paginationRegionOrderNoFilter(start, order, orderDir, length));
		} else if(orderCol.equals("")) {
			datatable.setRegions(maestrosService.paginationRegionFilterNoOrder(start, map, length));
		} else {
			datatable.setRegions(maestrosService.paginationRegionFilterAndOrder(start, map, order, orderDir, length));
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
