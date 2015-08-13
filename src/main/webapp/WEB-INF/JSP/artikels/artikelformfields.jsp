<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<form:label path='naam'>Naam:<form:errors path='naam' />
</form:label>
<form:input path='naam' autofocus='autofocus' required='required'
	maxlength='50' />
<form:label path='geschatteWaarde'>Geschatte waarde artikel:
<form:errors path='geschatteWaarde' delimiter=', ' />
</form:label>
<form:input path='geschatteWaarde' required='required' />
<form:label path='regio'>Regio:<form:errors path='regio' />
</form:label>
<form:input path='regio' required='required' maxlength='50' />
<form:select path="soort" >   					
    <form:options items="${soorten}" itemLabel="id" />  					
</form:select>
<form:select path="ouderdom" >   					
    <form:options items="${ouderdom}" itemLabel="id" />  					
</form:select>