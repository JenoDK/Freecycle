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
				<dt>Gebruiker</dt>
				<dd>${artikel.user.naam}</dd>
			</dl>
			<c:choose>
				<c:when test="${not empty artikel.reacties}">
					<c:forEach items='${artikel.reacties}' var='reactie'>
						<dl>
							<dt>${reactie.user.naam}</dt>
							<dd>${reactie.reactie}</dd>
						</dl>
					</c:forEach>
				</c:when>
				<c:otherwise>Nog geen reacties</c:otherwise>
			</c:choose>
			<spring:url var='url' value='/reactie' />

			<form:form action='${url}' method='post' commandName='reactieForm'>
				<form:label path='reactie'>Reactie(max 255 tekens):<form:errors path='reactie' />
				</form:label>
				<form:textarea rows="5" cols="30" path='reactie.reactie'
					autofocus='autofocus' required='required'/>
				<form:input path='artikel.id' type="hidden" value="${artikel.id}" />
				<input type='submit' value='Reageren' id='toevoegknop'>

			</form:form>
		</c:when>
		<c:otherwise>
			<div class='fout'>Artikel niet gevonden</div>
		</c:otherwise>
	</c:choose>

</body>
</html>