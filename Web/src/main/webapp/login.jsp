<%@page import="java.nio.charset.StandardCharsets"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layout/head.jsp" />
<link rel="stylesheet" href="css/login.css">
</head>
<body class="bg-dark">
	<jsp:include page="layout/header.jsp" />
	<div class="row" style="min-height: 100%">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<div class="container h-100">
				<div class="d-flex justify-content-center h-100">
					<div class="user_card">
						<div class="d-flex justify-content-center">
							<div class="brand_logo_container">
								<img src="img/VGameBase.png" class="brand_logo" alt="Logo">
							</div>
						</div>

						<div class="d-flex justify-content-center form_container">
							<form class="needs-validation" action="./login"
								method="post" novalidate>
								<h3 class="text-center mb-3 font-weight-bold">LOGIN</h3>
								<label for="username">Username:</label>
								<div class="input-group mb-3">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fas fa-user"></i></span>
									</div>
									<input type="text" name="username" id="username" class="form-control input_user"
										value="" required>
								</div>
								<label for="password">Password:</label>
								<div class="input-group mb-2">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fas fa-key"></i></span>
									</div>
									<input type="password" name="password" id="password" class="form-control input_pass"
										value="" required>
								</div>
								<div class="d-flex justify-content-center mt-3 login_container">
									<button type="button" name="button" class="btn login_btn">Login</button>
								</div>
							</form>
						</div>

						<div class="mt-5">
							<div class="d-flex justify-content-center links text-white">
								Don't have an account? <a href="./registration"
									class="ml-2 text-white font-weight-bold">Sign Up</a>
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
	(function() {
		'use strict';
		window.addEventListener('load',
				function() {
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