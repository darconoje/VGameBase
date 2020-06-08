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
</head>
<body class="bg-dark">
	<jsp:include page="layout/header.jsp" />
	<div class="row" style="min-height: 100%">
		<div class="col-md-2"></div>
		<div class="col-md-8 mx-auto mb-3">
			<h1 class="mt-5 mb-3 font-weight-bold text-white text-center">
				<u>Publishers</u>
			</h1>
			<div>
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
								<th scope="col"></th>
							</tr>
						</tfoot>
					</table>
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
	
		$('#publishersTable').DataTable({
			"processing" : true,
			"serverSide" : true,
			"ajax" : {
				url : './publishersJSON',
				dataSrc : 'publishers'
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
						"title" : "ID",
						'className' : 'font-weight-bold',
						"targets" : 0
					},
					{
						"title" : "Publisher",
						"targets" : 1
					},
					{
						"title" : "Total Games",
						"targets" : 2,
						"searchable" : false				
					},
					{
						"title" : "Global Sales (millions)",
						"targets" : 3,
						"searchable" : false						
					}]
		});
	</script>
</body>
</html>