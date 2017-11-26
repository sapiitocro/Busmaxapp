<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	
<style>
	.error {color: #ff0000; }
</style>
		
</head>
<body>
	<form  method='post'>
            <div align="center" class="form-group col-md-12" style="backgroud-color: #3c3d3d;">
              <div align="center" class="form-group floating-label-form-group controls">
                <label>Username</label>
                <input type="text" class="form-control" placeholder="Nombre de usuario" name="username">
                <p class="help-block text-danger"></p>
              </div>
             
              <br>
            <div class="form-group col-md-12">
              <div class="form-group floating-label-form-group controls">
                <label>Contraseña</label>
                <input type="password" class="form-control" placeholder="Contraseña"name="password">
                <p class="help-block text-danger"></p>
              <button type="submit" class="btn btn-sm btn-success" >Ingresar</button>
             <div style="margin:5px;"></div>
              </div>
            </div>
   </div>
            
          </form>
</body>
</html>
