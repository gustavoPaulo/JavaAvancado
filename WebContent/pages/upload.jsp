<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
	<a href="../index.jsp">
     <button class="btn btn-danger">Sair</button>
	</a>
	
	<h1>Upload</h1>

	<input type="file" id="file" name="file" onchange="uploadFile();">
	
	<img alt="Imagem" src="" id="target" width="200" height="200">
	

	<br>
	<br>
	<br>
	<br>
	<a href="fileUpload?acao=carregar">Carregar imagens</a>				
	
	<br>
	
	<form action="fileUpload" method="get">
	<table>
		<c:forEach items="${listaUserImagen}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.login}</td>
				<td><a href="fileUpload?acao=download&iduser=${user.id}">Download Arquivo</a></td>
			</tr>
		</c:forEach>
	</table>
	</form>
	
	
	
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>

<script type="text/javascript">
	function uploadFile(){


	var target = document.querySelector("img");
	var file = document.querySelector("input[type=file]").files[0];
	
	var reader = new FileReader();
	
	reader.onloadend = function () {
		
		target.src = reader.result;
		
		///////-------Inicio - Upload Ajax--------
		
		$.ajax({
			method: "post",
			url: "fileUpload",
			data: { fileUpload : reader.result }
		
		}).done(function(response) {
			alert("Sucess: " + response);
			
		}).fail(function(xhr, status, errorThrown) {
			
			alert("Error: " + xhr.responseText);
		});
	
		///////-------Fim - Upload Ajax--------
		
	};
	
	if(file){
		
		reader.readAsDataURL(file);
		
	}else{
		target.src="";
	}
}
	
</script>


</html>