<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
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
			<h2>Paswoord resetten</h2>
	</div>
		<div class="login-bottom"><spring:url
				value='/user/forgotPassword/reset' var='url' /> <form:form
				action='${url}' commandName='usernameAndEmail' id='toevoegform'>
				<div class="user"><form:input path='naam' name='naam'
						type='text' placeholder="Gebruikersnaam" maxlength='50'
						required='required' /> <i></i></div>
				<div class="user-mail"><form:input path='email' name='email'
						maxlength='100' type='email' placeholder="Email"
						required='required' /> <i></i></div>
				<form:errors cssClass="fout" path='' delimiter=', ' />
				<div class='keepme'>
					<div class="keep-registrerenbutton"><input type='submit'
						value='Reset paswoord' id='toevoegknop'></div>
					<div class="clear"></div>
				</div>

			</form:form>
			<div class="clear"></div></div>
	</div>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>