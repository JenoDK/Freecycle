<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<nav>
	<ul>
		<li><a href="<c:url value='/'/>">&#8962;</a></li>
		<li><a href="#">Artikels</a>
			<ul>
				<li><a href="<c:url value='/artikels'/>">Lijst</a></li>
				<li><a href="<c:url value='/artikels/toevoegen'/>">Toevoegen</a></li>
				<li><a href="<c:url value='/artikels/regio'/>">Per regio</a></li>
			</ul></li>
		<security:authorize access='isAnonymous()'>
			<li><a href="<c:url value='/login'/>">Aanmelden</a></li>
		</security:authorize>
		<security:authorize access='isAuthenticated ()'>
			<li><a href="#"><security:authentication property="name" /></a>
				<ul>
					<li>
					<spring:url value='/user/gegevens' var='wijzigURL'/>
					<form action='${wijzigURL}'>
						<input type='submit' value='Mijn Accountgegevens' id='logoutbutton'>
						<security:csrfInput />
					</form>
					</li>
					<li><a href="<c:url value='/user/mijnArtikels'/>">Mijn
							Artikels</a></li>
					<li>
						<form method='post' action='<c:url value="/logout"/>'
							id='logoutform'>
							<input type='submit'
								value='<security:authentication property="name"/> afmelden'
								id='logoutbutton'>
							<security:csrfInput />
						</form>
					</li>
				</ul></li>
		</security:authorize>
		<li><a href="<c:url value='/user/toevoegen'/>">Account
				aanmaken</a></li>
	</ul>
</nav>
