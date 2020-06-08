<%@page import="java.nio.charset.StandardCharsets"%>
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
</head>
<body class="bg-dark">
	<jsp:include page="layout/header.jsp" />
	<div class="row mt-5" style="min-height: 100%">
		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto">
			<div class="mt-5 mb-5 col-12 mt-3 mb-2 bg-white p-4 container1">
				<div class="container">
					<div class="jumbotron bg-danger mt-4 rounded-pill p-4">
						<h1 class="text-center text-white font-weight-bold">Game Name
							Text</h1>
					</div>
					<div class="container mb-5">
						<div class="container mb-5">
							<div class="row">
								<div class="col-5"></div>
								<div
									class="col-2 border border-dark d-flex justify-content-around">
									<div class="ml-3">
										<span class="text-success">0</span>
										<button class="btn btn-lg">
											<span class="glyphicon glyphicon-thumbs-up text-success"></span>
										</button>
									</div>
									<div class="ml-2">
										<span class="text-danger">0</span>
										<button class="btn btn-lg">
											<span class="glyphicon glyphicon-thumbs-down text-danger"></span>
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
										<span><i class="glyphicon glyphicon-tower text-white"></i></span> Genre
									</div>
									<div class="card-body">
										<h5 class="card-title mt-3">Game Genre</h5>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i class="glyphicon glyphicon-briefcase text-white"></i></span>
										Publisher
									</div>
									<div class="card-body">
										<h5 class="card-title mt-3">Game Publisher</h5>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
						</div>
						<div class="row mt-5">
							<div class="col-sm-6">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i class="glyphicon glyphicon-calendar text-white"></i></span>
										Release Year
									</div>
									<div class="card-body">
										<h5 class="card-title mt-3">Game Release Year</h5>
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
										<h5 class="card-title mt-3">Game Platform</h5>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
						</div>
						<div class="row mt-5">
							<div class="col-sm-12">
								<div class="card text-center">
									<div class="card-header text-white">
										<span><i class="fa fa-globe text-white"></i></span> Region Sales (in
										millions)
									</div>
									<div class="card-body">
										<div class="row">
											<div class="col-sm-6">
												<h5 class="card-title mt-3">
													<span class="font-weight-bold">Region name:</span> Region
													Sales
												</h5>
											</div>
											<div class="col-sm-6">
												<h5 class="card-title mt-3">
													<span class="font-weight-bold">Region name:</span> Region
													sales
												</h5>
											</div>
										</div>
										<div class="row mt-4">
											<div class="col-sm-6">
												<h5 class="card-title mt-3">
													<span class="font-weight-bold">Region name:</span> Region
													Sales
												</h5>
											</div>
											<div class="col-sm-6">
												<h5 class="card-title mt-3">
													<span class="font-weight-bold">Region name:</span> Region
													sales
												</h5>
											</div>
										</div>
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
										<div class="row">
											<div class="col-sm-12">
												<h5 class="card-title mt-3 font-italic text-center">This
													game doesn't have images yet</h5>
											</div>
										</div>
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
										<span><i class="fa fa-comments text-white"></i></span> Comments
									</div>
									<div class="card-body">
										<div class="row">
											<div class="col-sm-12">
												<h5 class="card-title mt-3 font-italic text-center">This
													game doesn't have comments yet</h5>
											</div>
										</div>
										<div class="row d-flex justify-content-center">
											<form class="needs-validation mt-4 col-4">
												<div class="form-group my-2">
													<label for="comment" class="font-weight-bold">Post
														a comment:</label>
													<textarea class="form-control" id="comment" rows="7"
														placeholder="Write something here..." minlength="5"
														maxlength="120" required></textarea>
												</div>
												<button type="submit" class="btn btn-lg btn-danger my-3"
													id="submitComment"><span class="fa fa-paper-plane font-white mr-2"></span> Send comment</button>
											</form>
										</div>
									</div>
									<div class="card-footer text-muted"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
	<jsp:include page="layout/footer.jsp" />
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
	</script>
</body>
</html>