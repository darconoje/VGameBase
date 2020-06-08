package com.vgamebase.servlet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.vgamebase.model.Game;
import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.GamePublisher;
import com.vgamebase.model.Genre;
import com.vgamebase.model.Platform;
import com.vgamebase.model.Publisher;
import com.vgamebase.model.Region;
import com.vgamebase.model.RegionSales;
import com.vgamebase.model.User;
import com.vgamebase.services.MaestrosService;
import com.vgamebase.services.impl.GameServiceImpl;
import com.vgamebase.services.impl.UserServiceImpl;
import com.vgamebase.servlet.validator.UserValidator;

@Controller("adminController")
public class AdminController {

	@Autowired
	private MaestrosService maestrosService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private GameServiceImpl gameService;

	public void insertUser(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userprofile = request.getParameter("userProfile");

		UserValidator userValidator = new UserValidator();

		boolean valid = userValidator.validateUser(request);

		HttpSession session = request.getSession();

		if (userService.findByUsername(username) != null && userService.findByUsername(username).isActive() == true) {
			valid = false;
			session.setAttribute("takenUsername", "true");
		}

		if (userService.findByEmail(email) != null && userService.findByUsername(username).isActive() == true) {
			valid = false;
			session.setAttribute("takenEmail", "true");
		}

		if (valid == true) {

			if (userService.findByUsername(username) != null
					&& userService.findByUsername(username).isActive() == false) {

				User newUser = userService.findByUsername(username);

				newUser.setUserName(username);
				newUser.setEmail(email);
				newUser.setUserProfile(maestrosService.findUserProfileById(Integer.valueOf(userprofile)));
				newUser.setActive(true);
				PasswordEncoder encoder = new BCryptPasswordEncoder();
				String encryptedPassword = encoder.encode(password);
				newUser.setPassword(encryptedPassword);

				userService.update(newUser);

			} else {

				User newUser = new User();

				newUser.setUserName(username);
				newUser.setEmail(email);
				newUser.setUserProfile(maestrosService.findUserProfileById(Integer.valueOf(userprofile)));
				newUser.setActive(true);
				PasswordEncoder encoder = new BCryptPasswordEncoder();
				String encryptedPassword = encoder.encode(password);
				newUser.setPassword(encryptedPassword);

				userService.save(newUser);

			}

			session.setAttribute("insertedUser", "true");

		}

	}

	public void insertGenre(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");

		// Validator
		boolean valid = false;
		if (name != null && !name.equals("")) {
			valid = true;
		}

		HttpSession session = request.getSession();
		if (maestrosService.findGenreByName(name) != null) {
			valid = false;
			session.setAttribute("duplicatedGenre", "true");
		}

		if (valid == true) {

			Genre newGenre = new Genre();

			newGenre.setName(name);
			maestrosService.saveGenre(newGenre);

			session.setAttribute("insertedGenre", "true");

		}

	}

	public void insertPublisher(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");

		// Validator
		boolean valid = false;
		if (name != null && !name.equals("")) {
			valid = true;
		}

		HttpSession session = request.getSession();
		if (maestrosService.findPublisherByName(name) != null) {
			valid = false;
			session.setAttribute("duplicatedPublisher", "true");
		}

		if (valid == true) {

			Publisher newPublisher = new Publisher();

			newPublisher.setName(name);
			maestrosService.savePublisher(newPublisher);

			session.setAttribute("insertedPublisher", "true");

		}

	}

	public void insertPlatform(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");

		// Validator
		boolean valid = false;
		if (name != null && !name.equals("")) {
			valid = true;
		}

		HttpSession session = request.getSession();
		if (maestrosService.findPlatformByName(name) != null) {
			valid = false;
			session.setAttribute("duplicatedPlatform", "true");
		}

		if (valid == true) {

			Platform newPlatform = new Platform();

			newPlatform.setName(name);
			maestrosService.savePlatform(newPlatform);

			session.setAttribute("insertedPlatform", "true");

		}

	}

