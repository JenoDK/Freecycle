<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Adminpanel' />
<link rel='stylesheet' href='<c:url value="/styles/artikelsStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css"
	rel="stylesheet">
</head>
<body>
	<v:menu />
	<div class="login">
		<div class="login-top">
			<spring:url value='/user/adminPanelUsers' var='usersURL'>
			</spring:url>
			<form action='${usersURL}'>
				<input type='submit' value='All users'>
			</form>
		</div>
		<div class="login-bottom">
			<c:if test="${not empty users}">
				<c:forEach items='${users}' var='user'>${user.naam} ${user.email} <spring:url
						value='/user/{id}/banHammer' var='banURL'>
						<spring:param name='id' value='${user.id}' />
					</spring:url>
					<form action='${banURL}'>
						<input type='submit' value='Ban user'>
					</form>
				</c:forEach>
			</c:if>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>