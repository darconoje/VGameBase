<%@page import="java.nio.charset.StandardCharsets"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layout/head.jsp" />
<link href="//cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script src="//cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/spinner.css">
</head>
<body class="bg-dark">
	<div class="spinner w-100 h-100 d-flex justify-content-center align-items-center">
    <div class="row spinner-content">
        <div class="col-md-12">
            <div class="loader">
                <div class="loader-inner">
                    <div class="loading one"></div>
                </div>
                <div class="loader-inner">
                    <div class="loading two"></div>
                </div>
                <div class="loader-inner">
                    <div class="loading three"></div>
                </div>
                <div class="loader-inner">
                    <div class="loading four"></div>
                </div>
            </div>
        </div>
    </div>
</div>
	<jsp:include page="layout/header.jsp" />
	<div class="alert alert-danger my-4 text-center"
		id="alertInvalidFileExtension" role="alert"
		<%String invalidFileExtension = (String) session.getAttribute("invalidFileExtension");%>
		<%if (invalidFileExtension == null || invalidFileExtension.equals("null") || invalidFileExtension.equals("")) {%>
		hidden="true" <%}%>>Invalid file extension, it must use a JSON
		format</div>
	<div class="alert alert-danger my-4 text-center"
		id="alertInvalidJSONFormat" role="alert"
		<%String invalidJSONFormat = (String) session.getAttribute("invalidJSONFormat");%>
		<%if (invalidJSONFormat == null || invalidJSONFormat.equals("null") || invalidJSONFormat.equals("")) {%>
		hidden="true" <%}%>>JSON file provided has invalid format,
		please try again</div>
	<div class="alert alert-success my-4 text-center"
		id="alertPopulatedTable" role="alert"
		<%String populatedTable = (String) session.getAttribute("populatedTable");%>
		<%if (populatedTable == null || populatedTable.equals("null") || populatedTable.equals("")) {%>
		hidden="true" <%}%>>Table populated successfully!</div>
	<div class="alert alert-success my-4 text-center"
		id="alertDeletedUser" role="alert"
		<%String deletedUser = (String) session.getAttribute("deletedUser");%>
		<%if (deletedUser == null || deletedUser.equals("null") || deletedUser.equals("")) {%>
		hidden="true" <%}%>>User deleted successfully!</div>		
	<div class="alert alert-success my-4 text-center"
		id="alertDeletedGenre" role="alert"
		<%String deletedGenre = (String) session.getAttribute("deletedGenre");%>
		<%if (deletedGenre == null || deletedGenre.equals("null") || deletedGenre.equals("")) {%>
		hidden="true" <%}%>>Genre deleted successfully!</div>
	<div class="alert alert-success my-4 text-center"
		id="alertDeletedPublisher" role="alert"
		<%String deletedPublisher = (String) session.getAttribute("deletedPublisher");%>
		<%if (deletedPublisher == null || deletedPublisher.equals("null") || deletedPublisher.equals("")) {%>
		hidden="true" <%}%>>Publisher deleted successfully!</div>			
	<div class="alert alert-success my-4 text-center"
		id="alertDeletedPlatform" role="alert"
		<%String deletedPlatform = (String) session.getAttribute("deletedPlatform");%>
		<%if (deletedPlatform == null || deletedPlatform.equals("null") || deletedPlatform.equals("")) {%>
		hidden="true" <%}%>>Platform deleted successfully!</div>			
	<div class="alert alert-success my-4 text-center"
		id="alertDeletedRegion" role="alert"
		<%String deletedRegion = (String) session.getAttribute("deletedRegion");%>
		<%if (deletedRegion == null || deletedRegion.equals("null") || deletedRegion.equals("")) {%>
		hidden="true" <%}%>>Region deleted successfully!</div>		
	<div class="alert alert-danger my-4 text-center"
		id="alertDeleteUndefined" role="alert"
		<%String deleteUndefined = (String) session.getAttribute("deleteUndefined");%>
		<%if (deleteUndefined == null || deleteUndefined.equals("null") || deleteUndefined.equals("")) {%>
		hidden="true" <%}%>>You can't delete Undefined if it's not empty</div>			
	<div class="row" style="min-height: 100%">
		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto mb-3">

			<h1 class="mt-5 mb-3 font-weight-bold text-white text-center">
				<u>Admin Dashboard</u>
			</h1>
			<div
				class="nav nav-pills  bg-white mt-5 border rounded-lg mx-auto d-flex justify-content-between p-2"
				id="v-pills-tab" role="tablist" aria-orientation="vertical">
				<a class="nav-link active font-weight-bold" id="pills-users-tab"
					data-toggle="pill" href="#pills-users" role="tab"
					aria-controls="pills-users" aria-selected="true">Users List</a> <a
					class="nav-link font-weight-bold" id="pills-genres-tab"
					data-toggle="pill" href="#pills-genres" role="tab"
					aria-controls="pills-genres" aria-selected="false">Genres List</a>
				<a class="nav-link font-weight-bold" id="pills-publishers-tab"
					data-toggle="pill" href="#pills-publishers" role="tab"
					aria-controls="pills-publishers" aria-selected="false">Publishers
					List</a> <a class="nav-link font-weight-bold" id="pills-platforms-tab"
					data-toggle="pill" href="#pills-platforms" role="tab"
					aria-controls="pills-platforms" aria-selected="false">Platforms
					List</a> <a class="nav-link font-weight-bold" id="pills-regions-tab"
					data-toggle="pill" href="#pills-regions" role="tab"
					aria-controls="pills-regions" aria-selected="false">Regions
					List</a> <a class="nav-link font-weight-bold" id="pills-populate-tab"
					data-toggle="pill" href="#pills-populate" role="tab"
					aria-controls="pills-populate" aria-selected="false">Populate
					Database</a>
			</div>
			<div class="tab-content" id="pills-tabContent">
				<div class="tab-pane fade show active mb-3" id="pills-users"
					role="tabpanel" aria-labelledby="pills-users-tab">
					<h2 class="mt-5 mb-3 ml-3 font-weight-bold text-white">Users
						List</h2>
					<div>
						<div
							class="col-12 mt-3 mb-2 table-responsive bg-white p-4 border border-light rounded-lg">
							<table id="usersTable"
								class="table table-hover table-striped table-bordered border border-dark rounded-lg"
								style="width: 100%">
								<thead class="bg-danger">
								</thead>
								<tbody>
								</tbody>
								<tfoot class="bg-danger">
									<tr>
										<th scope="col"></th>
										<th scope="col" class="dt-center"></th>
										<th scope="col"></th>
										<th scope="col"></th>
										<th scope="col"></th>
									</tr>
								</tfoot>
							</table>
							<a class="btn btn-lg btn-danger" href="./admin?view=user"
								role="button">Add User</a>
						</div>
					</div>
				</div>
				<div class="tab-pane fade show mb-3" id="pills-genres"
					role="tabpanel" aria-labelledby="pills-genres-tab">
					<h2 class="mt-5 mb-3 ml-3 font-weight-bold text-white">Genres
						List</h2>
					<div
						class="col-12 mt-3 mb-2 table-responsive bg-white p-4 border border-light rounded-lg">
						<table id="genresTable"
							class="table table-hover table-striped table-bordered border border-dark rounded-lg"
							style="width: 100%">
							<thead class="bg-danger">
							</thead>
							<tbody>
							</tbody>
							<tfoot class="bg-danger">
								<tr>
									<th scope="col"></th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</tfoot>
						</table>
						<a class="btn btn-lg btn-danger" href="./admin?view=genre"
							role="button">Add Genre</a>
					</div>
				</div>
				<div class="tab-pane fade show mb-3" id="pills-publishers"
					role="tabpanel" aria-labelledby="pills-publishers-tab">
					<h2 class="mt-5 mb-3 ml-3 font-weight-bold text-white">Publishers
						List</h2>
					<div
						class="col-12 mt-3 mb-2 table-responsive bg-white p-4 border border-light rounded-lg">
						<table id="publishersTable"
							class="table table-hover table-striped table-bordered border border-dark rounded-lg"
							style="width: 100%">
							<thead class="bg-danger">
							</thead>
							<tbody>
							</tbody>
							<tfoot class="bg-danger">
								<tr>
									<th scope="col"></th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</tfoot>
						</table>
						<a class="btn btn-lg btn-danger" href="./admin?view=publisher"
							role="button">Add Publisher</a>
					</div>
				</div>
				<div class="tab-pane fade show mb-3" id="pills-platforms"
					role="tabpanel" aria-labelledby="pills-platforms-tab">
					<h2 class="mt-5 mb-3 ml-3 font-weight-bold text-white">Platforms
						List</h2>
					<div
						class="col-12 mt-3 mb-2 table-responsive bg-white p-4 border border-light rounded-lg">
						<table id="platformsTable"
							class="table table-hover table-striped table-bordered border border-dark rounded-lg"
							style="width: 100%">
							<thead class="bg-danger">
							</thead>
							<tbody>
							</tbody>
							<tfoot class="bg-danger">
								<tr>
									<th scope="col"></th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</tfoot>
						</table>
						<a class="btn btn-lg btn-danger" href="./admin?view=platform"
							role="button">Add Platform</a>
					</div>
				</div>
				<div class="tab-pane fade show mb-3" id="pills-regions"
					role="tabpanel" aria-labelledby="pills-regions-tab">
					<h2 class="mt-5 mb-3 ml-3 font-weight-bold text-white">Regions
						List</h2>
					<div
						class="col-12 mt-3 mb-2 table-responsive bg-white p-4 border border-light rounded-lg">
						<table id="regionsTable"
							class="table table-hover table-striped table-bordered border border-dark rounded-lg"
							style="width: 100%">
							<thead class="bg-danger">
							</thead>
							<tbody>
							</tbody>
							<tfoot class="bg-danger">
								<tr>
									<th scope="col"></th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</tfoot>
						</table>
						<a class="btn btn-lg btn-danger" href="./admin?view=region"
							role="button">Add Region</a>
					</div>
				</div>
				<div class="tab-pane fade mb-3" id="pills-populate" role="tabpanel"
					aria-labelledby="pills-populate-tab">
					<h2 class="mt-5 mb-3 ml-3 font-weight-bold text-white">Populate
						Database</h2>
					<div class="bg-white p-4 border border-light rounded-lg mb-5">
						<div class="row ml-3">
							<form class="border border-dark rounded-lg m-2 p-2 col-8"
								action="./populate" method="post" enctype="multipart/form-data" id="populateGame">
								<div class="form-group">
									<label class="mt-2 col-form-label" for="game">Populate
										Table <span class="font-weight-bold">Game</span>
									</label> <input type="file"
										class="form-control mb-2 ml-3 col-4 pb-5 px-2 pt-3" id="game"
										name="game" accept=".json" required>
								</div>
								<button type="submit"
									class="btn btn-lg btn-danger ml-4  border border rounded-lg mb-2"
									id="submitGame">Populate!</button>
							</form>
						</div>
						<div class="row ml-3">
							<form class="border border-dark rounded-lg m-2 p-2 col-8"
								action="./populate" method="post" enctype="multipart/form-data" id="populateGenre">
								<div class="form-group">
									<label class="mt-2 col-form-label" for="genre">Populate
										Table <span class="font-weight-bold">Genre</span>
									</label> <input type="file"
										class="form-control mb-2 ml-3 col-4 pb-5 px-2 pt-3" id="genre"
										name="genre" accept=".json" required>
								</div>
								<button type="submit"
									class="btn btn-lg btn-danger ml-4  border border rounded-lg mb-2"
									id="submitGenre">Populate!</button>
							</form>
						</div>
						<div class="row ml-3">
							<form class="border border-dark rounded-lg m-2 p-2 col-8"
								action="./populate" method="post" enctype="multipart/form-data" id="populatePublisher">
								<div class="form-group">
									<label class="mt-2 col-form-label" for="publisher">Populate
										Table <span class="font-weight-bold">Publisher</span>
									</label> <input type="file"
										class="form-control mb-2 ml-3 col-4 pb-5 px-2 pt-3"
										id="publisher" name="publisher" accept=".json" required>
								</div>
								<button type="submit"
									class="btn btn-lg btn-danger ml-4  border border rounded-lg mb-2"
									id="submitPublisher">Populate!</button>
							</form>
						</div>
						<div class="row ml-3">
							<form class="border border-dark rounded-lg m-2 p-2 col-8"
								action="./populate" method="post" enctype="multipart/form-data" id="populatePlatform">
								<div class="form-group">
									<label class="mt-2 col-form-label" for="platform">Populate
										Table <span class="font-weight-bold">Platform</span>
									</label> <input type="file"
										class="form-control mb-2 ml-3 col-4 pb-5 px-2 pt-3"
										id="platform" name="platform" accept=".json" required>
								</div>
								<button type="submit"
									class="btn btn-lg btn-danger ml-4  border border rounded-lg mb-2"
									id="submitPlatform">Populate!</button>
							</form>
						</div>
						<div class="row ml-3">
							<form class="border border-dark rounded-lg m-2 p-2 col-8"
								action="./populate" method="post" enctype="multipart/form-data" id="populateGamePublisher">
								<div class="form-group">
									<label class="mt-2 col-form-label" for="game_publisher">Populate
										Table <span class="font-weight-bold">Game Publisher</span>
									</label> <input type="file"
										class="form-control mb-2 ml-3 col-4 pb-5 px-2 pt-3"
										id="game_publisher" name="game_publisher" accept=".json"
										required>
								</div>
								<button type="submit"
									class="btn btn-lg btn-danger ml-4  border border rounded-lg mb-2"
									id="submitGamePublisher">Populate!</button>
							</form>
						</div>
						<div class="row ml-3">
							<form class="border border-dark rounded-lg m-2 p-2 col-8"
								action="./populate" method="post" enctype="multipart/form-data" id="populateGamePlatform">
								<div class="form-group">
									<label class="mt-2 col-form-label" for="game_platform">Populate
										Table <span class="font-weight-bold">Game Platform</span>
									</label> <input type="file"
										class="form-control mb-2 ml-3 col-4 pb-5 px-2 pt-3"
										id="game_platform" name="game_platform" accept=".json"
										required>
								</div>
								<button type="submit"
									class="btn btn-lg btn-danger ml-4  border border rounded-lg mb-2"
									id="submitGamePlatform">Populate!</button>
							</form>
						</div>
						<div class="row ml-3">
							<form class="border border-dark rounded-lg m-2 p-2 col-8"
								action="./populate" method="post" enctype="multipart/form-data" id="populateRegion">
								<div class="form-group">
									<label class="mt-2 col-form-label" for="region">Populate
										Table <span class="font-weight-bold">Region</span>
									</label> <input type="file"
										class="form-control mb-2 ml-3 col-4 pb-5 px-2 pt-3"
										id="region" name="region" accept=".json" required>
								</div>
								<button type="submit"
									class="btn btn-lg btn-danger ml-4  border border rounded-lg mb-2"
									id="submitRegion">Populate!</button>
							</form>
						</div>
						<div class="row ml-3">
							<form class="border border-dark rounded-lg m-2 p-2 col-8"
								action="./populate" method="post" enctype="multipart/form-data" id="populateRegionSales">
								<div class="form-group">
									<label class="mt-2 col-form-label" for="region_sales">Populate
										Table <span class="font-weight-bold">Region Sales</span>
									</label> <input type="file"
										class="form-control mb-2 ml-3 col-4 pb-5 px-2 pt-3"
										id="region_sales" name="region_sales" accept=".json" required>
								</div>
								<button type="submit"
									class="btn btn-lg btn-danger ml-4  border border rounded-lg mb-2"
									id="submitRegionSales">Populate!</button>
							</form>
						</div>
						<div class="row ml-3">
							<form class="border border-dark rounded-lg m-2 p-2 col-8"
								action="./populate" method="post" enctype="multipart/form-data" id="populateUserProfile">
								<div class="form-group">
									<label class="mt-2 col-form-label" for="user_profile">Populate
										Table <span class="font-weight-bold">User Profile</span>
									</label> <input type="file"
										class="form-control mb-2 ml-3 col-4 pb-5 px-2 pt-3"
										id="user_profile" name="user_profile" accept=".json" required>
								</div>
								<button type="submit"
									class="btn btn-lg btn-danger ml-4  border border rounded-lg mb-2"
									id="submitUserProfile">Populate!</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
	<jsp:include page="layout/footer.jsp" />

	<!-- Modal -->
	<div class="modal fade" id="deleteUserModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="deleteUserModalTitle">Delete User</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Are you sure you want to delete the user <span
						class="font-weight-bold">#</span><span class="font-weight-bold"
						id="modal-userId"></span>?<br> This user will be set to inactive.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						id="modalBotonEliminarUser">Yes</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="deleteGenreModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="deleteGenreModalTitle">Delete
						Genre</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Are you sure you want to delete the genre <span
						class="font-weight-bold">#</span><span class="font-weight-bold"
						id="modal-genreId"></span>? <br> Genre of related games will
					be set to 'Undefined'.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						id="modalBotonEliminarGenre">Yes</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="deletePublisherModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="deletePublisherModalTitle">Delete
						Publisher</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Are you sure you want to delete the publisher <span
						class="font-weight-bold">#</span><span class="font-weight-bold"
						id="modal-publisherId"></span>?
					<br>
					Publisher of related game publishers will be set to 'Undefined'.						
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						id="modalBotonEliminarPublisher">Yes</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="deletePlatformModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="deletePlatformModalTitle">Delete
						Platform</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Are you sure you want to delete the platform <span
						class="font-weight-bold">#</span><span class="font-weight-bold"
						id="modal-platformId"></span>?
					<br>
					Platform of related game platforms will be set to 'Undefined'.						
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						id="modalBotonEliminarPlatform">Yes</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="deleteRegionModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="deleteRegionModalTitle">Delete
						Region</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Are you sure you want to delete the region <span
						class="font-weight-bold">#</span><span class="font-weight-bold"
						id="modal-regionId"></span>?
					<br>
					Region of related region sales will be set to 'Undefined'.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						id="modalBotonEliminarRegion">Yes</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
				</div>
			</div>
		</div>
	</div>
	


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
							
							$('#usersTable')
									.DataTable(
											{
												initComplete: function (settings,json) {
													this.api().columns(1).every( function () {
									                    var column = this;
									                    var select = $('<select><option value="true">Hide inactives</option><option value="false">Show inactives</option></select>')
									                        .appendTo( $(column.footer()))
									                        .on( 'change', function () {
									                            var val = $.fn.dataTable.util.escapeRegex(
									                                $(this).val()
									                            );
									     
									                            column
									                                .search( val ? val : '', true, false )
									                                .draw();
									                        } );

									                    
									                } );
												},
												"processing" : true,
												"serverSide" : true,
												"ajax" : {
													url : './adminUsersJSON',
													dataSrc : 'users'
												},
												"columns" : [ {
													data : "id"
												}, {
													data : "userName"
												}, {
													data : "email"
												}, {
													data : "userProfile.type"
												} ],
												"columnDefs" : [
														{
															"className" : "dt-center",
															"targets" : [ 4 ]
														},
														{
															"title" : "ID",
															'className' : 'font-weight-bold',
															"targets" : 0
														},
														{
															"title" : "Username",
															"targets" : 1
														},
														{
															"title" : "Email",
															"targets" : 2
														},
														{
															"title" : "Profile",
															"targets" : 3
														},
														{
															"title" : "Actions",
															"targets" : 4,
															"data" : 'id',
															"orderable" : false,
															"searchable" : false,
															"render" : function(
																	data, type,
																	row, meta) {
																if (data == 1) {
																	return '<a role="button" class="btn btn-danger botonVerUser" data-id='
																			+ data
																			+ ' href="./admin?view=user&id='
																			+ data
																			+ '\"><span class=\"glyphicon glyphicon-eye-open text-dark\"></span></a>'
																} else {
																	return '<a role="button" class="btn btn-danger botonVerUser" data-id='
																			+ data
																			+ ' href="./admin?view=user&id='
																			+ data
																			+ '\"><span class=\"glyphicon glyphicon-eye-open text-dark\"></span></a> <button class="btn btn-danger botonEliminar" data-id='+data+' data-toggle=\"modal\" data-target=\"#deleteUserModal\"><span class=\"glyphicon glyphicon-trash text-dark\"></span></button>'

																}
															}
														} ],
													    searchCols: [
													        null,
													        {'search': 'true' },
													        null,
													        null,
													        null
													      ]
											});

							$('#genresTable')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"ajax" : {
													url : './adminGenresJSON',
													dataSrc : 'genres'
												},
												"columns" : [ {
													data : "id"
												}, {
													data : "name"
												} ],
												"columnDefs" : [
														{
															"className" : "dt-center",
															"targets" : [ 2 ]
														},
														{
															"title" : "ID",
															'className' : 'font-weight-bold',
															"targets" : 0
														},
														{
															"title" : "Genre",
															"targets" : 1
														},
														{
															"title" : "Actions",
															"targets" : 2,
															"data" : 'id',
															"orderable" : false,
															"searchable" : false,
															"render" : function(
																	data, type,
																	row, meta) {
																return '<a role="button" class="btn btn-danger botonVerGenre" data-id='
																		+ data
																		+ ' href="./admin?view=genre&id='
																		+ data
																		+ '\"><span class=\"glyphicon glyphicon-eye-open text-dark\"></span></a> <button class="btn btn-danger botonEliminar" data-id='+data+' data-toggle=\"modal\" data-target=\"#deleteGenreModal\"><span class=\"glyphicon glyphicon-trash text-dark\"></span></button>'
															}
														} ]
											});

							$('#publishersTable')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"ajax" : {
													url : './adminPublishersJSON',
													dataSrc : 'publishers'
												},
												"columns" : [ {
													data : "id"
												}, {
													data : "name"
												} ],
												"columnDefs" : [
														{
															"className" : "dt-center",
															"targets" : [ 2 ]
														},
														{
															"title" : "ID",
															'className' : 'font-weight-bold',
															"targets" : 0
														},
														{
															"title" : "Publisher",
															"targets" : 1
														},
														{
															"title" : "Actions",
															"targets" : 2,
															"data" : 'id',
															"orderable" : false,
															"searchable" : false,
															"render" : function(
																	data, type,
																	row, meta) {
																return '<a role="button" class="btn btn-danger botonVerPublisher" data-id='
																		+ data
																		+ ' href="./admin?view=publisher&id='
																		+ data
																		+ '\"><span class=\"glyphicon glyphicon-eye-open text-dark\"></span></a> <button class="btn btn-danger botonEliminar" data-id='+data+' data-toggle=\"modal\" data-target=\"#deletePublisherModal\"><span class=\"glyphicon glyphicon-trash text-dark\"></span></button>'
															}
														} ]
											});

							$('#platformsTable')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"ajax" : {
													url : './adminPlatformsJSON',
													dataSrc : 'platforms'
												},
												"columns" : [ {
													data : "id"
												}, {
													data : "name"
												} ],
												"columnDefs" : [
														{
															"className" : "dt-center",
															"targets" : [ 2 ]
														},
														{
															"title" : "ID",
															'className' : 'font-weight-bold',
															"targets" : 0
														},
														{
															"title" : "Platform",
															"targets" : 1
														},
														{
															"title" : "Actions",
															"targets" : 2,
															"data" : 'id',
															"orderable" : false,
															"searchable" : false,
															"render" : function(
																	data, type,
																	row, meta) {
																return '<a role="button" class="btn btn-danger botonVerPlatform" data-id='
																		+ data
																		+ ' href="./admin?view=platform&id='
																		+ data
																		+ '\"><span class=\"glyphicon glyphicon-eye-open text-dark\"></span></a> <button class="btn btn-danger botonEliminar" data-id='+data+' data-toggle=\"modal\" data-target=\"#deletePlatformModal\"><span class=\"glyphicon glyphicon-trash text-dark\"></span></button>'
															}
														} ]
											});

							$('#regionsTable')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"ajax" : {
													url : './adminRegionsJSON',
													dataSrc : 'regions'
												},
												"columns" : [ {
													data : "id"
												}, {
													data : "name"
												} ],
												"columnDefs" : [
														{
															"className" : "dt-center",
															"targets" : [ 2 ]
														},
														{
															"title" : "ID",
															'className' : 'font-weight-bold',
															"targets" : 0
														},
														{
															"title" : "Region",
															"targets" : 1
														},
														{
															"title" : "Actions",
															"targets" : 2,
															"data" : 'id',
															"orderable" : false,
															"searchable" : false,
															"render" : function(
																	data, type,
																	row, meta) {
																return '<a role="button" class="btn btn-danger botonVerRegion" data-id='
																		+ data
																		+ ' href="./admin?view=region&id='
																		+ data
																		+ '\"><span class=\"glyphicon glyphicon-eye-open text-dark\"></span></a> <button class="btn btn-danger botonEliminar" data-id='+data+' data-toggle=\"modal\" data-target=\"#deleteRegionModal\"><span class=\"glyphicon glyphicon-trash text-dark\"></span></button>'
															}
														} ]
											});

						});
		window.setTimeout(function() {
			$("#alertInvalidFileExtension").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("invalidFileExtension");%>
		window.setTimeout(function() {
			$("#alertInvalidJSONFormat").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("invalidJSONFormat");%>
		window.setTimeout(function() {
			$("#alertPopulatedTable").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("populatedTable");%>
		window.setTimeout(function() {
			$("#alertDeletedUser").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("deletedUser");%>	
		window.setTimeout(function() {
			$("#alertDeletedGenre").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("deletedGenre");%>
		window.setTimeout(function() {
			$("#alertDeletedPublisher").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("deletedPublisher");%>		
		window.setTimeout(function() {
			$("#alertDeletedPlatform").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("deletedPlatform");%>		
		window.setTimeout(function() {
			$("#alertDeletedRegion").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("deletedRegion");%>	
		window.setTimeout(function() {
			$("#alertDeleteUndefined").fadeOut("slow");
		}, 3000);
	<%session.removeAttribute("deleteUndefined");%>		
		$('#regionsTable tbody').on('click', '.botonEliminar', function() {
			var id = $(this).attr("data-id");
			$("#modal-regionId").text(id);
		});

		$('#platformsTable tbody').on('click', '.botonEliminar', function() {
			var id = $(this).attr("data-id");
			$("#modal-platformId").text(id);
		});

		$('#publishersTable tbody').on('click', '.botonEliminar', function() {
			var id = $(this).attr("data-id");
			$("#modal-publisherId").text(id);
		});

		$('#genresTable tbody').on('click', '.botonEliminar', function() {
			var id = $(this).attr("data-id");
			$("#modal-genreId").text(id);
		});

		$('#usersTable tbody').on('click', '.botonEliminar', function() {
			var id = $(this).attr("data-id");
			$("#modal-userId").text(id);
		});
		
		$('#modalBotonEliminarUser').click(function() {
			$('#deleteUserModal').modal('toggle');
			$(".spinner").css('visibility', 'visible');
			var id = $('#modal-userId').text();
			$.ajax({
				type : "POST",
				url : "./admin?delete=user",
				data : {
					id : id
				},
				success : function(result) {
					location.reload();
					$(".spinner").css('visibility', 'hidden');
				}
			});
		});

		$('#modalBotonEliminarGenre').click(function() {
			$('#deleteGenreModal').modal('toggle');
			$(".spinner").css('visibility', 'visible');
			var id = $('#modal-genreId').text();
			$.ajax({
				type : "POST",
				url : "./admin?delete=genre",
				data : {
					id : id
				},
				success : function(result) {
					location.reload();
					$(".spinner").css('visibility', 'hidden');
				}
			});
		});
		
		$('#modalBotonEliminarPublisher').click(function() {
			$('#deletePublisherModal').modal('toggle');
			$(".spinner").css('visibility', 'visible');
			var id = $('#modal-publisherId').text();
			$.ajax({
				type : "POST",
				url : "./admin?delete=publisher",
				data : {
					id : id
				},
				success : function(result) {
					location.reload();
					$(".spinner").css('visibility', 'hidden');
				}
			});
		});	
		
		$('#modalBotonEliminarPlatform').click(function() {
			$('#deletePlatformModal').modal('toggle');
			$(".spinner").css('visibility', 'visible');
			var id = $('#modal-platformId').text();
			$.ajax({
				type : "POST",
				url : "./admin?delete=platform",
				data : {
					id : id
				},
				success : function(result) {
					location.reload();
					$(".spinner").css('visibility', 'hidden');
				}
			});
		});			
		
		$('#modalBotonEliminarRegion').click(function() {
			$('#deleteRegionModal').modal('toggle');
			$(".spinner").css('visibility', 'visible');
			var id = $('#modal-regionId').text();
			$.ajax({
				type : "POST",
				url : "./admin?delete=region",
				data : {
					id : id
				},
				success : function(result) {
					location.reload();
					$(".spinner").css('visibility', 'hidden');
				}
			});
		});		
		
		$(".spinner").css('visibility', 'hidden');
		
		$("#populateGame").submit(function () {
			$(".spinner").css('visibility', 'visible');
		});
		
		$("#populateGenre").submit(function () {
			$(".spinner").css('visibility', 'visible');
		});
		
		$("#populatePlatform").submit(function () {
			$(".spinner").css('visibility', 'visible');
		});
		
		$("#populatePublisher").submit(function () {
			$(".spinner").css('visibility', 'visible');
		});
		
		$("#populateGamePlatform").submit(function () {
			$(".spinner").css('visibility', 'visible');
		});
		
		$("#populateGamePublisher").submit(function () {
			$(".spinner").css('visibility', 'visible');
		});
		
		$("#populateRegion").submit(function () {
			$(".spinner").css('visibility', 'visible');
		});
		
		$("#populateRegionSales").submit(function () {
			$(".spinner").css('visibility', 'visible');
		});
		
		$("#populateUserProfile").submit(function () {
			$(".spinner").css('visibility', 'visible');
		});
	</script>
</body>
</html>