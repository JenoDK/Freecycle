<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Artikels per regio' />
</head>
<body>
	<v:menu />
	<h1>Artikels per regio</h1>
	<c:url value='/artikels' var='url' />
	<form:form action="${url}" commandName='regio' method='get'>
		<form:label path='regio'>Regio:<form:errors path='regio' />
		</form:label>
		<form:input path='regio' autofocus='autofocus' type='text'
			required='required' />


		<input type='submit' value='Zoeken'>
		<form:errors cssClass='fout' />
	</form:form>
	<c:forEach items='${artikels}' var='artikel'>
		<spring:url var='url' value='/artikels/{id}'>
			<spring:param name='id' value='${artikel.id}' />
		</spring:url>
		<h2>
			<a href='${url}'>${artikel.naam}</a>
		</h2>
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
	</c:forEach>
</body>
</html>