<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!DOCTYPE html>
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
			<h2>Welkom</h2>
			<h3>Aanmelden</h3>
		</div>
		<div class="login-bottom">
			<form method='post'>
				<div class="user">
					<input name='username' autofocus required type="text"
						value="Username" onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = 'Username';}"><i></i>
				</div>
				<div class="user-in">
					<input name='password' type='password' required type="password"
						value="Password" onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = 'Password';}"><i></i>
				</div>
				<div class='keepme'>
					<div class="keep-loginbutton">
						<security:csrfInput />
						<input type='submit' value='Aanmelden'>
					</div>
					<div class="clear"></div>
				</div>
			</form>
			<c:if test='${param.error != null}'>
				<div class='fout'>Verkeerde gebruikersnaam of paswoord.</div>
			</c:if>
			<div class="clear"></div>
			<div class="forgot">
				<spring:url var='url' value='/user/forgotPassword' />
				<p>
					<a href='${url}'>Paswoord vergeten?</a>
				</p>
				<div class="forgot-register">
					<p>
						Nog geen account? <a href="<c:url value='/user/toevoegen'/>">Registreer
							nu</a>
					</p>
				</div>
				<div class="clear"></div>
			</div>

		</div>
	</div>
</body>
</html>