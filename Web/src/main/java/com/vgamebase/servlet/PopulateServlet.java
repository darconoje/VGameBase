package com.vgamebase.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.vgamebase.model.Game;
import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.GamePublisher;
import com.vgamebase.model.Genre;
import com.vgamebase.model.Platform;
import com.vgamebase.model.Publisher;
import com.vgamebase.model.Region;
import com.vgamebase.model.RegionSales;
import com.vgamebase.model.UserProfile;
import com.vgamebase.servlet.AbstractServlet;

@WebServlet(name = "PopulateServlet", urlPatterns = { "/populate" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class PopulateServlet extends AbstractServlet {

	private static final long serialVersionUID = -1720688734823865429L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<Part> fileParts = request.getParts().stream()
				.filter(part -> part.getName().contains("game") || part.getName().contains("genre")
						|| part.getName().contains("platform") || part.getName().contains("publisher")
						|| part.getName().contains("game_publisher") || part.getName().contains("game_platform")
						|| part.getName().contains("region") || part.getName().contains("region_sales")
						|| part.getName().contains("user_profile") && part.getSize() > 0)
				.collect(Collectors.toList());

		for (Part p : fileParts) {
			if (!p.getContentType().equals("application/json")) {
				session.setAttribute("invalidFileExtension", "true");
				response.sendRedirect("./admin");
			} else {

				String table = p.getName();

				boolean invalidJSON = false;

				byte[] bytes = IOUtils.toByteArray(p.getInputStream());
				
				
				try {
					
					JSONArray array = new JSONArray(new String(bytes));
					
					switch (table) {

					case "game":
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							try {
								String game = json.getString("game_name");
								Genre gameGenre = maestrosService.findGenreByPk(json.getLong("genre_id"));
								Game g = new Game();
								g.setGenre(gameGenre);
								g.setName(game);
								gameService.save(g);
							} catch (Exception e) {
								invalidJSON = true;
								e.printStackTrace();
								break;
							}
						}
						break;
					case "genre":
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							try {
								String genre = json.getString("genre_name");
								Genre g = new Genre();
								g.setName(genre);
								maestrosService.saveGenre(g);							
							} catch (Exception e) {
								invalidJSON = true;
								e.printStackTrace();
								break;
							}
						}
						break;
					case "publisher":
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							try {
								String publisher = json.getString("publisher_name");
								Publisher pu = new Publisher();
								pu.setName(publisher);
								maestrosService.savePublisher(pu);
							} catch (Exception e) {
								invalidJSON = true;
								e.printStackTrace();
								break;
							}
						}
						break;
					case "platform":
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							try {
								String platform = json.getString("platform_name");
								Platform pl = new Platform();
								pl.setName(platform);
								maestrosService.savePlatform(pl);
							} catch (Exception e) {
								invalidJSON = true;
								e.printStackTrace();
								break;
							}
						}
						break;
					case "game_publisher":
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							try {
								Game game = gameService.findByPk(json.getLong("game_id"));
								Publisher publisher = maestrosService.findPublisherByPk(json.getLong("publisher_id"));
								GamePublisher gpu = new GamePublisher();
								gpu.setGame(game);
								gpu.setPublisher(publisher);
								maestrosService.saveGamePublisher(gpu);
							} catch (Exception e) {
								invalidJSON = true;
								e.printStackTrace();
								break;
							}
						}
						break;
					case "game_platform":
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							try {
								int releaseYear = json.getInt("release_year");
								Platform platform = maestrosService.findPlatformByPk(json.getLong("platform_id"));
								GamePublisher gamePublisher = maestrosService
										.findGamePublisherByPk(json.getLong("game_publisher_id"));
								GamePlatform gpl = new GamePlatform();
								gpl.setReleaseYear(releaseYear);
								gpl.setPlatform(platform);
								gpl.setGamepublisher(gamePublisher);
								gpl.setActive(true);
								maestrosService.saveGamePlatform(gpl);
							} catch (Exception e) {
								invalidJSON = true;
								e.printStackTrace();
								break;
							}
						}
						break;
					case "region":
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							try {
								String region = json.getString("region_name");
								Region r = new Region();
								r.setName(region);
								maestrosService.saveRegion(r);
							} catch (Exception e) {
								invalidJSON = true;
								e.printStackTrace();
								break;
							}
						}
						break;
					case "region_sales":
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							try {
								float numSales = json.getFloat("num_sales");
								Region region = maestrosService.findRegionByPk(json.getLong("region_id"));
								GamePlatform gamePlatform = maestrosService
										.findGamePlatformByPk(json.getLong("game_platform_id"));
								RegionSales rs = new RegionSales();
								rs.setSales(numSales);
								rs.setRegion(region);
								rs.setGamePlatform(gamePlatform);
								maestrosService.saveRegionSales(rs);
							} catch (Exception e) {
								invalidJSON = true;
								e.printStackTrace();
								break;
							}						
						}
						break;
					case "user_profile":
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							try {
								String type = json.getString("type");
								UserProfile up = new UserProfile();
								up.setType(type);
								maestrosService.saveUserProfile(up);
							} catch (Exception e) {
								invalidJSON = true;
								e.printStackTrace();
								break;
							}
						}
						break;
					default:
						invalidJSON = true;
						break;

					}					
					
				}catch(Exception e) {
					invalidJSON = true;
					e.printStackTrace();
				}



				if (invalidJSON == false) {
					session.setAttribute("populatedTable", "true");
					response.sendRedirect("./admin");
				} else {
					session.setAttribute("invalidJSONFormat", "true");
					response.sendRedirect("./admin");
				}

			}
		}

	}

}
