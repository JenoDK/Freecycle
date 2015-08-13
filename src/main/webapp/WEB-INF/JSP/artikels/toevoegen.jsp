<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Artikel toevoegen' />
</head>
<body>
	<v:menu />
	<h1>Artikel toevoegen</h1>
	<c:url value='/artikels' var='url' />
	<form:form action='${url}' commandName='artikel' id='toevoegform'>
		<jsp:include page='artikelformfields.jsp' />
		<input type='submit' value='Toevoegen' id='toevoegknop'>
	</form:form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>