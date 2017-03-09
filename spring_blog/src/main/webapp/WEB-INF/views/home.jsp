<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>

<c:when test="${empty sessionScope.id}">
<c:set var ="str">Spring MVC Blog  페이지 입니다,</c:set>
</c:when>

<c:otherwise>
<c:set var = "str">Hi ${sessionScope.id}.</c:set>
</c:otherwise>

</c:choose>

<c:set var ="title">Spring MVC Blog</c:set>

<c:if test="${not empty sessionScope.id && grade=='A'}">
<c:set var = "title">관리자페이지</c:set>
</c:if>
<html>
<head>
	<title>Home</title>
	<link href="${pageContext.request.contextPath }css/style.css" rel="stylesheet">
</head>
<body>
	<div class="title">${title }</div>

<div class = "content">
<h1>${str }</h1>
<img src = "images/비.png" width="50%"><br><br>

<c:choose>

<c:when test="${empty sessionScope.id }">
<input type = "button" value = "로그인" onclick="location.href = 'member/login'">
</c:when>

<c:otherwise>
<input type = "button" value = "로그아웃" onclick="location.href = 'member/logout'">
</c:otherwise>

</c:choose>

<P>  The time on the server is ${serverTime}. Hi Jack </P>
</div>



</body>
</html>
