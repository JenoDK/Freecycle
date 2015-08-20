<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Account aangemaakt' />
</head>
<body>
	<v:menu />
	<h1>Het account is aangepast.</h1>
	<c:if test='${not empty param.fout}'>
		<div class='fout'>${param.fout}</div>
	</c:if>
</body>
</html>