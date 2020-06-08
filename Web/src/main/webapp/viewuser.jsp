<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.vgamebase.model.UserProfile"%>
<%@page import="com.vgamebase.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layout/head.jsp" />
</head>
<body class="bg-dark">
	<jsp:include page="layout/header.jsp" />
			<%
			
				User user = (User) request.getAttribute("user");
				String username = "";
				String email = "";
				String profile = "";
				if(user != null){
					username = user.getUserName();
					email = user.getEmail();
					profile = user.getUserProfile().getType();
				}

			%>	
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

	<div class="alert alert-success my-4 text-center"
		id="alertInsertedUser" role="alert"
		<%String insertedUser = (String) session.getAttribute("insertedUser");%>
		<%if (insertedUser == null || insertedUser.equals("null") || insertedUser.equals("")) {%>
		hidden="true" <%}%>>User added successfully!</div>
		
	<div class="alert alert-success my-4 text-center"
		id="alertUpdatedUser" role="alert"
		<%String updatedUser = (String) session.getAttribute("updatedUser");%>
		<%if (updatedUser == null || updatedUser.equals("null") || updatedUser.equals("")) {%>
		hidden="true" <%}%>>User updated successfully!</div>		
	<div class="row mt-5" style="min-height: 100%">

		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto">
			<h1 class="mt-5 mb-5 font-weight-bold text-white text-center">
				<u><% if(user == null){ %>Add User<% }else{ %>Update User<% } %></u>
			</h1>
			<form
				class="mt-5 mb-3 col-12 mt-3 mb-2 bg-white p-4 border border-light rounded-lg needs-validation"
				action="./admin" method="post" novalidate>

				<input type="hidden" name="view" value="user" />
				<% if(user!=null){ %>
				<input type="hidden" name="id" value="<%=user.getId()%>" /> 
				<% } %>

				<h4 class="font-weight-bold">User Details</h4>
				<fieldset class="form-group mt-3">
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold" for="username">Username:</label>
						<input type="text" class="form-control mb-2 col-4" id="username" <% if(user!=null){ %>
							value="<%=username%>"<%} %>name="username" maxlength="15" required>
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">This field must be filled and
							contain less than 15 characters</div>
					</div>
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold" for="email">Email:</label>
						<input type="email" class="form-control mb-2 col-4" id="email" <% if(user!=null){ %>
							value="<%=email%>"<%} %>name="email" required>
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">You must fill this field with
							a valid email</div>
					</div>
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold" for="password">Password:</label>
						<input type="password" class="form-control mb-2 col-4"
							id="password" name="password" minlength="8" <% if(user!=null && user.getId()==1) { %> disabled <% } %> required>
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">This field must be filled and
							contain more than 8 characters</div>
					</div>
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold"
							for="passwordRepeat">Repeat Password:</label> <input
							type="password" class="form-control mb-2 col-4"
							id="passwordRepeat" name="passwordRepeat" minlength="8" <% if(user!=null && user.getId()==1) { %> disabled <% } %> required>
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">Doesn't match with password
							field</div>
					</div>
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold"
							for="userProfile">User Profile:</label>
						<div class="form-row ml-2">
							<select class="custom-select my-1 col-4" id="userProfile"
								name="userProfile" <% if(user!=null && user.getId()==1) { %> disabled <% } %> required>
								<option value="">Choose...</option>
								<%
									List<UserProfile> userProfiles = (List<UserProfile>) request.getAttribute("userProfiles");

								if (userProfiles != null) {

									for (UserProfile p : userProfiles) {
										String profileString = p.getType();
										long profileId = p.getId();
								%>
								<option value="<%=profileId%>" <% if(user!=null && profileString.equals(profile)){ %>selected<%} %>><%=profileString%></option>
								<%
									}

								}
								%>
							</select>
						</div>
					</div>
				</fieldset>
				<button type="submit" class="btn btn-lg btn-danger my-3" id="submit">Submit</button>
				<a href="./admin" class="ml-3 btn btn-danger btn-lg" role="button"
					aria-disabled="true">Back</a>
			</form>
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
			$("#alertTakenUsername").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("takenUsername");%>
		window.setTimeout(function() {
			$("#alertTakenEmail").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("takenEmail");%>
		window.setTimeout(function() {
			$("#alertInsertedUser").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("insertedUser");%>
		window.setTimeout(function() {
			$("#alertUpdatedUser").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("updatedUser");%>	
	
	<% if(user!=null){ %>
	
	if($("#password").val().length == 0){
		$("#password").prop('required',false);
		$("#passwordRepeat").prop('required',false);
	}else{
		$("#password").prop('required',true);
		$("#passwordRepeat").prop('required',true);
	}
	
	$("#password").change(function() {
		if($("#password").val().length == 0){
			$("#password").prop('required',false);
			$("#passwordRepeat").prop('required',false);
		}else{
			$("#password").prop('required',true);
			$("#passwordRepeat").prop('required',true);			
		}
	});
	
	<%}%>
		
	</script>
</body>
</html>