<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${artikel.naam}' />
</head>
<body>
	<v:menu />
	<c:choose>
		<c:when test="${not empty artikel}">
			<h1>${artikel.naam}</h1>
			<dl>
				<dt>Soort</dt>
				<dd>${artikel.soort.id}</dd>
				<dt>Geschatte waarde</dt>
				<dd>&euro; ${artikel.geschatteWaarde}</dd>
				<dt>Ouderdom</dt>
				<dd>${artikel.ouderdom.id}</dd>
				<dt>Regio</dt>
				<dd>${artikel.regio}</dd>
			</dl>
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
		</c:when>
		<c:otherwise>
			<div class='fout'>Artikel niet gevonden</div>
		</c:otherwise>
	</c:choose>
</body>
</html>