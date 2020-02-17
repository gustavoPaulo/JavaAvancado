<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pai</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<a href="../index.jsp">
     <button class="btn btn-danger">Sair</button>
	</a>
	
	<h1>Página Pai load jQuery</h1>

	<input type="button" value="Carregar página" onclick="carregar();">
	
	
<div id="mostrarPaginaFilha"></div> <!-- Local de carregamento da página filha -->	


</body>
<script type="text/javascript">
	function carregar() {
		
		$("#mostrarPaginaFilha").load('paginaFilha.jsp'); //Carrega a pagina filha com load jQuery
		
	}

</script>

</html>