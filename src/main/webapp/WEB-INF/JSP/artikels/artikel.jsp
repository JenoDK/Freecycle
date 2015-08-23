<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/loginStyle.css"/>'>
<link rel='stylesheet' href='<c:url value="/styles/artikelStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css"
	rel="stylesheet">
</head>
<body>
	<v:menu />
	<div class="wrap"><div class="reacties">
			<div class="login right">
				<div class="login-top">
					<h2>Reacties</h2>
			</div>
				<div class="login-bottom"><spring:url var='url'
						value='/reactie' /> <form:form action='${url}' method='post'
						commandName='reactieForm'>
						<div class="usertextarea"><form:textarea id="textarea" rows="5" cols="30"
							path='reactie.reactie' placeholder="Reactie(max 255 tekens)" autofocus='autofocus' required='required'
							maxlength="255" /></div>
						<p id="count"></p>
						<form:errors cssClass='fout' />
						<form:input path='artikel.id' type="hidden" value="${artikel.id}" />
						<div class='keepme'>
							<div class="keep-registrerenbutton"><input type='submit'
								value='Reageren' id='toevoegknop'></div>
							<div class="clear"></div>
						</div>


					</form:form> <c:choose>
						<c:when test="${not empty artikel.reacties}">
							<c:forEach items='${artikel.reacties}' var='reactie'>
								<div class="reactie"><spring:url var='url'
										value='/user/{id}'>
										<spring:param name='id' value='${reactie.user.id}' />
									</spring:url>
									<p><a class="userLink" href='${url}'>${reactie.user.naam}</a>:
										${reactie.reactie}</p> <c:choose>
										<c:when test="${reactie.user.naam == currentUser}">
											<spring:url value='/reactie/{id}/verwijderen'
												var='verwijderURL'>
												<spring:param name='id' value='${reactie.id}' />
											</spring:url>
											<form:form action='${verwijderURL}' method='post'>
												<div class='keepme'>
													<div class="keep-registrerenbutton"><input
														type='submit' value='Reactie verwijderen' id='toevoegknop'></div>
													<div class="clear"></div>
												</div>
											</form:form>
										</c:when>
									</c:choose></div>
							</c:forEach>
						</c:when>
						<c:otherwise>Nog geen reacties</c:otherwise>
					</c:choose></div>
		</div>
	</div> <c:choose>
			<c:when test="${not empty artikel}">
				<div class="login artikel">
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
				</dl> <br />
					<div class="login-top">
						<h2>Foto's</h2>
				</div> <c:choose>
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
								data-ratio="800/600" data-enableifsingleframe="true"><c:url value='/images/nopicture.jpg'
									var='url' /><a href="${url}"><img src="${url}"></a></div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:otherwise>
				<div class='fout'>Artikel niet gevonden</div>
			</c:otherwise>
		</c:choose>
		<div class="login"></div></div>
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