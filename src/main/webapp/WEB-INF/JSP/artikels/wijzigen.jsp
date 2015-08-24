<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/loginStyle.css"/>'>
<link rel='stylesheet'
	href='<c:url value="/styles/artikelToevoegenStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login">

		<div class="login-top">
			<h2>${artikel.naam}</h2>
	</div>
		<div class="login-bottom"><spring:url
				value='/artikels/{id}/wijzigen' var='url'>
				<spring:param name='id' value='${artikel.id}' />
			</spring:url> <form:form action='${url}' commandName='artikel'>
				<%-- 				<jsp:include page='artikelformfields.jsp' /> --%>


				<div class="user"><form:input path='naam' name='naam'
						type='text' placeholder="Artikelnaam" maxlength='50'
						required='required' /> <i></i></div>

				<div class="fout"><form:errors path='naam' cssClass="fout"
						delimiter=', ' /></div>

				<div class="user"><form:input path='geschatteWaarde'
						name='geschatteWaarde' placeholder="Geschatte waarde"
						required='required' /> <i></i></div>

				<div class="fout"><form:errors path='geschatteWaarde'
						cssClass="fout" delimiter=', ' /></div>

				<div class="user"><form:input path='regio' name='regio'
						placeholder="Regio" required='required' /> <i></i></div>

				<div class="fout"><form:errors path='regio' cssClass="fout"
						delimiter=', ' /></div>

				<div class="usertextarea"><form:textarea id="textarea"
						rows="5" cols="30" path='beschrijving' name="beschrijving"
						placeholder="Beschrijving(max 255 tekens)" maxlength="255" /></div>
				<p id="count"></p>
				<label> <form:select path="soort">
						<form:options items="${soorten}" itemLabel="id" />
					</form:select>
				</label>
				<label> <form:select path="ouderdom">
						<form:options items="${ouderdom}" itemLabel="id" />
					</form:select>
				</label>
				<div class="fout"><form:errors cssClass="fout" delimiter=', ' /></div>
				<div class='keepme'>
					<div class="keep-loginbutton"><input type='submit'
						value='Wijzigen'></div>
					<div class="clear"></div>
				</div>
			</form:form>
			<div class="clear"></div></div>
</div>
</body>
</html>