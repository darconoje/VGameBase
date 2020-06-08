package com.vgamebase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vgamebase.model.Region;
import com.vgamebase.model.RegionSales;

@WebServlet(name = "GamesServlet", urlPatterns = { "/games" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class GamesServlet extends AbstractServlet {

	private static final long serialVersionUID = -1720688734823865429L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = request.getParameter("view");
		String id = request.getParameter("id");

		if (view != null && view.equals("game") && id != null && !id.equals("") && !id.equals("null")) {
			request.setAttribute("game", maestrosService.findGamePlatformByPk(Long.valueOf(id)));
			List<Region> regions = maestrosService.findAllRegions();
			List<RegionSales> regionsales = new ArrayList<RegionSales>();
			for (Region region : regions) {
				regionsales.add(maestrosService.findRegionSalesByRegionAndGamePlatform(region,
						maestrosService.findGamePlatformByPk(Long.valueOf(id))));
			}
			request.setAttribute("regionsales", regionsales);
			request.getRequestDispatcher("./viewgame.jsp").forward(request, response);
		} else if (view != null && view.equals("gameAdmin")) {
			request.setAttribute("genres", maestrosService.findAllGenres());
			request.setAttribute("publishers", maestrosService.findAllPublishers());
			request.setAttribute("platforms", maestrosService.findAllPlatforms());
			request.setAttribute("regions", maestrosService.findAllRegions());
			if (id != null && !id.equals("") && !id.equals("null")) {
				request.setAttribute("game", maestrosService.findGamePlatformByPk(Long.valueOf(id)));
				List<Region> regions = maestrosService.findAllRegions();
				List<RegionSales> regionsales = new ArrayList<RegionSales>();
				for (Region region : regions) {
					regionsales.add(maestrosService.findRegionSalesByRegionAndGamePlatform(region,
							maestrosService.findGamePlatformByPk(Long.valueOf(id))));
				}
				request.setAttribute("regionsales", regionsales);
			}
			request.getRequestDispatcher("./viewgameadmin.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String imageId = request.getParameter("key");
		String delete = request.getParameter("delete");

		if (delete == null || delete.equals("") || delete.equals("null")) {
			request.setAttribute("genres", maestrosService.findAllGenres());
			request.setAttribute("publishers", maestrosService.findAllPublishers());
			request.setAttribute("platforms", maestrosService.findAllPlatforms());
			request.setAttribute("regions", maestrosService.findAllRegions());
			if (id != null && !id.equals("") && !id.equals("null")) {
				gameController.updateGame(request, response);
				response.sendRedirect("./games?view=gameAdmin&id=" + id);
			} else if (imageId != null && !imageId.equals("") && !imageId.equals("null")) {
				gameController.deleteImage(request, response);
			} else {
				gameController.insertGame(request, response);
				response.sendRedirect("./games?view=gameAdmin");
			}
		} else {
			gameController.deleteGame(request, response);
		}

	}

}
