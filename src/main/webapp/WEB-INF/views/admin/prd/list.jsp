<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Spring MVC CRUD</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-8">
				<h4 class="text-center">Paradero</h4>
				<hr>
				<a href="<%=request.getContextPath()%>/admin/prd/createform"
					class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>
					Crear Paradero</a> <a href="<%=request.getContextPath()%>/admin/menu"
					class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i>
					Menu</a> <br />
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Latitud</th>
							<th>Longitud</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
			
						<c:forEach var="prd" items="${paraderos}" >
							<tr>
								<td>${prd.id_paradero}</td>
								<td>${prd.nombre}</td>
								<td>${prd.latitud}</td>
								<td>${prd.longitud}</td>
								<td><a
									href="<%=request.getContextPath()%>/admin/prd/editform/${prd.id_paradero}"
									class="btn btn-info btn-xs"> <i
										class="glyphicon glyphicon-check"></i>Edit
								</a></td>
								<td><a
									href="<%=request.getContextPath()%>/admin/prd/deleteform/${prd.id_paradero}"
									class="btn btn-danger btn-xs"> <i
										class="glyphicon glyphicon-trash"></i> Delete
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<font color="red">${message}</font>
		</div>
	</div>
</body>
</html>
