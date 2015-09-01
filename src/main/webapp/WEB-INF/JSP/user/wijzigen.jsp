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
			<h2>Account gegevens wijzigen</h2>
	</div>
		<div class="login-bottom"><spring:url
				value='/user/{id}/wijzigen' var='url'>
				<spring:param name='id' value='${user.id}' />
			</spring:url> <form:form action='${url}' commandName='user' id='toevoegform'>
				<div class="user"><form:input path='naam' name='naam'
						type='text' placeholder="Gebruikersnaam" maxlength='50'
						required='required' /> <i></i></div>
				<div class="fout"><form:errors path='naam' cssClass="fout"
						delimiter=', ' /></div>
				<div class="user-in"><form:password path="paswoord"
						name='paswoord' required='required' placeholder="Paswoord" />
					<i></i></div>
				<div class="fout"><form:errors path='paswoord' cssClass="fout"
						delimiter=', ' /></div>

				<div class="user-in"><form:password path="matchingPaswoord"
						name='matchingPaswoord' required='required'
						placeholder="Paswoord herhalen" /><i></i></div>

				<div class="fout"><form:errors path='matchingPaswoord'
						cssClass="fout" delimiter=', ' /></div>
				<div class="user-mail"><form:input path='email' name='email'
						maxlength='100' type='email' placeholder="Email"
						required='required' /> <i></i></div>

				<div class="fout"><form:errors path='email' cssClass="fout"
						delimiter=', ' /></div>
				<div class="fout"><form:errors cssClass="fout" path=''
						delimiter=', ' /></div>
				<div class='keepme'>
					<div class="keep-registrerenbutton"><input type='submit'
						value='Wijzigen' id='toevoegknop'></div>
					<div class="clear"></div>
				</div>

			</form:form> <c:if test='${param.error != null}'>
				<div class='fout'>Verkeerde gebruikersnaam of paswoord.</div>
			</c:if>
			<div class="clear"></div></div>
</div>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>