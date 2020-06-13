<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.vgamebase.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layout/head.jsp" />
<link rel="stylesheet" href="css/user.css">
</head>
<body class="bg-dark">
	<%
		User user = (User) session.getAttribute("user");
	%>
	<jsp:include page="layout/header.jsp" />
	<div class="row" style="min-height: 100%; margin-bottom: 50px;">
		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto mb-3">
			<h1 class="mt-5 mb-5 font-weight-bold text-white text-center">
				<u>User Profile</u>
			</h1>
			<div class="row">
				<div class="col-sm-12">
					<div class="card text-center">
						<div class="card-header text-white"></div>
						<div class="card-body">
							<div class="row p-5">
								<div class="col-sm-3 border-bottom border-dark"></div>
								<div class="col-sm-6 border-bottom border-dark pb-5">
									<div class="my-5">
										<img src="img/avatar.png" class="avatar mr-4"
											alt="user profile image"
											style="max-width: 130px; max-height: 130px">
									</div>

									<ul class="list-group list-group-flush my-3 align-self-center">
										<li class="list-group-item"><span
											class="font-weight-bold">Username: </span><%=user.getUserName()%></li>
										<li class="list-group-item"><span
											class="font-weight-bold">Email: </span><%=user.getEmail()%></li>
										<li class="list-group-item"><span
											class="font-weight-bold">Comments: </span><%=user.getComments().size()%></li>
										<li class="list-group-item"><span
											class="font-weight-bold">Votes: </span><%=user.getVotes().size()%></li>																						
										<li class="list-group-item"><span
											class="font-weight-bold">Type: </span><%=user.getUserProfile().getType()%></li>
									</ul>

								</div>
								<div class="col-sm-3 border-bottom border-dark"></div>
							</div>
							<div class="row my-5">
								<div class="col-sm-6 d-flex justify-content-center">
									<div
										class="col-sm-8 commentsList d-flex justify-content-center">
										<div class="commentsListInner align-self-center">
											<h3 class="text-center font-weight-bold">
												<u>Comments List</u>
											</h3>
											<%
												if (user.getComments().size() == 0) {
											%>
											<p class="mt-5 font-italic">You haven't posted comments
												yet</p>
											<%
												} else {
												for (int i = 0; i < user.getComments().size(); i++) {
											%>

											<p class="my-4 mx-2">
												<span class="font-italic"><%=user.getComments().get(i).getText()%></span>
												<span class="font-weight-bold">in </span><a class="font-weight-bold" href="./games?view=game&id=<%=user.getComments().get(i).getGame().getId()%>"><%=user.getComments().get(i).getGame().getGamepublisher().getGame().getName()%> for <%=user.getComments().get(i).getGame().getPlatform().getName()%></a>
											</p>

											<%
												}
											}
											%>
										</div>
									</div>
								</div>
								<div class="col-sm-6 d-flex justify-content-center">
									<div class="col-sm-8 votesList d-flex justify-content-center">
										<div class="votesListInner align-self-center">
											<h3 class="text-center font-weight-bold">
												<u>Votes List</u>
											</h3>
											<%
												if (user.getComments().size() == 0) {
											%>
											<p class="mt-5 font-italic">You haven't posted votes yet</p>
											<%
												} else {
												for (int i = 0; i < user.getVotes().size(); i++) {
											%>

											<p class="my-4 mx-2">
											<% if(user.getVotes().get(i).getVote().equals("like")){ %>
											<span class="text-success font-weight-bold">Liked</span> <a class="font-weight-bold" href="./games?view=game&id=<%=user.getVotes().get(i).getGame().getId()%>"><%=user.getVotes().get(i).getGame().getGamepublisher().getGame().getName()%> for <%=user.getVotes().get(i).getGame().getPlatform().getName()%></a>
											<% } else { %>
											<span class="text-danger font-weight-bold">Disliked</span> <a class="font-weight-bold" href="./games?view=game&id=<%=user.getVotes().get(i).getGame().getId()%>"><%=user.getVotes().get(i).getGame().getGamepublisher().getGame().getName()%> for <%=user.getVotes().get(i).getGame().getPlatform().getName()%></a>
											<% } %>
											</p>
											<%
												}
											}
											%>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer text-muted"></div>
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
	</script>
</body>
</html>