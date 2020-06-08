package com.vgamebase.servlet.datatable;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vgamebase.model.Genre;
import com.vgamebase.model.Platform;
import com.vgamebase.model.Publisher;
import com.vgamebase.model.datatable.DataTableGames;
import com.vgamebase.servlet.AbstractServlet;
import com.vgamebase.servlet.configuration.HibernateProxyTypeAdapter;

@WebServlet(name = "GamesJSONServlet", urlPatterns = { "/gamesJSON" })
public class GamesJSONServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DataTableGames datatable = new DataTableGames();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		int draw = Integer.parseInt(request.getParameter("draw"));
		datatable.setDraw(draw);

		int start = Integer.valueOf(request.getParameter("start"));
		int length = Integer.valueOf(request.getParameter("length"));
		
		long countTotal = maestrosService.totalGamePlatforms();
		datatable.setRecordsTotal(countTotal);
		
		String genre = (String) request.getParameter("columns[2][search][value]");
		String platform = (String) request.getParameter("columns[3][search][value]");
		String publisher = (String) request.getParameter("columns[4][search][value]");
		String releaseYear = (String) request.getParameter("columns[5][search][value]");
		String search = request.getParameter("search[value]");

		Map<String, Object> map = new HashMap<String, Object>();
		
		Genre gr = new Genre();
		Platform pl = new Platform();
		Publisher pu = new Publisher();
		
		if (genre != null && !genre.equals("")) {
			gr = maestrosService.findGenreByPk(Long.valueOf(genre));
			map.put("gr", gr);
		}
		
		if (platform != null && !platform.equals("")) {
			pl = maestrosService.findPlatformByPk(Long.valueOf(platform));
			map.put("pl", pl);
		}
		
		if (publisher != null && !publisher.equals("")) {
			pu = maestrosService.findPublisherByPk(Long.valueOf(publisher));
			map.put("pu", pu);
		}	
		
		if (releaseYear != null && !releaseYear.equals("")) {
			int ry = Integer.valueOf(releaseYear);
			map.put("gp.releaseYear", ry);
		}

		if (search != null && !search.equals("")) {
			map.put("ga.name", search);
		}
		
		String hideInactives = request.getParameter("columns[1][search][value]");
		
		boolean hide = hideInactives.equals("true") ? true : false;

		long count = maestrosService.totalGamePlatformsFilter(map, hide);
		datatable.setRecordsFiltered(count);

		String orderCol = request.getParameter("order[0][column]");
		String orderDir = request.getParameter("order[0][dir]");

		String order = "";

		switch (orderCol == null ? "" : orderCol) {
		case "0":
			order = "gp.id";
			break;
		case "1":
			order = "ga.name";
			break;
		case "2":
			order = "gr.name";
			break;
		case "3":
			order = "pl.name";
			break;
		case "4":
			order = "pu.name";
			break;		
		case "5":
			order = "gp.releaseYear";
			break;			
		case "6":
			order = "global_sales";
		default:
			break;
		}
		
		if (orderCol.equals("") && search.equals("") && genre.equals("") && platform.equals("") && publisher.equals("") && releaseYear.equals("") ) {
			datatable.setGames(maestrosService.paginationGamePlatformInit(start, length, hide));
		} else if (search.equals("") && genre.equals("") && platform.equals("") && publisher.equals("") && releaseYear.equals("")) {
			datatable.setGames(maestrosService.paginationGamePlatformOrderNoFilter(start, order, orderDir, length, hide));
		} else if (orderCol.equals("")) {
			datatable.setGames(maestrosService.paginationGamePlatformFilterNoOrder(start, map, length, hide));
		} else {
			datatable.setGames(maestrosService.paginationGamePlatformFilterAndOrder(start, map, order, orderDir, length, hide));
		}
		
		Map<String, List<?>> filters = new HashMap<String, List<?>>();
		
		filters.put("genres", maestrosService.findAllGenres());
		filters.put("platforms", maestrosService.findAllPlatforms());
		filters.put("publishers", maestrosService.findAllPublishers());
		filters.put("releaseYears", maestrosService.findAllReleaseYears());
		
		datatable.setFilters(filters);
		
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
