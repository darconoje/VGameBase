<%@page import="java.nio.charset.StandardCharsets"%>
<%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page
	import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page
	import="org.springframework.security.core.authority.SimpleGrantedAuthority"%>
<%@page import="com.vgamebase.model.User"%>
<%@page import="com.vgamebase.model.Genre"%>
<%@page import="com.vgamebase.model.Publisher"%>
<%@page import="com.vgamebase.model.Platform"%>
<%@page import="com.vgamebase.model.Region"%>
<%@page import="com.vgamebase.model.GamePlatform"%>
<%@page import="com.vgamebase.model.Image"%>
<%@page import="com.vgamebase.model.RegionSales"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layout/head.jsp" />
<link rel="stylesheet" href="css/gameview.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.1/css/lightbox.css"
	crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.1/js/lightbox.js"></script>
</head>
<body class="bg-dark">
	<%
		UserDetails principalUser = null;
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
		principalUser = ((UserDetails) principal);
	}
	%>

	<%
		GamePlatform gameplatform = (GamePlatform) request.getAttribute("game");

	int releaseYear = 0;
	String platform = "";
	String publisher = "";
	String name = "";
	String genre = "";
	List<RegionSales> regionsales = new ArrayList<RegionSales>();
	List<Image> images = new ArrayList<Image>();
	List<Region> regions = new ArrayList<Region>();

	User user = (User) session.getAttribute("user");

	if (gameplatform != null) {
		releaseYear = gameplatform.getReleaseYear();
		platform = gameplatform.getPlatform().getName();
		publisher = gameplatform.getGamepublisher().getPublisher().getName();
		name = gameplatform.getGamepublisher().getGame().getName();
		genre = gameplatform.getGamepublisher().getGame().getGenre().getName();
		regionsales = (List<RegionSales>) request.getAttribute("regionsales");
		images = gameplatform.getImages();
		regions = (List<Region>) request.getAttribute("regions");
	}
	%>
	<jsp:include page="layout/header.jsp" />

	<div class="alert alert-success my-4 text-center"
		id="alertPostedComment" role="alert"
		<%String postedComment = (String) session.getAttribute("postedComment");%>
		<%if (postedComment == null || postedComment.equals("null") || postedComment.equals("")) {%>
		hidden="true" <%}%>>Comment posted successfully!</div>

	<div class="alert alert-success my-4 text-center" id="alertPostedVote"
		role="alert"
		<%String postedVote = (String) session.getAttribute("postedVote");%>
		<%if (postedVote == null || postedVote.equals("null") || postedVote.equals("")) {%>
		hidden="true" <%}%>>Vote posted successfully!</div>

	<div class="row mt-5" style="min-height: 100%">
		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto">
			<div class="mt-5 mb-5 col-12 mt-3 mb-2 bg-white p-4 container1">
				<div class="container">
					<div class="jumbotron bg-danger mt-4 rounded-pill p-4">
						<h1 class="text-center text-white font-weight-bold">
							<%
								if (gameplatform != null) {
							%><%=name%>
							<%
								}
							%>
						</h1>
					</div>
					<div class="container mb-5">
						<div class="container mb-5">
							<div class="row">
								<div class="col-5"></div>
								<div
									class="col-2 border border-dark d-flex justify-content-around">
									<div class="ml-3">
										<span class="text-success"><%=gameplatform.getLikes()%></span>
										<button class="btn btn-lg" id="voteLike">
											<span class="<% if(user.alreadyVoted(gameplatform,"like")) { %>fa fa-thumbs-up text-success <% }else{ %>fa fa-thumbs-o-up text-success<% } %>"></span>
										</button>
									</div>
									<div class="ml-2">
										<span class="text-danger"><%=gameplatform.getDislikes()%></span>
										<button class="btn btn-lg" id="voteDislike">
											<span class="<% if(user.alreadyVoted(gameplatform,"dislike")) { %>fa fa-thumbs-down text-danger <% }else{ %>fa fa-thumbs-o-down text-danger<% } %>"></span>
										</button>
									</div>
								</div>
								<div class="col-5"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i class="glyphicon glyphicon-tower text-white"></i></span>
										Genre
									</div>
									<div class="card-body">
										<h5 class="card-title mt-3">
											<%
												if (gameplatform != null) {
											%><%=genre%>
											<%
												}
											%>
										</h5>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i
											class="glyphicon glyphicon-briefcase text-white"></i></span>
										Publisher
									</div>
									<div class="card-body">
										<h5 class="card-title mt-3">
											<%
												if (gameplatform != null) {
											%><%=publisher%>
											<%
												}
											%>
										</h5>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
						</div>
						<div class="row mt-5">
							<div class="col-sm-6">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i
											class="glyphicon glyphicon-calendar text-white"></i></span> Release
										Year
									</div>
									<div class="card-body">
										<h5 class="card-title mt-3">
											<%
												if (gameplatform != null) {
											%><%=releaseYear%>
											<%
												}
											%>
										</h5>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i class="fa fa-gamepad text-white"></i></span> Platform
									</div>
									<div class="card-body">
										<h5 class="card-title mt-3">
											<%
												if (gameplatform != null) {
											%><%=platform%>
											<%
												}
											%>
										</h5>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
						</div>
						<div class="row mt-5">
							<div class="col-sm-12">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i class="fa fa-globe text-white"></i></span> Region
										Sales (in millions)
									</div>
									<div class="card-body">
										<%
											if (gameplatform != null) {

											if (regions.size() % 2 == 0) {

												for (int i = 0; i < regions.size(); i = i + 2) {
										%>

										<div class="row mt-2 mb-2">
											<div class="col-sm-6">
												<h5 class="card-title mt-3">
													<span class="font-weight-bold"><%=regions.get(i).getName()%>:</span>
													<%
														if (regionsales.get(i) == null) {
													%>
													0
													<%
														} else {
													%><%=regionsales.get(i).getSales()%>
													<%
														}
													%>
												</h5>
											</div>
											<div class="col-sm-6">
												<h5 class="card-title mt-3">
													<span class="font-weight-bold"><%=regions.get(i + 1).getName()%>:</span>
													<%
														if (regionsales.get(i + 1) == null) {
													%>
													0
													<%
														} else {
													%><%=regionsales.get(i + 1).getSales()%>
													<%
														}
													%>
												</h5>
											</div>
										</div>

										<%
											}
										%>

										<%
											} else {

											for (int i = 0; i < regions.size(); i = i + 2) {
										%>


										<%
											if (i == regions.size() - 1) {
										%>
										<div class="row mt-2 mb-2">
											<div class="col-sm-12">
												<h5 class="card-title mt-3">
													<span class="font-weight-bold"><%=regions.get(i).getName()%>:</span>
													<%
														if (regionsales.get(i) == null) {
													%>
													0
													<%
														} else {
													%><%=regionsales.get(i).getSales()%>
													<%
														}
													%>
												</h5>
											</div>
										</div>
										<%
											} else {
										%>

										<div class="row mt-2 mb-2">
											<div class="col-sm-6">
												<h5 class="card-title mt-3">
													<span class="font-weight-bold"><%=regions.get(i).getName()%>:</span>
													<%
														if (regionsales.get(i) == null) {
													%>
													0
													<%
														} else {
													%><%=regionsales.get(i).getSales()%>
													<%
														}
													%>
												</h5>
											</div>
											<div class="col-sm-6">
												<h5 class="card-title mt-3">
													<span class="font-weight-bold"><%=regions.get(i + 1).getName()%>:</span>
													<%
														if (regionsales.get(i + 1) == null) {
													%>
													0
													<%
														} else {
													%><%=regionsales.get(i + 1).getSales()%>
													<%
														}
													%>
												</h5>
											</div>
										</div>


										<%
											}
										}
										%>

										<%
											}

										}
										%>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="container mt-5 mb-5">
						<div class="row">
							<div class="col-sm-12">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i class="fa fa-camera text-white"></i></span> Images
									</div>
									<div class="card-body">
										<%
											if (images == null || images.size() == 0) {
										%>
										<div class="row">
											<div class="col-sm-12">
												<h5 class="card-title mt-3 font-italic text-center">This
													game doesn't have images yet</h5>
											</div>
										</div>
										<%
											} else {
											for (int i = 0; i < images.size(); i++) {
										%>
										<a
											href="data:image/png;base64, <%=images.get(i).getImageParsed()%>"
											data-lightbox="gameImages"><img
											src="data:image/png;base64, <%=images.get(i).getImageParsed()%>"
											style="max-width: 200px; max-height: 200px" /></a>
										<%
											}
										}
										%>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="container mt-5 mb-5">
						<div class="row">
							<div class="col-sm-12">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i class="fa fa-comments text-white"></i></span>
										Comments
									</div>
									<div class="card-body">
										<%
											if (gameplatform.getComments() == null || gameplatform.getComments().size() == 0) {
										%>
										<div class="row">
											<div class="col-sm-12">
												<h5 class="card-title mt-3 font-italic text-center">This
													game doesn't have comments yet</h5>
											</div>
										</div>
										<%
											} else {

											for (int i = 0; i < gameplatform.getComments().size(); i++) {
										%>


										<div class="row d-flex justify-content-center my-5">
											<div class="col-8">
												<div class="card card-white post p-4">
													<div class="post-heading">
														<div class="float-left image">
															<img src="img/avatar.png" class="avatar mr-4"
																alt="user profile image"
																style="max-width: 35px; max-height: 35px">
														</div>
														<div class="float-left meta">
															<div class="title h5">
																<b class="text-info"><%=gameplatform.getComments().get(i).getUser().getUserName()%></b>
																posted a comment.
															</div>
														</div>
														<%
															if (user != null) {
															if (user.getId() == gameplatform.getComments().get(i).getUser().getId() || principalUser != null
															&& principalUser.getAuthorities().contains(new SimpleGrantedAuthority("Moderator"))) {
														%>

														<div class="float-right">
															<button type="button"
																class="btn btn-danger eliminarComment"
																data-id="<%=gameplatform.getComments().get(i).getId()%>">
																<span class="fa fa-trash text-white"></span>
															</button>
														</div>
														<%
															}
														}
														%>
													</div>
													<div class="post-description border-top mt-4">
														<p class="mt-4 mb-3 font-weight-bold"><%=gameplatform.getComments().get(i).getText()%></p>
													</div>
												</div>
											</div>

										</div>


										<%
											}
										}
										%>
										<div class="row d-flex justify-content-center">
											<form class="needs-validation mt-4 col-4" action="./games"
												method="post" novalidate>
												<%
													if (gameplatform != null) {
												%>
												<input type="hidden" name="id"
													value="<%=gameplatform.getId()%>" />
												<%
													}
												%>
												<div class="form-group my-2">
													<label for="comment" class="font-weight-bold">Post
														a comment:</label>
													<textarea class="form-control" name="comment" id="comment"
														rows="7" placeholder="Write something here..."
														minlength="3" maxlength="120" required></textarea>
													<div class="valid-feedback">Looks good!</div>
													<div class="invalid-feedback">The comment must have
														at least 3 characters and less than 120</div>
												</div>
												<button type="submit" class="btn btn-lg btn-danger my-3"
													id="submitComment">
													<span class="fa fa-paper-plane font-white mr-2"></span>
													Send comment
												</button>
											</form>
										</div>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
							<a href="./games" class="mt-5 btn btn-danger btn-lg"
								role="button" aria-disabled="true">Back</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
	<jsp:include page="layout/footer.jsp" />
	<script>
		setInterval(function() {
			$.ajax({
				url : './onlineusers',
				type : 'GET',
				success : function(data) {
					$("#onlineusers").text(data);
				}
			})
		}, 5000);

		window.setTimeout(function() {
			$("#alertPostedComment").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("postedComment");%>
		window.setTimeout(function() {
			$("#alertPostedVote").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("postedVote");%>
		$('.eliminarComment').click(function() {
			var id = $(this).attr("data-id");
			$.ajax({
				type : "POST",
				url : "./games?delete=comment",
				data : {
					id : id
				},
				success : function(result) {
					location.reload();
				}
			});
		});

		$('#voteLike').click(function() {
			$.ajax({
				type : "POST",
				url : "./games",
				data : {
					id :
	<%=gameplatform.getId()%>
		,
					vote : "like"
				},
				success : function(result) {
					location.reload();
				}
			});
		});

		$('#voteDislike').click(function() {
			$.ajax({
				type : "POST",
				url : "./games",
				data : {
					id :
	<%=gameplatform.getId()%>
		,
					vote : "dislike"
				},
				success : function(result) {
					location.reload();
				}
			});
		});
	</script>
</body>
</html>