<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/loginStyle.css"/>'>
<link rel='stylesheet' href='<c:url value="/styles/artikelsStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css"
	rel="stylesheet">
</head>
<body>
	<v:menu />
	<div class="login">
		<div class="login-top">
			<h2>Artikels per soort en/of regio</h2>
	</div>
		<div class="login-bottom"><c:url
				value='/artikels/artikelsZoeken' var='url' /> <form:form
				action="${url}" commandName='regioSoortOuderdom' method='get'>
				<div class="user"><form:input path='regio' name='regio'
						type='text' placeholder="Regio (kan leeg zijn om alleen op soort te zoeken)" maxlength='50'
						autofocus='autofocus' /></div>
				<label> <form:select path="soort" name='soort'>
						<form:options items="${soorten}" itemLabel="id" />
					</form:select>
				</label>
				<form:errors cssClass='fout' />
				<div class='keepme'>
					<div class="keep-loginbutton"><input type='submit'
						value='Zoeken' id='toevoegknop'></div>
					<div class="clear"></div>
				</div>
				<p>${fout}</p>

			</form:form></div> <c:forEach items='${artikels}' var='artikel'>
			<div class="login artikelsInside">
				<div class="login-top">
					<h2><spring:url var='url' value='/artikels/{id}'>
							<spring:param name='id' value='${artikel.id}' />
						</spring:url> <a href='${url}'>${artikel.naam}</a></h2>
					<h3>Regio ${artikel.regio}</h3>
			</div>
				<div class="login-bottom"><c:choose>
						<c:when test="${not empty artikel.uploadFiles}">
							<div class="fotorama" data-nav="thumbs"
								data-allowfullscreen="native" data-width="100%"
								data-ratio="800/600" data-enableifsingleframe="true"><c:forEach
									items='${artikel.uploadFiles}' var='uploadFile'>
									<c:url value='/file/show/${uploadFile.id}' var='url' />
									<a href="${url}"><img src="${url}"></a>
								</c:forEach></div>
						</c:when>
						<c:otherwise>
							<div class="fotorama" data-nav="thumbs"
								data-allowfullscreen="native" data-width="100%"
								data-ratio="800/600" data-enableifsingleframe="true"><c:url
									value='/images/nopicture.jpg' var='url' /><a href="${url}"><img
									src="${url}"></a></div>
						</c:otherwise>
					</c:choose></div>
			</div>
		</c:forEach>
</div>
	<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
	src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
</body>
</html>