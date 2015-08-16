<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Account registreren' />
</head>
<body>
	<v:menu />
	<h1>Account aanmaken</h1>
	<c:url value='/user' var='url' />
	<form:form action='${url}' commandName='user' id='toevoegform'>
		<jsp:include page='userformfields.jsp' />
		<input type='submit' value='Toevoegen' id='toevoegknop'>
	</form:form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>