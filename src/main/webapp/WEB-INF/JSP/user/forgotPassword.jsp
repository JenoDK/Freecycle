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
	<h1>Paswoord resetten</h1>
	<spring:url value='/user/forgotPassword/reset' var='url' />
	<form:form action='${url}' commandName='usernameAndEmail'>
		<form:label path='naam'>Usernaam:
		</form:label>
		<form:input path='naam' autofocus='autofocus' required='required'
			maxlength='50' />
		<form:label path='email'>Email:<form:errors path='email' />
		</form:label>
		<form:input path='email' autofocus='autofocus' required='required'
			maxlength='100' type='email' />
		<form:errors cssClass="fout" path='' delimiter=', ' />
		<input type='submit' value='Reset paswoord'>
	</form:form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>