<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Home' />
<link rel='stylesheet' href='<c:url value="/styles/indexStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login">
		<div class="login-top">
			<img id="logo" src="<c:url value="/images/recycle.png" />"/>
<%-- 			<c:url value='/file/show/5' var='url' /> --%>
<%-- 			<img src="${url}" alt="car_image"/> --%>
			<h2>FREECYCLE</h2>
			<h3>Sharing is caring</h3>
	</div>
	<security:authorize access='isAnonymous()'>
		<div class="login-bottom">
			<div class='keepme'>
				<div class="keep-loginbutton2">
					<form action="<c:url value='/user/toevoegen'/>"><input
						type="submit" value="Aan de slag"></form>
			</div>
				<div class="keep-loginbutton2">
					<form action="<c:url value='/login'/>"><input type="submit"
						value="Inloggen"></form>
			</div>
				<div class="clear"></div>
		</div>
	</div>
	</security:authorize>
</div>
</body>
</html>