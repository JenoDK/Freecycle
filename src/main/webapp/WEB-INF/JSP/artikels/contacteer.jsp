<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/loginStyle.css"/>'>
<link rel='stylesheet' href='<c:url value="/styles/contacteerStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login">

		<div class="login-top">
			<h2>Contacteer ${contactBericht.artikel.user.naam}</h2>
	</div>
		<div class="login-bottom"><spring:url
				value='/artikels/contacteer' var='url' /> <form:form
				action='${url}' commandName='contactBericht' id='toevoegform'>
				<div class="user"><form:input path='naam' name='naam'
						type='text' placeholder="Naam" maxlength='50' required='required' />
					<i></i></div>
				<div class="user-mail"><form:input path='email' name='email'
						maxlength='100' type='email' placeholder="Email"
						required='required' /> <i></i></div>
				<div class="usertextarea"><form:textarea id="textarea"
						rows="5" cols="30" path='bericht'
						placeholder="Bericht(max 255 tekens)" required='required'
						maxlength="255" /></div>
				<p id="count"></p>
				<form:input path='artikel.id' type="hidden" value="${contactBericht.artikel.id}" />
				<%
					ReCaptcha c = ReCaptchaFactory.newReCaptcha("6Lf7XgsTAAAAANVtZnaqMJCtpqTTBjeatUaxEQqJ","6Lf7XgsTAAAAADJQ940XN0Hwc6Qglw2pt2FUKhE1", false);
									out.print(c.createRecaptchaHtml(null, null));
				%>
				<div class="fout"><form:errors cssClass="fout" path=''
						delimiter=', ' /></div>
				<div class='keepme'>
					<div class="keep-registrerenbutton"><input type='submit'
						value='Versturen' id='toevoegknop'></div>
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
	<script>
		document.getElementById('textarea').onkeyup = function() {
			document.getElementById('count').innerHTML = "Characters left: "
					+ (255 - this.value.length);
		};
	</script>
</body>
</html>