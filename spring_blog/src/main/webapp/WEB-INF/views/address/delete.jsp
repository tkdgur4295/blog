<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 24px;
}
div {
	text-align: center;
	margin-top: 50px;
}
</style>
<script type="text/javascript">
function alist() {
	var url = "list";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	
	location.href=url;
}
</script>
</head>
<body>
<c:choose>
<c:when test="${flag }">
삭제
</c:when>
<c:otherwise>
삭제 실패
</c:otherwise>
</c:choose>


	<div class ="bottom">
		<input type="button" value="목록으로..."
			onclick="alist()">
	</div>


</body>
</html>
