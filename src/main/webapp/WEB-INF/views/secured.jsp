<%@ page session="false"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"
%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"
%><!DOCTYPE html>
<html>
<head>
<title>Testing OAuth2 with Google</title>
</head>
<body>
<h1>Yey!</h1>
<sec:authentication property="principal" var="principal" />
<ul>
	<li>Id: <c:out value="${principal.id}" /></li>
	<li>User name: <c:out value="${principal.username}" /></li>
	<li>Email: <c:out value="${principal.email}" /></li>
	<li>
		<span>Picture:</span>
		<img style="display: block; width: 128px; height: auto" src="<c:url value='${principal.picture}' />" />
	</li>
</ul>
<sec:authorize access="isAuthenticated()">
	<p>Since you're logged in, you can try
		<c:url var="logoutUrl" value="/logout" />
		<a href="${logoutUrl}" onclick="document.getElementById('_logoutForm').submit(); return false;">logging out</a>.
		Logging out <em>does not</em> sign you out of the provider (Google, in this case).
		It actually logs you out of this client (i.e. invalidates session and clears cookies of this web app).
	</p>
	<%-- CSRF is enabled, by default. We'll need to POST /logout --%>
	<form:form id="_logoutForm" action="${logoutUrl}" cssStyle="display: none" method="post"></form:form>
</sec:authorize>
</body>
</html>