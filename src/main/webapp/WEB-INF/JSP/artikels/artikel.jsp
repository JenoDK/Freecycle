<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/loginStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css"
	rel="stylesheet">
</head>
<body>
	<v:menu />
	<c:choose>
		<c:when test="${not empty artikel}">
			<div class="login">
				<div class="login-top">
					<h2>${artikel.naam}</h2>
			</div>
				<dl>
					<dt>Soort</dt>
					<dd>${artikel.soort.id}</dd>
					<dt>Geschatte waarde</dt>
					<dd>&euro; ${artikel.geschatteWaarde}</dd>
					<dt>Ouderdom</dt>
					<dd>${artikel.ouderdom.id}</dd>
					<dt>Regio</dt>
					<dd>${artikel.regio}</dd>
					<dt>Gebruiker</dt>
					<dd>${artikel.user.naam}</dd>
			</dl> <c:choose>
					<c:when test="${not empty artikel.uploadFiles}">
						<div class="fotorama" data-nav="thumbs" data-allowfullscreen="native"><c:forEach
								items='${artikel.uploadFiles}' var='uploadFile'>
								<c:url value='/file/show/${uploadFile.id}' var='url' />
								<%-- 							<img class="artikelFoto" src="${url}" alt="" /> --%>
								<%-- 							<c:url value='/images/recycle.png' var='url2' /> --%>
								<!-- 							<a -->
								<%-- 								href="${url2}" --%>
								<!-- 								class="with-caption image-link"> <img class="artikelFoto"  -->
								<%-- 								src="${url2}"/> --%>
								<!-- 							</a> -->

								 <a href="${url}"><img src="${url}"></a>

							</c:forEach></div>
					</c:when>
					<c:otherwise>Nog geen fotos</c:otherwise>
				</c:choose> <br /> <c:choose>
					<c:when test="${not empty artikel.reacties}">
						<c:forEach items='${artikel.reacties}' var='reactie'>
							<dl>
								<dt>${reactie.user.naam}</dt>
								<dd>${reactie.reactie}</dd>
							</dl>
						</c:forEach>
					</c:when>
					<c:otherwise>Nog geen reacties</c:otherwise>
				</c:choose> <spring:url var='url' value='/reactie' /> <form:form
					action='${url}' method='post' commandName='reactieForm'>
					<form:label path='reactie'>Reactie(max 255 tekens):<form:errors
							path='reactie' />
					</form:label>
					<form:textarea id="textarea" rows="5" cols="30"
						path='reactie.reactie' autofocus='autofocus' required='required'
						maxlength="255" />
					<p id="count"></p>
					<form:input path='artikel.id' type="hidden" value="${artikel.id}" />
					<input type='submit' value='Reageren' id='toevoegknop'>

				</form:form>
			</div>
		</c:when>
		<c:otherwise>
			<div class='fout'>Artikel niet gevonden</div>
		</c:otherwise>
	</c:choose>
	<div class="login"></div>
	<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
	src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
	<script>
		document.getElementById('textarea').onkeyup = function() {
			document.getElementById('count').innerHTML = "Characters left: "
					+ (255 - this.value.length);
		};
	</script>
</body>
</html>