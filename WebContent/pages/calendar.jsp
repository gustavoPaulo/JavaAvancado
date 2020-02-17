<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Calendário</title>

<link href='../script/fullcalendar.min.css' rel='stylesheet' />
<link href='../script/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='../script/moment.min.js'></script>
<script src='../script/jquery.min.js'></script>
<script src='../script/fullcalendar.min.js'></script>

<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}

</style>

</head>
<body>

	<h1>Calendário</h1>
	
	<div id='calendar'></div>
	
</body>

<script>

	
	$(document).ready(function() {

		$.get("buscarCalendarioDatas", function(response) {
			//INICIO - Ajax GET
			
			var	datas = JSON.parse(response);
			
			$('#calendar').fullCalendar({
				header : {
					left : 'prev,next today',
					center : 'title',
					right : 'month,basicWeek,basicDay'
				},
				defaultDate : '2017-02-12',
				navLinks : true, // can click day/week names to navigate views
				editable : true,
				eventLimit : true, // allow "more" link when too many events
				events : datas
			});
		});//FIM - Ajax GET
	});
</script>

</html>