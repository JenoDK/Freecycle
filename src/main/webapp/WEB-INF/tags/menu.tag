<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<div id="wrap">
	<ul class="navbar">
		<li><a href="<c:url value='/'/>">Home</a></li>
		<li><a href="#">Artikels</a>
			<ul>
				<li><a href="<c:url value='/artikels'/>">Lijst</a></li>
				<li><a href="<c:url value='/artikels/toevoegen'/>">Toevoegen</a></li>
				<li><a href="<c:url value='/artikels/zoeken'/>">Zoeken</a></li>
			</ul></li>
		<security:authorize access='isAnonymous()'>
			<li class="rightMenu"><a href="<c:url value='/login'/>">Inloggen<img id="aanmeldenlogo" src="<c:url value="/images/user2.png" />"/></a></li>
		</security:authorize>
		<security:authorize access='isAuthenticated ()'>
			<li class="rightMenu"><a href="#"><security:authentication property="name" /><img id="aanmeldenlogo" src="<c:url value="/images/user2.png" />"/></a>
				<ul>
					<li><spring:url value='/user/gegevens' var='wijzigURL' />
						<form action='${wijzigURL}'>
							<input type='submit' class="submitLink" value='Mijn Accountgegevens'
								id='logoutbutton'>
							<security:csrfInput />
						</form>
					<li><a href="<c:url value='/user/mijnArtikels'/>">Mijn
							Artikels</a></li>
					<li>
						<form method='post' action='<c:url value="/logout"/>'
							id='logoutform'>
							<input type='submit' class="submitLink" 
								value='<security:authentication property="name"/> afmelden'
								id='logoutbutton'>
							<security:csrfInput />
						</form>
					</li>
				</ul></li>
		</security:authorize>
		<security:authorize access='isAnonymous()'>
			<li class="rightMenu"><a href="<c:url value='/user/toevoegen'/>">Registreren</a></li>
		</security:authorize>
	</ul>
</div>
<div class="clear"></div>
