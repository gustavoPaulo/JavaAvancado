<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Progress Bar</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">

/* Fundo da barra de progresso */
#myProgress{
	width: 50%;
	background-color: #ddd;
}

/* Barra de progresso */
#myBar{
	width: 0.5%;
	height: 15px;
	background-color: #4CAF50;
}

.ui-progressbar{
	position: relative;
}

.progress-label{
	position: relative;
	left: 30%;
	top: 4%;
	font-weigth: bold;
	text-shadow: 1px 1px 0 #fff;
}

</style>

</head>
<body>
	<a href="../index.jsp">
     <button class="btn btn-danger">Sair</button>
	</a>
	
	<h1>Barra de Progresso -> Exemplo com Javascript</h1>
	
	<!-- Barra de progresso com Javascript -->
	<div id="myProgress"> <!-- Fundo da barra de progresso -->
		<div id="myBar"></div> <!-- Barra de progresso -->
	</div>
	
	<br>
	<button onclick="exibirBarra()">Iniciar a barra de progresso</button>
	
	
	<!-- Barra de progresso com jQuery -->
	<br>
	<br>
	<br>
	<h1>Barra de Progresso -> Exemplo com jQuery</h1>
	
	<div id="progressbar">
		<div class="progress-label">
			Carregando...
		</div>
	</div>
	
	
	
	<script type="text/javascript">
	
	//Script da barra de progresso por jQuery
	$(function() {
		var progressbar = $("#progressbar"),
		progresslabel = $(".progress-label");
		
		progressbar.progressbar ({/*Cria a barra no <div>*/
			
			value: false,
			change: function () {
				
				progresslabel.text(progressbar.progressbar('value') + "%");
			},
			complete: function () {
				progresslabel.text('Completo!');
			}
		});
		
		function progress() {
			var val = progressbar.progressbar("value") || 0;
			
			progressbar.progressbar("value", val + 2);
			
			if(val < 99){
				setTimeout(progress, 80);
			}
		}
		setTimeout(progress, 2000);/*Chamado ao abrir a tabela*/
		
	});
	
	
	//Script da barra de progresso por Javascript
	function exibirBarra() {
		
		var elem = document.getElementById("myBar");
		var width = 1;
		var id = setInterval(frame, 10);
		
		function frame() {
			
			if(width >= 100){
				clearInterval(id);
			}else{
				width++;
				elem.style.width = width + "%";
			}
		}		
	}
</script>
	
</body>
</html>