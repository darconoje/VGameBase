<%@page import="java.nio.charset.StandardCharsets"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layout/head.jsp" />
<link rel="stylesheet" href="css/registration.css">
</head>
<body class="bg-dark">
	<jsp:include page="layout/header.jsp" />

	<div class="alert alert-danger my-4 text-center" id="alertCaptcha"
		role="alert"
		<%String captcha = (String) session.getAttribute("captcha");%>
		<%if (captcha == null || captcha.equals("null") || captcha.equals("")) {%>
		hidden="true" <%}%>>Wrong captcha, try again</div>

	<div class="alert alert-danger my-4 text-center"
		id="alertTakenUsername" role="alert"
		<%String takenUsername = (String) session.getAttribute("takenUsername");%>
		<%if (takenUsername == null || takenUsername.equals("null") || takenUsername.equals("")) {%>
		hidden="true" <%}%>>Username already in use, please try with
		another one</div>

	<div class="alert alert-danger my-4 text-center" id="alertTakenEmail"
		role="alert"
		<%String takenEmail = (String) session.getAttribute("takenEmail");%>
		<%if (takenEmail == null || takenEmail.equals("null") || takenEmail.equals("")) {%>
		hidden="true" <%}%>>Email already in use, please try with
		another one</div>

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
							<form class="needs-validation" action="./registration"
								method="post" novalidate>
								<h3 class="text-center mb-5 font-weight-bold">SIGN UP</h3>
								<label for="username">Username:</label>
								<div class="input-group mb-3">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fas fa-user"></i></span>
									</div>
									<input type="text" name="username" id="username"
										class="form-control input_user" value="" placeholder=""
										maxlength="15" required>
									<div class="valid-tooltip">Looks good!</div>
									<div class="invalid-tooltip">This field must be filled
										and contain less than 15 characters</div>
								</div>
								<label for="email">Email:</label>
								<div class="input-group mb-3">
									<div class="input-group-append">
										<span class="input-group-text"><i
											class="fas fa-envelope"></i></span>
									</div>
									<input type="email" name="email" id="email"
										class="form-control input_user" value="" placeholder=""
										required>	
									<div class="valid-tooltip">Looks good!</div>
									<div class="invalid-tooltip">You must fill this field
										with a valid email</div>
								</div>
								<label for="password">Password:</label>
								<div class="input-group mb-3">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fas fa-key"></i></span>
									</div>
									<input type="password" name="password" id="password"
										class="form-control input_pass" value="" placeholder=""
										minlength="8" required>
									<div class="valid-tooltip">Looks good!</div>
									<div class="invalid-tooltip">This field must be filled
										and contain more than 8 characters</div>
								</div>
								<label for="passwordRepeat">Repeat password:</label>
								<div class="input-group mb-3">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fas fa-key"></i></span>
									</div>
									<input type="password" name="passwordRepeat" id="passwordRepeat"
										class="form-control input_pass" value="" placeholder=""
										minlength="8" required>
									<div class="valid-tooltip">Looks good!</div>
									<div class="invalid-tooltip">Doesn't match with password
										field</div>
								</div>
								<h4 class="text-center mb-5 font-weight-bold mt-5">Captcha</h4>
								<div class="form-row ml-2 my-3">
									<img id="captcha" src="./captcha-image.jpg" />
								</div>
								<label for="passwordRepeat">Captcha Answer:</label>
								<div class="input-group mb-3">
									<div class="input-group-append">
										<span class="input-group-text"><i
											class="fas fa-barcode"></i></span>
									</div>
									<input type="text" name="answer" id="answer"
										class="form-control input_user" value="" placeholder=""
										required>
									<div class="invalid-tooltip">You must fill this field</div>
								</div>
								<div class="d-flex justify-content-center mt-3 login_container">
									<button type="submit" name="submit" id="submit" class="btn login_btn">Sign
										Up</button>
								</div>
							</form>
						</div>

						<div class="mt-5">
							<div class="d-flex justify-content-center links text-white">
								Already have an account? <a href="./login"
									class="ml-2 text-white font-weight-bold">Log In</a>
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
	
		$("#password").change(function() {
			var password = $("#password").val();
			$("#passwordRepeat").attr("pattern", '^' + password + '$');
		});

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

		window.setTimeout(function() {
			$("#alertCaptcha").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("captcha");%>
		window.setTimeout(function() {
			$("#alertTakenUsername").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("takenUsername");%>
		window.setTimeout(function() {
			$("#alertTakenEmail").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("takenEmail");%>
		
	</script>
</body>
</html>