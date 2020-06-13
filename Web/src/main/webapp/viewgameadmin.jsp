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
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.9/css/fileinput.min.css"
	media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/gameviewadmin.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.1/js/plugins/piexif.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.1/js/plugins/sortable.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.1/js/plugins/purify.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.1/js/fileinput.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.1/themes/fas/theme.min.js">
	
</script>

</head>
<body class="bg-dark">
	<jsp:include page="layout/header.jsp" />

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
	
	User user = (User) session.getAttribute("user");
	
	if(gameplatform != null){
		releaseYear = gameplatform.getReleaseYear();
		platform = gameplatform.getPlatform().getName();
		publisher = gameplatform.getGamepublisher().getPublisher().getName();
		name = gameplatform.getGamepublisher().getGame().getName();
		genre = gameplatform.getGamepublisher().getGame().getGenre().getName();
		regionsales = (List<RegionSales>) request.getAttribute("regionsales");
		images = gameplatform.getImages();
	}
	%>

	<div class="alert alert-danger my-4 text-center"
		id="alertDuplicatedGamePlatform" role="alert"
		<%String duplicatedGamePlatform = (String) session.getAttribute("duplicatedGamePlatform");%>
		<%if (duplicatedGamePlatform == null || duplicatedGamePlatform.equals("null") || duplicatedGamePlatform.equals("")) {%>
		hidden="true" <%}%>>Duplicated game, please try again</div>

	<div class="alert alert-danger my-4 text-center"
		id="alertDuplicatedGame" role="alert"
		<%String duplicatedGame = (String) session.getAttribute("duplicatedGame");%>
		<%if (duplicatedGame == null || duplicatedGame.equals("null") || duplicatedGame.equals("")) {%>
		hidden="true" <%}%>>Duplicated game, please try again</div>

	<div class="alert alert-success my-4 text-center"
		id="alertInsertedGame" role="alert"
		<%String insertedGame = (String) session.getAttribute("insertedGame");%>
		<%if (insertedGame == null || insertedGame.equals("null") || insertedGame.equals("")) {%>
		hidden="true" <%}%>>Game added successfully!</div>

	<div class="alert alert-success my-4 text-center" id="alertUpdatedGame"
		role="alert"
		<%String updatedGame = (String) session.getAttribute("updatedGame");%>
		<%if (updatedGame == null || updatedGame.equals("null") || updatedGame.equals("")) {%>
		hidden="true" <%}%>>Game updated successfully!</div>

	<div class="alert alert-success my-4 text-center" id="alertPostedVote"
		role="alert"
		<%String postedVote = (String) session.getAttribute("postedVote");%>
		<%if (postedVote == null || postedVote.equals("null") || postedVote.equals("")) {%>
		hidden="true" <%}%>>Vote posted successfully!</div>

	<div class="row mt-5" style="min-height: 100%">
		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto">
			<h1 class="mt-5 mb-5 font-weight-bold text-white text-center">
				<u> <% if(gameplatform == null){ %>Add Game<% } else { %>Update Game<% } %>
				</u>
			</h1>
			<form
				class="mt-5 mb-3 col-12 mt-3 mb-2 bg-white p-4 border border-light rounded-lg needs-validation"
				action="./games" method="post" enctype="multipart/form-data"
				novalidate>

				<% if(gameplatform!=null){ %>
				<input type="hidden" name="id" value="<%=gameplatform.getId()%>" />
				<% } %>

				<h4 class="font-weight-bold">Game Details</h4>
				<fieldset class="form-group mt-3">
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold" for="name">Name:</label>
						<input type="text" class="form-control mb-2 col-4" id="name"
							name="name" <% if(gameplatform != null){ %> value="<%=name%>"
							<% } %> required>
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">This field must be filled</div>
					</div>
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold"
							for="releaseYear">Release Year:</label> <input type="number"
							class="form-control mb-2 col-4" id="releaseYear"
							name="releaseYear" min="1980" max="2020"
							<% if(gameplatform != null){ %> value=<%=releaseYear%> <% } %>
							required>
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">Year must be between 1980 and
							2020</div>
					</div>
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold" for="genre">Genre:</label>
						<div class="form-row ml-2">
							<select class="custom-select my-1 col-4" id="genre" name="genre"
								required>
								<option value="">Choose...</option>
								<%
									List<Genre> genres = (List<Genre>) request.getAttribute("genres");

								if (genres != null) {

									for (Genre g : genres) {
										String genreString = g.getName();
										long genreId = g.getId();
								%>
								<option value="<%=genreId%>"
									<% if(gameplatform!=null && genreString.equals(genre)){ %>
									selected <%} %>><%=genreString%></option>
								<%
									}

								}
								%>
							</select>
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">This field must be filled</div>
						</div>
					</div>
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold"
							for="publisher">Publisher:</label>
						<div class="form-row ml-2">
							<select class="custom-select my-1 col-4" id="publisher"
								name="publisher" required>
								<option value="">Choose...</option>
								<%
									List<Publisher> publishers = (List<Publisher>) request.getAttribute("publishers");

								if (publishers != null) {

									for (Publisher p : publishers) {
										String publisherString = p.getName();
										long publisherId = p.getId();
								%>
								<option value="<%=publisherId%>"
									<% if(gameplatform!=null && publisherString.equals(publisher)){ %>
									selected <%} %>><%=publisherString%></option>
								<%
									}

								}
								%>
							</select>
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">This field must be filled</div>
						</div>
					</div>
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold" for="platform">Platform:</label>
						<div class="form-row ml-2">
							<select class="custom-select my-1 col-4" id="platform"
								name="platform" required>
								<option value="">Choose...</option>
								<%
									List<Platform> platforms = (List<Platform>) request.getAttribute("platforms");

								if (platforms != null) {

									for (Platform p : platforms) {
										String platformString = p.getName();
										long platformId = p.getId();
								%>
								<option value="<%=platformId%>"
									<% if(gameplatform!=null && platformString.equals(platform)){ %>
									selected <%} %>><%=platformString%></option>
								<%
									}

								}
								%>
							</select>
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">This field must be filled</div>
						</div>
					</div>
				</fieldset>
				<h4 class="font-weight-bold">Game Sales Per Region (in
					millions)</h4>
				<fieldset class="form-group mt-3">
					<%
						List<Region> regions = (List<Region>) request.getAttribute("regions");

					if (regions != null) {
						int contador = 0;
						for (Region r : regions) {
							String regionString = r.getName();
							long regionId = r.getId();
					%>
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold"
							for="regionSales"><%=regionString%>:</label> <input type="number"
							class="form-control mb-2 col-4" id="regionSales"
							name="regionSales" min="0" step="0.01"
							<% if(gameplatform != null){ %>
							value=<%=regionsales.get(contador).getSales()%> <% } %> required>
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">This field must be filled</div>
					</div>
					<%
					contador++;				
						}

					}
					%>
				</fieldset>
				<h4 class="font-weight-bold">Game Images</h4>
				<fieldset class="form-group mt-3">
					<div class="file-loading mb-4">
						<input id="image" name="image" type="file" multiple>
					</div>
				</fieldset>
				<button type="submit" class="btn btn-lg btn-danger my-3" id="submit">Submit</button>
				<a href="./games" class="ml-3 btn btn-danger btn-lg" role="button"
					aria-disabled="true">Back</a>
			</form>

			<% if(gameplatform != null){ %>

			<div
				class="mt-5 mb-3 col-12 mt-3 mb-2 bg-white p-4 border
				border-light rounded-lg">
				<h4 class="font-weight-bold mb-1">Votes</h4>
				<span class="text-success"><%=gameplatform.getLikes()%></span>
				<button class="btn btn-lg" id="voteLike">
					<span
						class="<% if(user.alreadyVoted(gameplatform,"like")) { %>fa fa-thumbs-up text-success <% }else{ %>fa fa-thumbs-o-up text-success<% } %>"></span>
				</button>
				<span class="text-danger"><%=gameplatform.getDislikes()%></span>
				<button class="btn btn-lg" id="voteDislike">
					<span
						class="<% if(user.alreadyVoted(gameplatform,"dislike")) { %>fa fa-thumbs-down text-danger <% }else{ %>fa fa-thumbs-o-down text-danger<% } %>"></span>
				</button>
				<h4 class="font-weight-bold mt-3">Comments</h4>
				<fieldset class="mt-3">

					<% if(gameplatform.getComments() == null || gameplatform.getComments().size() == 0) { %>

					<p class="my-2 font-italic">This game doesn't have comments yet</p>

					<% } else { 
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
															if ( principalUser != null
																	&& principalUser.getAuthorities().contains(new SimpleGrantedAuthority("Admin")) || principalUser != null
															&& principalUser.getAuthorities().contains(new SimpleGrantedAuthority("Moderator"))) {
														%>

									<div class="float-right">
										<button type="button" class="btn btn-danger eliminarComment"
											data-id="<%=gameplatform.getComments().get(i).getId()%>">
											<span class="fa fa-trash text-white"></span>
										</button>
									</div>

									<%		} 
													} %>

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
				</fieldset>
				<form class="needs-validation mt-4" action="./games" method="post"
					novalidate>
					<%
													if (gameplatform != null) {
												%>
					<input type="hidden" name="id" value="<%=gameplatform.getId()%>" />
					<input type="hidden" name="admin" value="admin" />
					<%
													}
												%>
					<div class="form-group my-2">
						<label for="comment" class="font-weight-bold">Post a
							comment:</label>
						<textarea class="form-control" id="comment" name="comment" rows="7"
							placeholder="Write something here..." minlength="5"
							maxlength="120" required></textarea>
					</div>
					<button type="submit" class="btn btn-lg btn-danger my-3"
						id="submitComment">Post</button>
				</form>
			</div>

			<% } %>

		</div>
		<div class="col-md-2"></div>
	</div>
	<jsp:include page="layout/footer.jsp" />
	<script>
		window.setTimeout(function() {
			$("#alertDuplicatedGamePlatform").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("duplicatedGamePlatform");%>
		window.setTimeout(function() {
			$("#alertDuplicatedGame").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("duplicatedGame");%>	
		window.setTimeout(function() {
			$("#alertInsertedGame").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("insertedGame");%>
		window.setTimeout(function() {
			$("#alertUpdatedGame").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("updatedGame");%>	
		window.setTimeout(function() {
			$("#alertPostedVote").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("postedVote");%>	
		(
				function() {
					'use strict';
					window.addEventListener('load', function() {
						// Fetch all the forms we want to apply custom Bootstrap validation styles to
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										form.classList.add('was-validated');
									}, false);
								});
					}, false);
				})();
	</script>
	<script>
    setInterval(function(){ 
		$.ajax({
		    url: './onlineusers',
		    type: 'GET',
		    success: function(data){
		    	$("#onlineusers").text(data);
		    }
		})
    }, 5000);
	
		$(document)
				.ready(
						function() {
							$("#image")
									.fileinput(
											{
												initialPreview : [ 
													
													<%
														if(images != null && images.size()>0){
															
															for(Image image : images){																
													%>
													
													'<img src=\'data:image/png;base64, <%=image.getImageParsed() %>\' style="width:200px; max-height:150px" class=\'file-preview-image\' alt=\'Car Image\' title=\'Car Image\'>',		
													
													<%}

}%>

													
												],
												initialPreviewConfig: [
													
													<%if (images != null && images.size() > 0) {

	for (Image image : images) {%>
													{
													'caption': '<%=image.getName()%>',
													width: '120px',
													key: <%=image.getId()%>,
													
													},
													
													<%}

}%>
													
												],
												initialPreviewAsData : false,
												deleteUrl : "./games",
												overwriteInitial : false,
												maxFileSize : 1000,
												browseClass : "btn btn-danger",
												browseLabel : "Pick Images",
												browseIcon : "<i class=\"glyphicon glyphicon-picture\"></i> ",
												allowedFileTypes : ['image'],
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
	</script>
</body>
</html>