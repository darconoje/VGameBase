package com.vgamebase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminServlet", urlPatterns = { "/admin" })
public class AdminServlet extends AbstractServlet {

	private static final long serialVersionUID = -1720688734823865429L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = request.getParameter("view");
		String id = request.getParameter("id");

		if (view != null && view.equals("user")) {
			request.setAttribute("userProfiles", maestrosService.findAllUserProfiles());
			if (id != null && !id.equals("") && !id.equals("null")) {
				request.setAttribute("user", userService.findById(Long.valueOf(id)));
			}
			request.getRequestDispatcher("./viewuser.jsp").forward(request, response);
		} else if (view != null && view.equals("genre")) {
			if (id != null && !id.equals("") && !id.equals("null")) {
				request.setAttribute("genre", maestrosService.findGenreByPk(Long.valueOf(id)));
			}
			request.getRequestDispatcher("./viewgenre.jsp").forward(request, response);
		} else if (view != null && view.equals("publisher")) {
			if (id != null && !id.equals("") && !id.equals("null")) {
				request.setAttribute("publisher", maestrosService.findPublisherByPk(Long.valueOf(id)));
			}
			request.getRequestDispatcher("./viewpublisher.jsp").forward(request, response);
		} else if (view != null && view.equals("platform")) {
			if (id != null && !id.equals("") && !id.equals("null")) {
				request.setAttribute("platform", maestrosService.findPlatformByPk(Long.valueOf(id)));
			}
			request.getRequestDispatcher("./viewplatform.jsp").forward(request, response);
		} else if (view != null && view.equals("region")) {
			if (id != null && !id.equals("") && !id.equals("null")) {
				request.setAttribute("region", maestrosService.findRegionByPk(Long.valueOf(id)));
			}
			request.getRequestDispatcher("./viewregion.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("./admin.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = request.getParameter("view");
		String id = request.getParameter("id");
		String delete = request.getParameter("delete");

		if (delete == null || delete.equals("") || delete.equals("null")) {
			switch (view) {
			case "user":
				request.setAttribute("userProfiles", maestrosService.findAllUserProfiles());
				if (id != null && !id.equals("") && !id.equals("null")) {
					adminController.updateUser(request, response);
					response.sendRedirect("./admin?view=user&id=" + id);
				} else {
					adminController.insertUser(request, response);
					response.sendRedirect("./admin?view=user");
				}
				break;
			case "genre":
				if (id != null && !id.equals("") && !id.equals("null")) {
					adminController.updateGenre(request, response);
					response.sendRedirect("./admin?view=genre&id=" + id);
				} else {
					adminController.insertGenre(request, response);
					response.sendRedirect("./admin?view=genre");
				}
				break;
			case "publisher":
				if (id != null && !id.equals("") && !id.equals("null")) {
					adminController.updatePublisher(request, response);
					response.sendRedirect("./admin?view=publisher&id=" + id);
				} else {
					adminController.insertPublisher(request, response);
					response.sendRedirect("./admin?view=publisher");
				}
				break;
			case "platform":
				if (id != null && !id.equals("") && !id.equals("null")) {
					adminController.updatePlatform(request, response);
					response.sendRedirect("./admin?view=platform&id=" + id);
				} else {
					adminController.insertPlatform(request, response);
					response.sendRedirect("./admin?view=platform");
				}
				break;
			case "region":
				if (id != null && !id.equals("") && !id.equals("null")) {
					adminController.updateRegion(request, response);
					response.sendRedirect("./admin?view=region&id=" + id);
				} else {
					adminController.insertRegion(request, response);
					response.sendRedirect("./admin?view=region");
				}
				break;
			default:
				response.sendRedirect("./admin");
				break;
			}
			
		} else {
			
			switch (delete) {
			
			case "user":
				adminController.deleteUser(request, response);
				break;
			case "genre":
				adminController.deleteGenre(request, response);
				break;
			case "publisher":
				adminController.deletePublisher(request, response);
				break;
			case "platform":
				adminController.deletePlatform(request, response);
				break;
			case "region":
				adminController.deleteRegion(request, response);
				break;
			default:
				break;
			
			}

		}

	}

}
