<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Datatable jQuery</title>

<link rel="stylesheet"	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<script type="text/javascript"	src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<table id="minhatabela" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>Código</th>
				<th>Login</th>
				<th>Senha</th>
			</tr>
		</thead>
	</table>


</body>

<script type="text/javascript">
	$(document).ready(function() { //Faz o proessamento na hora que abre a tela
		$('#minhatabela').DataTable({
		"processing": true,
		"serverSide": true,
		"ajax": "carregarDadosDataTable" //Retorno dos dados do servidor
	});
});
</script>

</html>