	public void insertRegion(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");

		// Validator
		boolean valid = false;
		if (name != null && !name.equals("")) {
			valid = true;
		}

		HttpSession session = request.getSession();
		if (maestrosService.findRegionByName(name) != null) {
			valid = false;
			session.setAttribute("duplicatedRegion", "true");
		}

		if (valid == true) {

			Region newRegion = new Region();

			newRegion.setName(name);
			maestrosService.saveRegion(newRegion);

			session.setAttribute("insertedRegion", "true");

		}

	}

	public void updateUser(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userprofile = request.getParameter("userProfile");

		String id = request.getParameter("id");

		UserValidator userValidator = new UserValidator();

		boolean valid = userValidator.validateUser(request);

		HttpSession session = request.getSession();

		User user = userService.findById(Long.valueOf(id));

		if (userService.findByUsername(username) != null && !username.equals(user.getUserName())) {
			valid = false;
			session.setAttribute("takenUsername", "true");
		}

		if (userService.findByEmail(email) != null && !email.equals(user.getEmail())) {
			valid = false;
			session.setAttribute("takenEmail", "true");
		}

		if (valid == true) {

			user.setUserName(username);
			user.setEmail(email);
			user.setUserProfile(maestrosService.findUserProfileById(Integer.valueOf(userprofile)));

			if (password != null && !password.equals("")) {
				PasswordEncoder encoder = new BCryptPasswordEncoder();
				String encryptedPassword = encoder.encode(password);
				user.setPassword(encryptedPassword);
			}

			userService.update(user);

			session.setAttribute("updatedUser", "true");

		}

	}

	public void updateGenre(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		String id = request.getParameter("id");

		// Validator
		boolean valid = false;
		if (name != null && !name.equals("")) {
			valid = true;
		}

		HttpSession session = request.getSession();
		if (maestrosService.findGenreByName(name) != null) {
			valid = false;
			session.setAttribute("duplicatedGenre", "true");
		}

		if (valid == true) {

			Genre genre = maestrosService.findGenreByPk(Long.valueOf(id));

			genre.setName(name);
			maestrosService.updateGenre(genre);

			session.setAttribute("updatedGenre", "true");

		}

	}

	public void updatePublisher(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		String id = request.getParameter("id");

		// Validator
		boolean valid = false;
		if (name != null && !name.equals("")) {
			valid = true;
		}

		HttpSession session = request.getSession();
		if (maestrosService.findPublisherByName(name) != null) {
			valid = false;
			session.setAttribute("duplicatedPublisher", "true");
		}

		if (valid == true) {

			Publisher publisher = maestrosService.findPublisherByPk(Long.valueOf(id));

			publisher.setName(name);
			maestrosService.updatePublisher(publisher);

			session.setAttribute("updatedPublisher", "true");

		}

	}

	public void updatePlatform(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		String id = request.getParameter("id");

		// Validator
		boolean valid = false;
		if (name != null && !name.equals("")) {
			valid = true;
		}

		HttpSession session = request.getSession();
		if (maestrosService.findPlatformByName(name) != null) {
			valid = false;
			session.setAttribute("duplicatedPlatform", "true");
		}

		if (valid == true) {

			Platform platform = maestrosService.findPlatformByPk(Long.valueOf(id));

			platform.setName(name);
			maestrosService.updatePlatform(platform);

			session.setAttribute("updatedPlatform", "true");

		}

	}

	public void updateRegion(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		String id = request.getParameter("id");

		// Validator
		boolean valid = false;
		if (name != null && !name.equals("")) {
			valid = true;
		}

		HttpSession session = request.getSession();
		if (maestrosService.findRegionByName(name) != null) {
			valid = false;
			session.setAttribute("duplicatedRegion", "true");
		}

		if (valid == true) {

			Region region = maestrosService.findRegionByPk(Long.valueOf(id));

			region.setName(name);
			maestrosService.updateRegion(region);

			session.setAttribute("updatedRegion", "true");

		}

	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");

		User user = userService.findById(Long.valueOf(id));

		user.setActive(false);

		userService.update(user);

		HttpSession session = request.getSession();

		session.setAttribute("deletedUser", "true");

	}

