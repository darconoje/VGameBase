package com.vgamebase.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vgamebase.servlet.configuration.HibernateProxyTypeAdapter;
import com.vgamebase.model.Comment;
import com.vgamebase.model.Game;
import com.vgamebase.model.GamePlatform;
import com.vgamebase.model.GamePublisher;
import com.vgamebase.model.Image;
import com.vgamebase.model.RegionSales;
import com.vgamebase.model.User;
import com.vgamebase.model.Vote;
import com.vgamebase.model.datatable.DeleteImage;
import com.vgamebase.services.CommentService;
import com.vgamebase.services.GameService;
import com.vgamebase.services.ImageService;
import com.vgamebase.services.MaestrosService;
import com.vgamebase.services.VoteService;
import com.vgamebase.services.impl.UserServiceImpl;
import com.vgamebase.servlet.validator.GameValidator;

@Controller("gameController")
public class GameController {

	@Autowired
	private MaestrosService maestrosService;

	@Autowired
	private GameService gameService;

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private VoteService voteService;

	public void insertGame(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("name");
		String releaseYear = request.getParameter("releaseYear");
		String genre = request.getParameter("genre");
		String publisher = request.getParameter("publisher");
		String platform = request.getParameter("platform");
		String[] regionSales = request.getParameterValues("regionSales");

		GameValidator gameValidator = new GameValidator();

		boolean valid = gameValidator.validateGame(request);

		HttpSession session = request.getSession();

		if (maestrosService.findGamePlatformByGamePublisherAndPlatform(
				maestrosService.findGamePublisherByGameAndPublisher(gameService.findByName(name),
						maestrosService.findPublisherByPk(Long.valueOf(publisher))),
				maestrosService.findPlatformByPk(Long.valueOf(platform))) != null) {
			valid = false;
			session.setAttribute("duplicatedGamePlatform", "true");
		}

		if (valid == true) {

			if (gameService.findByName(name) == null) {
				Game newGame = new Game();
				newGame.setGenre(maestrosService.findGenreByPk(Long.valueOf(genre)));
				newGame.setName(name);
				gameService.save(newGame);
			}

			if (maestrosService.findGamePublisherByGameAndPublisher(gameService.findByName(name),
					maestrosService.findPublisherByPk(Long.valueOf(publisher))) == null) {
				GamePublisher newGamePublisher = new GamePublisher();
				newGamePublisher.setGame(gameService.findByName(name));
				newGamePublisher.setPublisher(maestrosService.findPublisherByPk(Long.valueOf(publisher)));
				maestrosService.saveGamePublisher(newGamePublisher);
			}

			GamePlatform newGamePlatform = new GamePlatform();
			newGamePlatform.setActive(true);
			newGamePlatform.setReleaseYear(Integer.valueOf(releaseYear));
			newGamePlatform.setGamepublisher(maestrosService.findGamePublisherByGameAndPublisher(
					gameService.findByName(name), maestrosService.findPublisherByPk(Long.valueOf(publisher))));
			newGamePlatform.setPlatform(maestrosService.findPlatformByPk(Long.valueOf(platform)));
			maestrosService.saveGamePlatform(newGamePlatform);

			List<Part> fileParts = request.getParts().stream()
					.filter(part -> part.getName().contains("image") && part.getSize() > 0)
					.collect(Collectors.toList());

			List<Image> images = new ArrayList<>();
			for (Part p : fileParts) {

				byte[] bytes = IOUtils.toByteArray(p.getInputStream());
				String nameImage = p.getSubmittedFileName();
				Image image = new Image();
				image.setImage(bytes);
				image.setName(nameImage);
				images.add(image);
			}

			if (!images.isEmpty()) {

				for (Image img : images) {
					img.setGame(newGamePlatform);
					imageService.save(img);
				}

			}

			long counter = 1;
			for (String rs : regionSales) {
				RegionSales newRegionSales = new RegionSales();
				newRegionSales.setSales(Float.valueOf(rs));
				newRegionSales.setGamePlatform(maestrosService.findGamePlatformByGamePublisherAndPlatform(
						maestrosService.findGamePublisherByGameAndPublisher(gameService.findByName(name),
								maestrosService.findPublisherByPk(Long.valueOf(publisher))),
						maestrosService.findPlatformByPk(Long.valueOf(platform))));
				newRegionSales.setRegion(maestrosService.findRegionByPk(counter));
				maestrosService.saveRegionSales(newRegionSales);

				counter++;
			}

			session.setAttribute("insertedGame", "true");

		}

	}

