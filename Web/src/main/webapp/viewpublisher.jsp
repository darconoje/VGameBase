<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.vgamebase.model.Publisher"%>
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
			
				Publisher publisher = (Publisher) request.getAttribute("publisher");
				String name = "";
				if(publisher != null){
					name = publisher.getName();
				}

			%>
	<div class="alert alert-danger my-4 text-center"
		id="alertDuplicatedPublisher" role="alert"
		<%String duplicatedPublisher = (String) session.getAttribute("duplicatedPublisher");%>
		<%if (duplicatedPublisher == null || duplicatedPublisher.equals("null") || duplicatedPublisher.equals("")) {%>
		hidden="true" <%}%>>Duplicated publisher, try with another name</div>

	<div class="alert alert-success my-4 text-center"
		id="alertInsertedPublisher" role="alert"
		<%String insertedPublisher = (String) session.getAttribute("insertedPublisher");%>
		<%if (insertedPublisher == null || insertedPublisher.equals("null") || insertedPublisher.equals("")) {%>
		hidden="true" <%}%>>Publisher added successfully!</div>
		
	<div class="alert alert-success my-4 text-center"
		id="alertUpdatedPublisher" role="alert"
		<%String updatedPublisher = (String) session.getAttribute("updatedPublisher");%>
		<%if (updatedPublisher == null || updatedPublisher.equals("null") || updatedPublisher.equals("")) {%>
		hidden="true" <%}%>>Publisher updated successfully!</div>			

	<div class="row mt-5" style="min-height: 100%">
		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto">
			<h1 class="mt-5 mb-5 font-weight-bold text-white text-center">
				<u><% if(publisher == null){ %>Add Publisher<% }else{ %>Update Publisher<% } %></u>
			</h1>
			<form
				class="mt-5 mb-3 col-12 mt-3 mb-2 bg-white p-4 border border-light rounded-lg needs-validation"
				action="./admin" method="post" novalidate>

				<input type="hidden" name="view" value="publisher" />
				<% if(publisher!=null){ %>
				<input type="hidden" name="id" value="<%=publisher.getId()%>" /> 
				<% } %>					

				<h4 class="font-weight-bold">Publisher Details</h4>
				<fieldset class="form-group mt-3">
					<div class="form-group">
						<label class="mt-2 col-form-label font-weight-bold" for="name">Publisher
							Name:</label> <input type="text" class="form-control mb-2 col-4"
							id="name" name="name" <% if(publisher!=null){ %> value="<%=name %>"<%}%> required>
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
			$("#alertDuplicatedPublisher").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("duplicatedPublisher");%>
		window.setTimeout(function() {
			$("#alertInsertedPublisher").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("insertedPublisher");%>
		window.setTimeout(function() {
			$("#alertUpdatedPublisher").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("updatedPublisher");%>	
		
	</script>
</body>
</html>