	public void deleteGenre(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			String id = request.getParameter("id");

			List<Game> games = gameService.findAllByGenre(maestrosService.findGenreByPk(Long.valueOf(id)));

			if (maestrosService.findGenreByName("Undefined") == null) {

				Genre newGenre = new Genre();

				newGenre.setName("Undefined");
				maestrosService.saveGenre(newGenre);

			}

			if (games != null) {

				games.stream().forEach(g -> {
					g.setGenre(maestrosService.findGenreByName("Undefined"));
					gameService.update(g);
				});

			}

			maestrosService.deleteGenre(maestrosService.findGenreByPk(Long.valueOf(id)));

			session.setAttribute("deletedGenre", "true");

		} catch (Exception e) {

			session.setAttribute("deleteUndefined", "true");
			e.printStackTrace();

		}

	}

	public void deletePublisher(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			String id = request.getParameter("id");

			List<GamePublisher> gamePublishers = maestrosService
					.findAllGamePublishersByPublisher(maestrosService.findPublisherByPk(Long.valueOf(id)));

			if (maestrosService.findPublisherByName("Undefined") == null) {

				Publisher newPublisher = new Publisher();

				newPublisher.setName("Undefined");
				maestrosService.savePublisher(newPublisher);

			}

			if (gamePublishers != null) {

				gamePublishers.stream().forEach(gp -> {
					gp.setPublisher(maestrosService.findPublisherByName("Undefined"));
					maestrosService.updateGamePublisher(gp);
				});

			}

			maestrosService.deletePublisher(maestrosService.findPublisherByPk(Long.valueOf(id)));

			session.setAttribute("deletedPublisher", "true");

		} catch (Exception e) {

			session.setAttribute("deleteUndefined", "true");
			e.printStackTrace();

		}

	}

	public void deletePlatform(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			String id = request.getParameter("id");

			List<GamePlatform> gamePlatforms = maestrosService
					.findAllGamePlatformsByPlatform(maestrosService.findPlatformByPk(Long.valueOf(id)));

			if (maestrosService.findPlatformByName("Undefined") == null) {

				Platform newPlatform = new Platform();

				newPlatform.setName("Undefined");
				maestrosService.savePlatform(newPlatform);

			}

			if (gamePlatforms != null) {

				gamePlatforms.stream().forEach(gp -> {
					gp.setPlatform(maestrosService.findPlatformByName("Undefined"));
					maestrosService.updateGamePlatform(gp);
				});

			}

			maestrosService.deletePlatform(maestrosService.findPlatformByPk(Long.valueOf(id)));

			session.setAttribute("deletedPlatform", "true");

		} catch (Exception e) {

			session.setAttribute("deleteUndefined", "true");
			e.printStackTrace();

		}

	}

	public void deleteRegion(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			String id = request.getParameter("id");

			List<RegionSales> regionSales = maestrosService
					.findAllRegionSalesByRegion(maestrosService.findRegionByPk(Long.valueOf(id)));

			if (maestrosService.findRegionByName("Undefined") == null) {

				Region newRegion = new Region();

				newRegion.setName("Undefined");
				maestrosService.saveRegion(newRegion);

			}

			if (regionSales != null) {

				regionSales.stream().forEach(rs -> {
					if (maestrosService.findRegionSalesByRegionAndGamePlatform(
							maestrosService.findRegionByName("Undefined"), rs.getGamePlatform()) == null) {
						rs.setRegion(maestrosService.findRegionByName("Undefined"));
						maestrosService.updateRegionSales(rs);
					} else {
						RegionSales rs2 = maestrosService.findRegionSalesByRegionAndGamePlatform(
								maestrosService.findRegionByName("Undefined"), rs.getGamePlatform());
						rs2.setSales(rs2.getSales()+rs.getSales());
						maestrosService.updateRegionSales(rs2);
					}

				});

			}

			maestrosService.deleteRegion(maestrosService.findRegionByPk(Long.valueOf(id)));

			session.setAttribute("deletedRegion", "true");

		} catch (Exception e) {

			session.setAttribute("deleteUndefined", "true");
			e.printStackTrace();

		}

	}

}
