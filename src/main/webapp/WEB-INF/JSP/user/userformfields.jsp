<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<form:label path='naam'>Usernaam:<form:errors path='naam' />
</form:label>
<form:input path='naam' autofocus='autofocus' required='required'
	maxlength='50' />
<form:label path='paswoord'>Paswoord:

</form:label>
<form:password path="paswoord" required='required' />

<form:label path='matchingPaswoord'>Paswoord herhalen:<form:errors
		path='matchingPaswoord' />
</form:label>
<form:password path="matchingPaswoord" required='required' />
<form:label path='email'>Email:<form:errors path='email' />
</form:label>
<form:input path='email' autofocus='autofocus' required='required'
	maxlength='100' type='email' />
	<form:errors cssClass="fout" path='' delimiter=', ' />
