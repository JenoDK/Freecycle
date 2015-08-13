<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
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
			<li>
				<form method='post' action='<c:url value="/logout"/>'
					id='logoutform'>
					<input type='submit'
						value='<security:authentication property="name"/> afmelden'
						id='logoutbutton'>
					<security:csrfInput />
				</form>
			</li>
		</security:authorize>
	</ul>
</nav>
