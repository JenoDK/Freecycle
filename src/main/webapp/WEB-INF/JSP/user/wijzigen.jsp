<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Account wijzigen' />
</head>
<body>
	<v:menu />
	<h1>Account gegevens wijzigen</h1>
	<spring:url value='/user/{id}/wijzigen' var='url'>
		<spring:param name='id' value='${user.id}' />
	</spring:url>
	<form:form action='${url}' commandName='user'>
		<jsp:include page='userformfields.jsp' />
		<input type='submit' value='Wijzigen'>
	</form:form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
		</script>
</body>
</html>