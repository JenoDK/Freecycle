<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${naam} verwijdered' />
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login">

		<div class="login-top">
			<h2>Het artikel ${naam} is verwijderd.</h2>
	</div>
	<c:if test='${not empty param.fout}'>
		<div class='fout'>${param.fout}</div>
	</c:if>
	</div>
</body>
</html>
