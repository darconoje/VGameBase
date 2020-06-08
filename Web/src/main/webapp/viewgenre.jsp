<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.vgamebase.model.Genre"%>
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
			
				Genre genre = (Genre) request.getAttribute("genre");
				String name = "";
				if(genre != null){
					name = genre.getName();
				}

			%>	
	<div class="alert alert-danger my-4 text-center"
		id="alertDuplicatedGenre" role="alert"
		<%String duplicatedGenre = (String) session.getAttribute("duplicatedGenre");%>
		<%if (duplicatedGenre == null || duplicatedGenre.equals("null") || duplicatedGenre.equals("")) {%>
		hidden="true" <%}%>>Duplicated genre, try with another name</div>
		
	<div class="alert alert-success my-4 text-center"
		id="alertInsertedGenre" role="alert"
		<%String insertedGenre = (String) session.getAttribute("insertedGenre");%>
		<%if (insertedGenre == null || insertedGenre.equals("null") || insertedGenre.equals("")) {%>
		hidden="true" <%}%>>Genre added successfully!</div>
		
	<div class="alert alert-success my-4 text-center"
		id="alertUpdatedGenre" role="alert"
		<%String updatedGenre = (String) session.getAttribute("updatedGenre");%>
		<%if (updatedGenre == null || updatedGenre.equals("null") || updatedGenre.equals("")) {%>
		hidden="true" <%}%>>Genre updated successfully!</div>					

	<div class="row mt-5" style="min-height: 100%">
		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto">
			<h1 class="mt-5 mb-5 font-weight-bold text-white text-center">
				<u><% if(genre == null){ %>Add Genre<% }else{ %>Update Genre<% } %></u>
			</h1>
			<form
				class="mt-5 mb-3 col-12 mt-3 mb-2 bg-white p-4 border border-light rounded-lg needs-validation"
				action="./admin" method="post" novalidate>

				<input type="hidden" name="view" value="genre" />
				<% if(genre!=null){ %>
				<input type="hidden" name="id" value="<%=genre.getId()%>" /> 
				<% } %>

				<h4 class="font-weight-bold">Genre Details</h4>
				<fieldset class="form-group mt-3">
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold" for="name">Genre
							Name:</label> <input type="text" class="form-control mb-2 col-4"
							id="name" name="name" <% if(genre!=null){ %> value="<%=name %>"<%}%> required>
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">This field must be filled</div>
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
			$("#alertDuplicatedGenre").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("duplicatedGenre");%>
		window.setTimeout(function() {
			$("#alertInsertedGenre").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("insertedGenre");%>
		window.setTimeout(function() {
			$("#alertUpdatedGenre").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("updatedGenre");%>	
		
	</script>
</body>
</html>