<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script>
function blist() {
	var url = "list";
	url +="?col=${col}";
	url +="&word=${word}";
	url +="&nowPage=${nowPage}";
	location.href = url;
}
</script>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
</style>
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>

	<!-- *********************************************** -->

	<DIV class="title">PasswdErrorPage</DIV>
	
	<div class = "content">
	비밀번호를 다시 입력해주세요
	
	</div>


		<DIV class='bottom'>
			<input type='button' value='다시시도' onclick="history.back()">
		</DIV>



	<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>