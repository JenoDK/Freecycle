<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Artikels' />
</head>
<body>
	<v:menu />
	<h1>
		<spring:message code="aantalArtikels" arguments="${aantalArtikels}" />
	</h1>
	<c:forEach items='${artikels}' var='artikel'>
		<h2>
			<spring:url var='url' value='/artikels/{id}'>
				<spring:param name='id' value='${artikel.id}' />
			</spring:url>
			<a href='${url}'>${artikel.naam}</a>
		</h2>
		<p>Soort: ${artikel.soort.id}<br>
			Ouderdom: ${artikel.ouderdom.id}<br>
			Regio: ${artikel.regio}<br>
			Geschatte waarde: &euro; ${artikel.geschatteWaarde}
		</p>
	</c:forEach>
</body>
</html>