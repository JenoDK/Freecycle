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
	<h1>Mijn artikels</h1>
	<c:forEach items='${artikels}' var='artikel'>
		<h2>
			<spring:url var='url' value='/artikels/{id}'>
				<spring:param name='id' value='${artikel.id}' />
			</spring:url>
			<a href='${url}'>${artikel.naam}</a>
		</h2>
		<p>
			Soort: ${artikel.soort.id}<br> Ouderdom: ${artikel.ouderdom.id}<br>
			Regio: ${artikel.regio}<br> Geschatte waarde: &euro;
			${artikel.geschatteWaarde}
		</p>
		<spring:url value='/artikels/{id}/verwijderen' var='verwijderURL'>
			<spring:param name='id' value='${artikel.id}' />
		</spring:url>
		<form:form action='${verwijderURL}' method='post'>
			<input type='submit' value='Verwijderen'>
		</form:form>
		<spring:url value='/artikels/{id}/wijzigen' var='wijzigURL'>
			<spring:param name='id' value='${artikel.id}' />
		</spring:url>
		<form action='${wijzigURL}'>
			<input type='submit' value='Wijzigen'>
		</form>
	</c:forEach>
</body>
</html>