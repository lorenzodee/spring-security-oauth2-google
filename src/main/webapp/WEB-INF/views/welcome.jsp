<%@ page session="false"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><!DOCTYPE html>
<html>
<head>
<title>Testing OAuth2 with Google</title>
</head>
<body>
<h1>Testing OAuth2 with Google</h1>
<p>Go to <a href="<c:url value='/secured' />">this</a> secured URL. You'll be asked to authenticate with the OAuth provider (in this case Google).</p>
</body>
</html>