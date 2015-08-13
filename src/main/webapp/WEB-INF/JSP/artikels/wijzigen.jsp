<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${artikel.naam}' />
</head>
<body>
	<v:menu />
	<h1>${artikel.naam}</h1>
	<spring:url value='/artikels/{id}/wijzigen' var='url'>
		<spring:param name='id' value='${artikel.id}' />
	</spring:url>
	<form:form action='${url}' commandName='artikel'>
		<jsp:include page='artikelformfields.jsp' />
		<input type='submit' value='Wijzigen'>
	</form:form>
</body>
</html>