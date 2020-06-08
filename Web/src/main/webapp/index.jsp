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
	<div class="row" style="min-height: 100%">
		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto mb-3">
			<h1 class="mt-5 mb-3 font-weight-bold text-white text-center">
				<u>Games</u>
			</h1>
			<div>
				<div
					class="col-12 mt-3 mb-2 table-responsive bg-white p-4 border border-light rounded-lg">
					<table id="gamesTable"
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
								<th scope="col" class="dt-center"></th>
								<th scope="col" class="dt-center"></th>
								<th scope="col" class="dt-center"></th>
								<th scope="col" class="dt-center"></th>
								<th scope="col"></th>
								<th scope="col"></th>
							</tr>
						</tfoot>
					</table>
					<a class="btn btn-lg btn-danger" href="./games?view=gameAdmin"
						role="button">Add Game</a>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
	<jsp:include page="layout/footer.jsp" />
	
	<!-- Modal -->
	<div class="modal fade" id="deleteGameModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="deleteGameModalTitle">Delete Game</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Are you sure you want to delete the game <span
						class="font-weight-bold">#</span><span class="font-weight-bold"
						id="modal-gameId"></span>?
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						id="modalBotonEliminarGame">Yes</button>
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
	
		$('#gamesTable')
				.DataTable(
						{
							initComplete : function(settings, json) {
								var genres = json.filters.genres;
								var platforms = json.filters.platforms;
								var publishers = json.filters.publishers;
								var releaseYears = json.filters.releaseYears;
								this
										.api()
										.columns(1)
										.every(
												function() {
													var column = this;
													var select = $(
															'<select><option value="true">Hide inactives</option><option value="false">Show inactives</option></select>')
															.appendTo(
																	$(column
																			.footer()))
															.on(
																	'change',
																	function() {
																		var val = $.fn.dataTable.util
																				.escapeRegex($(
																						this)
																						.val());
																		column
																				.search(
																						val ? val
																								: '',
																						true,
																						false)
																				.draw();
																	});
												});
								this
										.api()
										.columns(2)
										.every(
												function() {
													var column = this;
													var select = $(
															'<select><option value=""></option></select>')
															.appendTo(
																	$(column
																			.footer()))
															.on(
																	'change',
																	function() {
																		var val = $.fn.dataTable.util
																				.escapeRegex($(
																						this)
																						.val());

																		column
																				.search(
																						val ? val
																								: '',
																						true,
																						false)
																				.draw();
																	});

													for (i = 0; i < genres.length; i++) {
														select
																.append('<option value="'+genres[i].id+'">'
																		+ genres[i].name
																		+ '</option>');
													}

												});
								this
										.api()
										.columns(3)
										.every(
												function() {
													var column = this;
													var select = $(
															'<select><option value=""></option></select>')
															.appendTo(
																	$(column
																			.footer()))
															.on(
																	'change',
																	function() {
																		var val = $.fn.dataTable.util
																				.escapeRegex($(
																						this)
																						.val());

																		column
																				.search(
																						val ? val
																								: '',
																						true,
																						false)
																				.draw();
																	});

													for (i = 0; i < platforms.length; i++) {
														select
																.append('<option value="'+platforms[i].id+'">'
																		+ platforms[i].name
																		+ '</option>');
													}

												});
								this
										.api()
										.columns(4)
										.every(
												function() {
													var column = this;
													var select = $(
															'<select><option value=""></option></select>')
															.appendTo(
																	$(column
																			.footer()))
															.on(
																	'change',
																	function() {
																		var val = $.fn.dataTable.util
																				.escapeRegex($(
																						this)
																						.val());

																		column
																				.search(
																						val ? val
																								: '',
																						true,
																						false)
																				.draw();
																	});

													for (i = 0; i < publishers.length; i++) {
														select
																.append('<option value="'+publishers[i].id+'">'
																		+ publishers[i].name
																		+ '</option>');
													}

												});
								this
										.api()
										.columns(5)
										.every(
												function() {
													var column = this;
													var select = $(
															'<select><option value=""></option></select>')
															.appendTo(
																	$(column
																			.footer()))
															.on(
																	'change',
																	function() {
																		var val = $.fn.dataTable.util
																				.escapeRegex($(
																						this)
																						.val());

																		column
																				.search(
																						val ? val
																								: '',
																						true,
																						false)
																				.draw();
																	});

													for (i = 0; i < releaseYears.length; i++) {
														select
																.append('<option value="'+releaseYears[i]+'">'
																		+ releaseYears[i]
																		+ '</option>');
													}

												});
							},
							"processing" : true,
							"serverSide" : true,
							"ajax" : {
								url : './gamesJSON',
								dataSrc : 'games'
							},
							"columns" : [ {
								data : 0
							}, {
								data : 1
							}, {
								data : 2
							}, {
								data : 3
							} ],
							"columnDefs" : [
									{
										"className" : "dt-center",
										"targets" : [ 7 ]
									},
									{
										"title" : "ID",
										'className' : 'font-weight-bold',
										"targets" : 0
									},
									{
										"title" : "Game",
										"targets" : 1
									},
									{
										"title" : "Genre",
										"targets" : 2
									},
									{
										"title" : "Platform",
										"targets" : 3
									},
									{
										"title" : "Publisher",
										"targets" : 4
									},
									{
										"title" : "Year",
										"targets" : 5
									},
									{
										"title" : "Sales (millions)",
										"targets" : 6,
										"searchable" : false
									},
									{
										"title" : "Actions",
										"targets" : 7,
										"data" : 0,
										"orderable" : false,
										"searchable" : false,
										"render" : function(data, type, row,
												meta) {
											return '<a role="button" class="btn btn-danger botonVerGame" data-id='
													+ data
													+ ' href="./games?view=gameAdmin&id='
													+ data
													+ '"><span class=\"glyphicon glyphicon-eye-open text-dark\"></span></a> <button class="btn btn-danger botonEliminar" data-id='+data+' data-toggle=\"modal\" data-target=\"#deleteGameModal\"><span class=\"glyphicon glyphicon-trash text-dark\"></span></button>'
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
		
		$('#gamesTable tbody').on('click', '.botonEliminar', function() {
			var id = $(this).attr("data-id");
			$("#modal-gameId").text(id);
		});
		
		$('#modalBotonEliminarGame').click(function() {
			$('#deleteGameModal').modal('toggle');
			$(".spinner").css('visibility', 'visible');
			var id = $('#modal-gameId').text();
			$.ajax({
				type : "POST",
				url : "./games?delete=game",
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
	</script>
</body>
</html>