	public void updateGame(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("name");
		String releaseYear = request.getParameter("releaseYear");
		String genre = request.getParameter("genre");
		String publisher = request.getParameter("publisher");
		String platform = request.getParameter("platform");
		String[] regionSales = request.getParameterValues("regionSales");

		String id = request.getParameter("id");

		GameValidator gameValidator = new GameValidator();

		boolean valid = gameValidator.validateGame(request);

		HttpSession session = request.getSession();

		GamePlatform gameplatform = maestrosService.findGamePlatformByPk(Long.valueOf(id));
		Game game = gameplatform.getGamepublisher().getGame();
		GamePublisher gamepublisher = gameplatform.getGamepublisher();

		if (maestrosService
				.findGamePlatformByGamePublisherAndPlatform(
						maestrosService.findGamePublisherByGameAndPublisher(gameService.findByName(
								name), maestrosService.findPublisherByPk(Long.valueOf(publisher))),
						maestrosService.findPlatformByPk(Long.valueOf(platform))) != null
				&& gameplatform.getId() != maestrosService.findGamePlatformByGamePublisherAndPlatform(
						maestrosService.findGamePublisherByGameAndPublisher(gameService.findByName(name),
								maestrosService.findPublisherByPk(Long.valueOf(publisher))),
						maestrosService.findPlatformByPk(Long.valueOf(platform))).getId()) {
			valid = false;
			session.setAttribute("duplicatedGame", "true");
		}

		if (gameService.findByName(name) != null && !name.equals(game.getName())) {
			valid = false;
			session.setAttribute("duplicatedGame", "true");
		}

		if (maestrosService.findGamePublisherByGameAndPublisher(gameService.findByName(name),
				maestrosService.findPublisherByPk(Long.valueOf(publisher))) != null && gamepublisher.getId() != maestrosService.findGamePublisherByGameAndPublisher(gameService.findByName(name),
						maestrosService.findPublisherByPk(Long.valueOf(publisher))).getId()) {
			valid = false;
			session.setAttribute("duplicatedGame", "true");
		}

		if (valid == true) {

			game.setName(name);
			game.setGenre(maestrosService.findGenreByPk(Long.valueOf(genre)));

			gamepublisher.setGame(game);
			gamepublisher.setPublisher(maestrosService.findPublisherByPk(Long.valueOf(publisher)));

			gameplatform.setGamepublisher(gamepublisher);
			gameplatform.setPlatform(maestrosService.findPlatformByPk(Long.valueOf(platform)));
			gameplatform.setReleaseYear(Integer.valueOf(releaseYear));

			long counter = 1;
			for (String rs : regionSales) {
				RegionSales newRegionSales = maestrosService
						.findRegionSalesByRegionAndGamePlatform(maestrosService.findRegionByPk(counter), gameplatform);
				newRegionSales.setSales(Float.valueOf(rs));
				newRegionSales.setGamePlatform(gameplatform);
				newRegionSales.setRegion(maestrosService.findRegionByPk(counter));
				maestrosService.updateRegionSales(newRegionSales);

				counter++;
			}

			List<Part> fileParts = request.getParts().stream()
					.filter(part -> part.getName().contains("image") && part.getSize() > 0)
					.collect(Collectors.toList());

			List<Image> images = new ArrayList<>();
			for (Part p : fileParts) {

				byte[] bytes = IOUtils.toByteArray(p.getInputStream());
				String nameImage = p.getSubmittedFileName();
				Image image = new Image();
				image.setImage(bytes);
				image.setName(nameImage);
				images.add(image);
			}

			if (!images.isEmpty()) {

				for (Image img : images) {
					if (imageService.findByName(img.getName()) == null) {
						img.setGame(gameplatform);
						imageService.save(img);
					}
				}

			}

			session.setAttribute("updatedGame", "true");

		}

	}

	public void deleteGame(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		
		GamePlatform gamePlatform = maestrosService.findGamePlatformByPk(Long.valueOf(id));
		
		gamePlatform.setActive(false);
		
		maestrosService.updateGamePlatform(gamePlatform);
		
		HttpSession session = request.getSession();

		session.setAttribute("deletedGame", "true");
		
	}
	
	public void deleteImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String imageId = request.getParameter("key");
		
		imageService.delete(imageService.findByPk(Long.valueOf(imageId)));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String s = "";

		Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
		out.print(gson.toJson(s));
		out.flush();
		
	}
	
	public void postComment(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String comment = request.getParameter("comment");
		
		Comment newComment = new Comment();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails principalUser = (UserDetails) principal;
		User user = userService.findByUsername(principalUser.getUsername());
		
		newComment.setGame(maestrosService.findGamePlatformByPk(Long.valueOf(id)));
		newComment.setText(comment);
		newComment.setUser(user);
		
		commentService.save(newComment);
		
		HttpSession session = request.getSession();

		session.setAttribute("postedComment", "true");
		session.setAttribute("user", user);
	}
	
	public void deleteComment(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		
		Comment comment = commentService.findByPk(Long.valueOf(id));
		
		commentService.delete(comment);
		
		HttpSession session = request.getSession();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails principalUser = (UserDetails) principal;
		User user = userService.findByUsername(principalUser.getUsername());
		
		session.setAttribute("user", user);
		
	}
	
	public void postVote(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String vote = request.getParameter("vote");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails principalUser = (UserDetails) principal;
		User user = userService.findByUsername(principalUser.getUsername());
		
		if(voteService.findByUserAndGamePlatform(user, maestrosService.findGamePlatformByPk(Long.valueOf(id))) == null) {
			
			Vote newVote = new Vote();
			
			newVote.setText(vote);
			newVote.setGame(maestrosService.findGamePlatformByPk(Long.valueOf(id)));
			newVote.setUser(user);
			
			voteService.save(newVote);
			
		} else {
			
			Vote newVote = voteService.findByUserAndGamePlatform(user, maestrosService.findGamePlatformByPk(Long.valueOf(id)));
			
			newVote.setText(vote);
			
			voteService.update(newVote);
			
		}
		
		HttpSession session = request.getSession();

		session.setAttribute("postedVote", "true");
		session.setAttribute("user", user);
		
	}

}
