<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<link
	href="/webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css"
	rel="stylesheet">


<title>Add Todo Page</title>
</head>
<body>

	<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
		<a class="navbar-brand m-1" href="https://courses.in28minutes.com">in28minutes</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="/">Home</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/todos/showToDoPage">Todos</a></li>
			</ul>
		</div>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
		</ul>
	</nav>


	<div class="container">
		<h3>Hi ${name}, Please Enter your Todo Details</h3>

		<form:form action="addingToDo" modelAttribute="todo" method="POST">

			<fieldset class="mb-3">
				<form:label path="description">Description</form:label>
				<form:input type="text" path="description" required="required" />
				<form:errors path="description" />
			</fieldset>

			<fieldset class="mb-3">
				<form:label path="targetDate">Target Date</form:label>
				<form:input type="text" path="targetDate" required="required" />
				<form:errors path="targetDate" />
			</fieldset>

			<form:input type="hidden" path="id" />

			<form:input type="hidden" path="done" />

			<input type="submit" class="btn btn-success" />

		</form:form>

	</div>
	<script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="/webjars/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="/webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

	<script type="text/javascript">
		$('#targetDate').datepicker({
			format : 'yyyy-mm-dd',
			startDate : '-3d'
		});
	</script>

</body>
</html>