<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html>
<head>
<link rel='stylesheet' href='<c:url value="/styles/loginStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login">

		<div class="login-top">
			<h2>Foto uploaden voor artikel: ${artikel.naam}</h2>
	</div>
		<div class="login-bottom"><c:url
				value='/file/uploadFile?${_csrf.parameterName}=${_csrf.token}'
				var='url' />
			<form method="post" action="${url}" enctype="multipart/form-data">

				<div><input type="file" name="uploadFile" size="50" /></div> <c:if
					test='${not empty param.fout}'>
					<div class='fout'>${param.fout}</div>
				</c:if>

				<div><input name="artikelid" type="hidden"
					value="${artikel.id}" /></div>
				<div><input type="submit" value="Upload" /></div>


		</form>
			<form method='get' action='<c:url value="/artikels/${artikel.id}"/>'>
				<input type='submit' class="submitLink" value='Doorgaan zonder foto'>
		</form></div>

</div>
</body>
</html>