<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Artikels' />
</head>
<body>
	<v:menu />
	<h1>${user.naam}</h1>
	<dl>
		<dt>email</dt>
		<dd>${user.email}</dd>
		<dt>aantal artikels</dt>
		<dd>${user.artikelscount}</dd>
	</dl>
	<spring:url value='/user/{id}/verwijderen' var='verwijderURL'>
		<spring:param name='id' value='${user.id}' />
	</spring:url>
	<form:form action='${verwijderURL}' method='post'>
		<input type='submit' value='Verwijderen'>
	</form:form>
	<spring:url value='/user/{id}/wijzigen' var='wijzigURL'>
		<spring:param name='id' value='${user.id}' />
	</spring:url>
	<form action='${wijzigURL}'>
		<input type='submit' value='Wijzigen'>
	</form>
</body>
</html>