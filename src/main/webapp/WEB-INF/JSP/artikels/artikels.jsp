<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/loginStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login">
		<div class="login-top">
			<h2><spring:message code="aantalArtikels" arguments="${aantalArtikels}" /></h2>
	</div>
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
	</div>
</body>
</html>